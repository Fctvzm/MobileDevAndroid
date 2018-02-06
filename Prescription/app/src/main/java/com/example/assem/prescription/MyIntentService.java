package com.example.assem.prescription;

import android.Manifest;
import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

public class MyIntentService extends IntentService {

    private static final String[] EVENT_PROJECTION = new String[]{Calendars._ID,};
    private static final int PROJECTION_ID_INDEX = 0;
    private static final String timezone = "Asia/Almaty";
    private static final String username = "zaytkalieva739@gmail.com";
    private long calID = 0;
    private long eventID = 0;

    public static final String ACTION_PUT = "putEvent";
    public static final String PARAM_POS = "position";

    public MyIntentService() {
        super("MyIntentService");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_PUT.equals(action)) {
                int pos = intent.getIntExtra(PARAM_POS, 0);
                handleActionPUT(pos);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleActionPUT(int pos) {
        Prescription pill = MainActivity.pills.get(pos);
        Cursor cur = null;
        ContentResolver cr = getContentResolver();
        Uri uri = Calendars.CONTENT_URI;
        String selection = "((" + Calendars.ACCOUNT_NAME + " = ?) AND ("
                + Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[]{username, "com.google", username};

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {return;}
        cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);

        assert cur != null;
        while (cur.moveToNext()) {
            calID = cur.getLong(PROJECTION_ID_INDEX);
        }

        long startMillis = 0;
        long endMillis = 0;
        int mills = (pill.getDuration()-1)*24*60*60*1000;
        Calendar beginTime = Calendar.getInstance();
        startMillis = beginTime.getTimeInMillis();
        endMillis = startMillis + mills;

        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, startMillis);
        values.put(CalendarContract.Events.DTEND, endMillis);
        values.put(CalendarContract.Events.TITLE, pill.getName());
        values.put(CalendarContract.Events.DESCRIPTION, pill.getInstruction());
        values.put(CalendarContract.Events.CALENDAR_ID, calID);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, timezone);
        uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);

        assert uri != null;
        eventID = Long.parseLong(uri.getLastPathSegment());

        values = new ContentValues();
        values.put(CalendarContract.Reminders.MINUTES, 15);
        values.put(CalendarContract.Reminders.EVENT_ID, eventID);
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        uri = cr.insert(CalendarContract.Reminders.CONTENT_URI, values);

        Intent local = new Intent();
        local.setAction(ServiceComplete.ACTION_COMPLETE);
        this.sendBroadcast(local);
    }

}
