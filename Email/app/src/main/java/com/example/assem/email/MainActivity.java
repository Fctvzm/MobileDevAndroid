package com.example.assem.email;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {


    ArrayList<EmailData> arrayOfEmails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            readingFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final Intent intent = new Intent(this, EmailDetailActivity.class);

        final UserAdapter adapter = new UserAdapter(this, arrayOfEmails);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("data", arrayOfEmails.get(position));
                startActivity(intent);
            }

        });

       CheckBox check = (CheckBox)findViewById(R.id.title);
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    Collections.sort(arrayOfEmails);
                    adapter.notifyDataSetChanged();
                }

            }
        });

        CheckBox dateSort = (CheckBox)findViewById((R.id.date));
        dateSort.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    Collections.sort(arrayOfEmails, EmailData.dateComparator);
                    adapter.notifyDataSetChanged();
                }
            }
        });


    }

    public void readingFile () throws IOException, ParseException {
        arrayOfEmails = new ArrayList<EmailData>();
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.emails));
        while (scan.hasNextLine()){
            arrayOfEmails.add(new EmailData(scan.nextLine().toString(),
                            scan.nextLine().toString(), scan.nextLine().toString(), scan.nextLine().toString()));
        }
        scan.close();
    }



}
