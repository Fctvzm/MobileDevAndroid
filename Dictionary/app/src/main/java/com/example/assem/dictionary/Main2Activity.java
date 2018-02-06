package com.example.assem.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {

    ArrayList<String> words;
    ArrayList<String> defs;
    TextView t;
    Random r;
    int random;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        words = intent.getStringArrayListExtra("words");
        defs = intent.getStringArrayListExtra("defs");

        r = new Random();
        random = r.nextInt(10);

        t = (TextView)findViewById(R.id.textView2);
        t.setText(words.get(random));

        ListView list = (ListView)findViewById(R.id.ListView2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, defs);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //if (toast != null) toast.cancel();
                if (position == random)
                    toast.makeText(getApplicationContext(), R.string.toast_congrats, Toast.LENGTH_SHORT).show();
                else
                    toast.makeText(getApplicationContext(), R.string.toast_loser, Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void textView_click(View view) {
        random = r.nextInt(10);
        t.setText(words.get(random));
    }
}
