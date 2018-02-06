package com.example.assem.calculator;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    public TextView window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        window = (TextView) findViewById(R.id.textView);
    }


    public void number_click(View view) {
        Button b = (Button) view;
        window.setText(b.getText());
    }


    public void operation_click(View view) {
    }
}
