package com.example.nurzhaussyn.kbtuapplication;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nurzhaussyn.kbtuapplication.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

public class ViewPager1Adapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private int[] y1_labels, x1_labels;

    public ViewPager1Adapter (Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.pager1_layout, null);
        GraphView graphV = (GraphView) view.findViewById(R.id.graph);

        StaticLabelsFormatter statFormater = new StaticLabelsFormatter(graphV);
        //statFormater.setVerticalLabels(getVertLabels(position));
        statFormater.setHorizontalLabels(getHorizLabels(position));
        graphV.getGridLabelRenderer().setLabelFormatter(statFormater);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(getSeries(position));
        graphV.addSeries(series);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    private String[] getHorizLabels(int position) {
        Log.d("POSITION", ""+position);
        String[] labels;
        switch (position){
            case 0:
                labels = new String[6];
                labels[0] = "2014\naut";
                labels[1] = "2014\nwin";
                labels[2] = "2015\naut";
                labels[3] = "2015\nwin";
                labels[4] = "2016\naut";
                labels[5] = "2016\nwin";
                break;
            case 1:
                labels = new String[6];
                labels[0] = "2014\naut";
                labels[1] = "2014\nwin";
                labels[2] = "2015\naut";
                labels[3] = "2015\nwin";
                labels[4] = "2016\naut";
                labels[5] = "2016\nwin";
                break;
            case 2:
                labels = new String[6];
                labels[0] = "2014\naut";
                labels[1] = "2014\nwin";
                labels[2] = "2015\naut";
                labels[3] = "2015\nwin";
                labels[4] = "2016\naut";
                labels[5] = "2016\nwin";
                break;
            default:
                labels = new String[6];
                break;
        }
        return labels;
    }

    private String[] getVertLabels(int position) {
        String[] labels;
        switch (position){
            case 0:
                labels = new String[6];
                labels[0] = "20";
                labels[1] = "22";
                labels[2] = "23";
                labels[3] = "30";
                labels[4] = "25";
                labels[5] = "15";
                break;
            case 1:
                labels = new String[6];
                labels[0] = "20";
                labels[1] = "22";
                labels[2] = "23";
                labels[3] = "30";
                labels[4] = "25";
                labels[5] = "15";
                break;
            case 2:
                labels = new String[6];
                labels[0] = "20";
                labels[1] = "22";
                labels[2] = "23";
                labels[3] = "30";
                labels[4] = "25";
                labels[5] = "15";
                break;
            default:
                labels = new String[6];
                break;
        }
        return labels;
    }

    private DataPoint[] getSeries(int position) {
        DataPoint[] data;
        switch (position){
            case 0:
                data = new DataPoint[6];
                DataPoint d1 = new DataPoint(100, 20); //2014
                data[0] = d1;
                DataPoint d2 = new DataPoint(200, 22);
                data[1] = d2;
                DataPoint d3 = new DataPoint(300, 23); //2015
                data[2] = d3;
                DataPoint d4 = new DataPoint(400, 30);
                data[3] = d4;
                DataPoint d5 = new DataPoint(500, 25); //2016
                data[4] = d5;
                DataPoint d6 = new DataPoint(600, 15);
                data[5] = d6;
                break;
            case 1:
                data = new DataPoint[6];
                DataPoint d11 = new DataPoint(100, 20); //2014
                data[0] = d11;
                DataPoint d22 = new DataPoint(200, 22);
                data[1] = d22;
                DataPoint d33 = new DataPoint(300, 23); //2015
                data[2] = d33;
                DataPoint d44 = new DataPoint(400, 30);
                data[3] = d44;
                DataPoint d55 = new DataPoint(500, 25); //2016
                data[4] = d55;
                DataPoint d66 = new DataPoint(600, 15);
                data[5] = d66;
                break;
            case 2:
                //
                data = new DataPoint[6];
                DataPoint d111 = new DataPoint(100, 20); //2014
                data[0] = d111;
                DataPoint d222 = new DataPoint(200, 22);
                data[1] = d222;
                DataPoint d333 = new DataPoint(300, 23); //2015
                data[2] = d333;
                DataPoint d444 = new DataPoint(400, 30);
                data[3] = d444;
                DataPoint d555 = new DataPoint(500, 25); //2016
                data[4] = d555;
                DataPoint d666 = new DataPoint(600, 15);
                data[5] = d666;
                break;
            default:
                data = new DataPoint[6];
                break;
        }
        return data;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
