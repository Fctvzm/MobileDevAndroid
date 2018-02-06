package com.example.nurzhaussyn.kbtuapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nurzhaussyn.kbtuapplication.fragments.EventsFragment;
import com.squareup.picasso.Picasso;

public class EventInfoActivity extends AppCompatActivity {

    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        Intent intent = getIntent();
        position = intent.getIntExtra(EventsFragment.POS, 0);

        Wall wall = EventsFragment.walls.get(position);
        Picasso.with(this).load(wall.getUrl()).into((ImageView)findViewById(R.id.backdrop));
        TextView textView = (TextView)findViewById(R.id.postText);
        textView.setText(wall.getContent());
    }


}
