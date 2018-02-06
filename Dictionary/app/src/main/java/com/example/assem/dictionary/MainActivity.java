package com.example.assem.dictionary;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> words;
    ArrayList<String> defs;
    ArrayAdapter<String> adapter;


    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = (TextView)findViewById(R.id.textView);
        final Intent intent = new Intent(this, Main2Activity.class);


        try {
            readingFile();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),
                    "Problems: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }


        Button b = (Button)findViewById(R.id.bnSwitch);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent.putExtra("defs", defs);
                intent.putExtra("words", words);
                startActivity(intent);
            }
        });


        ListView list = (ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, words);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                t.setText(defs.get(position));
            }
        });

    }

    protected void onStart(){
        super.onStart();
        try {
            readingFileFromIStorage();
            adapter.notifyDataSetChanged();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void readingFile () throws IOException {
        words = new ArrayList<String>();
        defs = new ArrayList<String>();

        Scanner scan = new Scanner(getResources().openRawResource(R.raw.dictionary));
        while (scan.hasNextLine()){
            words.add(scan.nextLine().toString());
            defs.add(scan.nextLine().toString());
        }
        scan.close();
    }

    public void readingFileFromIStorage () throws FileNotFoundException {
        Scanner scan = new Scanner (
                openFileInput("dic2.txt"));
        while (scan.hasNext ()){
            words.add(scan.nextLine().toString());
            defs.add(scan.nextLine().toString());
            }
        scan.close ();
    }


}
