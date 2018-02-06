package com.example.assem.email;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EmailDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_detail);

        Intent intent = getIntent();
        EmailData sample = (EmailData)intent.getSerializableExtra("data");

        TextView t = (TextView)findViewById(R.id.textView);
        t.setText(sample.toString());
    }
}
