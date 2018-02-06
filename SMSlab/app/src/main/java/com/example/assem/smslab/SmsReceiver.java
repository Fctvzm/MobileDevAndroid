package com.example.assem.smslab;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        String text = "";
        for (int i=0; i<msgs.length; i++) {
            String smsBody = msgs[i].getMessageBody().toString();
            String address = msgs[i].getOriginatingAddress();
            MainActivity.smsAdapter.add(new Sms(smsBody, address, false));
        }
    }
}
