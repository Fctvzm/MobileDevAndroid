package com.example.assem.prescription;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Assem on 13.11.2017.
 */

public class Adapter extends ArrayAdapter<Prescription> {

    public Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Prescription> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_layout, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView duration = (TextView) convertView.findViewById(R.id.duration);
        TextView dose = (TextView) convertView.findViewById(R.id.dose);
        TextView instruction = (TextView) convertView.findViewById(R.id.instruction);
        TextView frequency = (TextView) convertView.findViewById(R.id.frequency);

        Prescription pre = getItem(position);
        Context c = getContext();
        assert pre != null;
        name.setText(pre.getName());
        duration.setText(c.getResources().getString(R.string.duration, pre.getDuration()));
        dose.setText(c.getResources().getString(R.string.dose, pre.getDose()));
        frequency.setText(c.getResources().getString(R.string.freguency, pre.getFrequency()));
        instruction.setText(c.getResources().getString(R.string.intsr, pre.getInstruction()));

        return convertView;
    }
}
