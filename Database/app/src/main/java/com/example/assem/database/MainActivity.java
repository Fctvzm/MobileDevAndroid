package com.example.assem.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Person.people = new ArrayList<Person>();
        creatingDatabase();
        fetchingData();
        GridView gridView = (GridView)findViewById(R.id.gridView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.grid_layout, Person.getAllPeople());
        gridView.setAdapter(adapter);

        GraphView graph = (GraphView)findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series;
        int size = Person.people.size();
        DataPoint [] values = new DataPoint[size];
        for (int i = 0; i < size; i++) {
            DataPoint v = new DataPoint(i, Person.people.get(i).getAge());
            values[i] = v;
        }
        series = new LineGraphSeries<>(values);
        graph.addSeries(series);
    }

    public void creatingDatabase () {
        database = openOrCreateDatabase("people", MODE_PRIVATE, null);
        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.pizza));
        String query = "";
        while (scanner.hasNextLine()){
            query += scanner.nextLine() + "\n";
            if (query.trim().endsWith(";")){
                database.execSQL(query);
                query = "";
            }
        }
    }

    public void fetchingData () {
        Cursor cursor = database.rawQuery("SELECT name, age, gender from person", null);
        if (cursor.moveToFirst()){
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                String gender = cursor.getString(cursor.getColumnIndex("gender"));
                Person.people.add(new Person(name, gender, age));
            }
            cursor.close();
        }
    }

}
