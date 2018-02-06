package com.example.assem.email;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Assem on 20.09.2017.
 */

public class UserAdapter extends ArrayAdapter<EmailData> {

    public UserAdapter(Context context, ArrayList<EmailData> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        EmailData email = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }
        // Lookup view for data population
        TextView sender = (TextView) convertView.findViewById(R.id.sender);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        // Populate the data into the template view using the data object
        sender.setText(email.sender);
        title.setText(email.title);

        /*sender.setTag(position);
        sender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                Intent intent = new Intent(MainActivity.class, EmailDetailActivity.class);
            }
        });*/
        // Return the completed view to render on screen
        return convertView;
    }

}
