package com.example.nurzhaussyn.kbtuapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nurzhaussyn on 03.10.2017.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images = {R.drawable.slide1,R.drawable.slide2,R.drawable.slide3,R.drawable.slide4 };
    private String [] texts = {"Data mining makes it possible to analyze routine business transactions and glean a significant amount of information about individuals' buying habits and preferences",
            "Have you ever been writing an important text or email and notice afterwards you’ve incorrectly spelled something? Luckily, machine learning algorithms are used to detect when a word is spelled incorr",
            "Picking the most creative paintings is a network problem akin to finding super spreaders of disease. That’s allowed a machine to pick out the most creative paintings in history",
            "Developing applications using .Net framework is very robust and highly secure with great quality. .Net platform reduces development time, creates quality, reliable, and scalable applications that ens"};

    public ViewPagerAdapter (Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.pager_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(texts[position]);
        imageView.setImageResource(images[position]);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
