package com.example.assem.umbrella;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Thread thread;
    Button start, pause;
    private Object mPauseLock;
    private boolean mPaused;
    public static boolean first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final com.example.assem.umbrella.Umbrella umbrella =
                (com.example.assem.umbrella.Umbrella)findViewById(R.id.umbrella);
        start = (Button)findViewById(R.id.Start);
        pause = (Button)findViewById(R.id.Pause);
        mPauseLock = new Object();
        mPaused = false;
        first = true;

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (mPauseLock) {
                        while (mPaused) {try {mPauseLock.wait();} catch (InterruptedException e) {}}
                    }
                    umbrella.postInvalidate();
                    try {Thread.sleep(1000);} catch (InterruptedException e) {}
                }
            }
        });
    }

    public void onClick(View view) throws InterruptedException {
        if (view instanceof Button) {
            Button button = (Button) view;
            if (button.getText().equals("Start")) {
                pause.setEnabled(true);
                start.setEnabled(false);
                if (first) {
                    thread.start();
                    first = false;
                }
                Resume();
            } else {
                start.setEnabled(true);
                pause.setEnabled(false);
                Pause();
            }
        }
    }

    private void Pause() {
        synchronized (mPauseLock) {
            mPaused = true;
        }
    }

    private void Resume() {
        synchronized (mPauseLock) {
            mPaused = false;
            mPauseLock.notifyAll();
        }
    }
}
