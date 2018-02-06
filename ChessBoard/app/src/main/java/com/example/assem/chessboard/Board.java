package com.example.assem.chessboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Assem on 24.10.2017.
 */

public class Board extends View {

    public Board(Context context) {
        super(context);
    }

    public Board (Context context, AttributeSet attr) {
        super(context, attr);
    }

    @Override
    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);

        Paint black = new Paint();
        black.setARGB(255, 0, 0, 0);
        Paint white = new Paint();
        white.setARGB(255, 255, 255, 255);

        int width = canvas.getWidth();

        int recWidth = width / 8;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i%2 == 0) {
                    if (j%2 == 0) canvas.drawRect(new Rect(j*recWidth, i*recWidth, j*recWidth + recWidth, i*recWidth + recWidth), black);
                    else canvas.drawRect(new Rect(j*recWidth,i*recWidth, j*recWidth + recWidth, i*recWidth + recWidth), white);
                }
                else {
                    if (j%2 != 0) canvas.drawRect(new Rect(j*recWidth, i*recWidth, j*recWidth + recWidth, i*recWidth + recWidth), black);
                    else canvas.drawRect(new Rect(j*recWidth,i*recWidth, j*recWidth + recWidth, i*recWidth + recWidth), white);
                }
            }
        }

    }
}
