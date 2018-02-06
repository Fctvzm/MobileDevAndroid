package com.example.nurzhaussyn.kbtuapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CourseActivity extends AppCompatActivity {

    private String nameSubj, whenSubj, whoSubj, whatAboutSubj;
    private TextView when, who, whatAb;
    private ViewPager viewPager;
    private int dotsCount;
    private ImageView[] dots;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        Intent intent = getIntent();
        String[] arrData = intent.getStringArrayExtra("arrData");
        nameSubj = arrData[0];
        whenSubj = arrData[2];
        whoSubj = arrData[1];
        whatAboutSubj = arrData[3];
        //viewpager
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPager1Adapter adapter = new ViewPager1Adapter(this);
        viewPager.setAdapter(adapter);
        dotsCount = 3;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotsCount; i++){
                    //dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }
                //dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(nameSubj);
        //
        when = (TextView) findViewById(R.id.when);
        when.setText(whenSubj);
        who = (TextView) findViewById(R.id.who);
        who.setText(whoSubj);
        whatAb = (TextView) findViewById(R.id.whatAbout);
        whatAb.setText(whatAboutSubj);
        imageView = (ImageView) findViewById(R.id.imageUp);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
