package com.example.nurzhaussyn.kbtuapplication.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nurzhaussyn.kbtuapplication.R;

/**
 * Created by Nurzhaussyn on 04.10.2017.
 */

public class NotifFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.notifications, container, false);
        return v;
    }
}
