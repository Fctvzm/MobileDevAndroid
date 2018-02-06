package com.example.assem.smslab;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button sendButton;
    private EditText messageEditText;
    public static Adapter smsAdapter;
    private ListView smsListView;
    private final String number1 = "15555215556";
    private final String number2 = "15555215554";
    private SmsManager mgr;
    private TelephonyManager tmr;
    private String own;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        smsListView = (ListView)findViewById(R.id.messageListView);
        sendButton = (Button)findViewById(R.id.sendButton);
        messageEditText = (EditText)findViewById(R.id.messageEditText);

        if (checkSelfPermission(Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
        }

        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                == PackageManager.PERMISSION_DENIED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        }

        tmr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        own = tmr.getLine1Number();

        ArrayList<Sms> smses = new ArrayList<>();
        smsAdapter = new Adapter(this, R.layout.item_message, smses);
        smsListView.setAdapter(smsAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                String reciever = "";
                if (own.equals(number1)) {
                    reciever = number2;
                } else {
                    reciever = number1;
                }
                Sms sms = new Sms(messageEditText.getText().toString(), reciever, true);
                smsAdapter.add(sms);
                messageEditText.setText("");
                mgr = SmsManager.getDefault();
                mgr.sendTextMessage (sms.getNumber(), null , sms.getText(), null , null );
            }
        });

    }
}
