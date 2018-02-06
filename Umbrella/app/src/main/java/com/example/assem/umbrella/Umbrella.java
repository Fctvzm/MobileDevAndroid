package com.example.assem.umbrella;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Assem on 30.10.2017.
 */

public class Umbrella extends View {

    private static ArrayList<Pair<Integer, Integer> > bmps;
    private static int frames;
    private static Bitmap bmp;
    private static final int size = 100;

    public Umbrella(Context context) {
        super(context);
        if (bmps == null) {
            init();
        }
    }

    public Umbrella(Context context, AttributeSet attr) {
        super(context, attr);
        if (bmps == null) {
            init();
        }
    }

    private void init () {
        bmps = new ArrayList<>();
        frames = -1;
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.umbrella);
        bmp = Bitmap.createScaledBitmap(bmp, size, size, true);
    }

    @Override
    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
        if (!MainActivity.first)fallUmbrella(canvas);
    }

    private void fallUmbrella (Canvas canvas) {
        Random random = new Random();
        int maxX = canvas.getWidth() - size;
        int maxY = canvas.getHeight();
        int x = 0;
        frames++;

        if (frames % 3 == 0) {
            x = random.nextInt(maxX);
            bmps.add(new Pair(x, -size));
        }

        for (int i = 0; i < bmps.size(); i++) {
            int actY = bmps.get(i).second.intValue() + size;
            if (actY < maxY) {
                int actX = bmps.get(i).first.intValue();
                bmps.set(i, new Pair(actX, actY));
                canvas.drawBitmap(bmp, actX, actY, null); //меланин
            }
        }
    }
}
