package com.example.assem.dictionary;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    Intent intent;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mp = MediaPlayer.create(this, R.raw.hello);
        mp.start();

    }

    public void onClick(View view) {
        Button b = (Button)view;
        Log.d("tessst",b.getText() +"="+ getResources().getString( R.string.button_play));
        if (b.getText().equals( getResources().getString( R.string.button_play)))
            intent = new Intent(this, MainActivity.class);
        else
            intent = new Intent(this, AddWordActivity.class);
        startActivity(intent);
    }

    protected void onStart() {
        super.onStart();
        mp.start();
    }
}
