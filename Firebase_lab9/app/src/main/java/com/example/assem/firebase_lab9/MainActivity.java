package com.example.assem.firebase_lab9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button sendButton;
    private EditText messageEditText;
    private String userName;
    private ListView messageListView;
    private Adapter messageAdapter;

    private FirebaseDatabase mFireBaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageListView = (ListView)findViewById(R.id.messageListView);

        userName = "Assem";

        mFireBaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFireBaseDatabase.getReference().child("messages");

        ArrayList<Message> messages = new ArrayList<>();
        messageAdapter = new Adapter(this, R.layout.item_message, messages);
        messageListView.setAdapter(messageAdapter);

        sendButton = (Button)findViewById(R.id.sendButton);
        messageEditText = (EditText)findViewById(R.id.messageEditText);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = new Message(messageEditText.getText().toString(), userName, true);
                mMessagesDatabaseReference.push().setValue(message);
                messageEditText.setText("");
            }
        });

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Message message = dataSnapshot.getValue(Message.class);
                if (message.getName().equals(userName)) {message.isMine = true;}
                else {message.isMine = false;}
                messageAdapter.add(message);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        mMessagesDatabaseReference.addChildEventListener(mChildEventListener);
    }
}
