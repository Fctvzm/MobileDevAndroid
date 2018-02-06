package com.example.nurzhaussyn.kbtuapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;


public class LoginFragment extends Fragment {

    private String[] scope = new String[]{VKScope.GROUPS};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        Button b = (Button)v.findViewById(R.id.LoginButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VKSdk.login(getActivity(), scope);
            }
        });
        return v;
    }

}