package com.example.assem.prescription;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    public static ArrayList<Prescription> pills;
    private BroadcastReceiver completeReceiver;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkSelfPermission(Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.READ_CALENDAR}, 1);
        }

        pills = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ServiceComplete.ACTION_COMPLETE);
        completeReceiver = new ServiceComplete();
        registerReceiver(completeReceiver, filter);

        getJSON();

        listView.setAdapter(new Adapter(this, R.layout.item_layout, pills));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onClickView(i);
            }
        });
    }

    @Override
    protected void onDestroy (){
        super.onDestroy();
        unregisterReceiver(completeReceiver);
    }

    private void getJSON () {
        String json = null;
        try {
            InputStream is = getAssets().open("prescription.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            parseJSON(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseJSON (String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray items = (JSONArray)jsonObject.getJSONArray("items");
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = (JSONObject)items.get(i);
            String Jname = item.getString("name");
            String Jdose = item.getString("dose");
            String Jinstruction = item.getString("instruction");
            int Jfrequency = item.getInt("frequency");
            int Jduration = item.getInt("duration");
            pills.add(new Prescription(Jname, Jdose, Jfrequency, Jduration, Jinstruction));
        }
    }

    private void onClickView (int pos) {
        Intent intent = new Intent(this, MyIntentService.class);
        intent.setAction(MyIntentService.ACTION_PUT);
        intent.putExtra(MyIntentService.PARAM_POS, pos);
        startService(intent);
    }
}
