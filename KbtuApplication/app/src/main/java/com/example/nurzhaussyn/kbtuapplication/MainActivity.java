package com.example.nurzhaussyn.kbtuapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.nurzhaussyn.kbtuapplication.fragments.CoursesFragment;
import com.example.nurzhaussyn.kbtuapplication.fragments.EventsFragment;
import com.example.nurzhaussyn.kbtuapplication.fragments.NotifFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class MainActivity extends AppCompatActivity {

    BottomBar bottomBar;
    EventsFragment ph;
    LoginFragment lg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.bottomBarItemOne){
                    if (VKSdk.wakeUpSession(getApplicationContext())) {
                        ph = new EventsFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, ph).commit();
                    } else {
                        lg = new LoginFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, lg).commit();
                    }
                }else if(tabId == R.id.bottomBarItemTwo){
                    NotifFragment ha = new NotifFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, ha).commit();
                }else{
                    CoursesFragment f = new CoursesFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                ph = new EventsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, ph).commit();
            }
            @Override
            public void onError(VKError error) {
            }
        }));
    }
}
