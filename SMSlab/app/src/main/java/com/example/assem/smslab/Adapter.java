package com.example.assem.smslab;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Assem on 07.11.2017.
 */

public class Adapter extends ArrayAdapter<Sms> {

    public Adapter(Context context, int resource, ArrayList<Sms> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_message, parent, false);
        }

        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);

        Sms sms = getItem(position);
        messageTextView.setText(sms.getText());
        authorTextView.setText(sms.getNumber());

        LinearLayout layout = (LinearLayout)convertView.findViewById(R.id.bubble_layout);
        LinearLayout parent_layout = (LinearLayout)convertView.findViewById(R.id.bubble_layout_parent);

        if (sms.isMine) {
            layout.setBackgroundResource(R.drawable.bubble2);
            parent_layout.setGravity(Gravity.RIGHT);
        }
        else {
            layout.setBackgroundResource(R.drawable.bubble1);
            parent_layout.setGravity(Gravity.LEFT);
        }
        return convertView;
    }
}
