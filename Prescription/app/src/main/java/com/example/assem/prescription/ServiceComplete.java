package com.example.assem.prescription;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class ServiceComplete extends BroadcastReceiver {
    public static final String ACTION_COMPLETE = "complete";
    @Override
    public void onReceive(Context context, Intent intent) {
        //ask about version v4 and version v7
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_event)
                .setContentTitle("Event of calendar")
                .setContentText("Your taking pills time and date added to calendar succesfully")
                .setDefaults(Notification.DEFAULT_SOUND)
                .setPriority(NotificationManager.IMPORTANCE_HIGH);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
