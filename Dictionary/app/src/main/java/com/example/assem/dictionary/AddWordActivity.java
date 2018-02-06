package com.example.assem.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class AddWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        final EditText word = (EditText) findViewById(R.id.word);
        final EditText def = (EditText) findViewById(R.id.definition);
        Button b = (Button)findViewById(R.id.addWord);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    PrintStream output = new PrintStream(
                            openFileOutput("dic2.txt", MODE_APPEND));
                    output.println(word.getText().toString());
                    output.println(def.getText().toString());
                    output.close();
                    Toast.makeText(getApplicationContext(), R.string.toast_added, Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
