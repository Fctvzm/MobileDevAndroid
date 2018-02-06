package com.example.assem.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Calculate c = new Calculate();
    public boolean show = false;

    public void number_click(View view) {
        TextView t = (TextView)findViewById(R.id.textView);
        t.append(((Button) view).getText().toString());
    }

    public void operation_click(View view) {
        TextView t = (TextView)findViewById(R.id.textView);
        c.first = Integer.parseInt(t.getText().toString());
        c.operation = ((Button) view).getText().toString();
        t.setText("");
        show = false;
    }

    public void result_click(View view)
    {
        TextView t = (TextView)findViewById(R.id.textView);
        if (!show)
        {
            c.second = Integer.parseInt(t.getText().toString());
            show = true;
        }
        else
            c.first = Integer.parseInt(t.getText().toString());
        c.Cal();
        t.setText(c.result + "");
    }


    public void clear_click(View view) {
        c.first = 0;
        c.second = 0;
        c.result = 0;
        TextView t = (TextView)findViewById(R.id.textView);
        t.setText("");
    }
}
