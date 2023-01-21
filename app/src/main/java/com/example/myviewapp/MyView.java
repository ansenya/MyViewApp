package com.example.myviewapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MyView extends View {
    private final Bitmap someBird;
    private final Paint paint = new Paint();
    private int currentColor = 0;

    public MyView(Context context) {
        super(context);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bluebird_1);
        someBird = Bitmap.createScaledBitmap(bitmap, 70, 50, false) ;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (currentColor){
            case 0:
                canvas.drawColor(Color.BLUE);
                break;
            case 1:
                canvas.drawColor(Color.GREEN);
                break;
            case 2:
                canvas.drawColor(Color.YELLOW);
                break;
            default:
                canvas.drawColor(Color.YELLOW);
        }
        for(Pair<Float, Float> coordinates: coordinates){
            canvas.drawBitmap(someBird, coordinates.first, coordinates.second, null);
        }

        drawStaff(canvas);
        super.onDraw(canvas);

    }



    List<Pair<Float, Float>> coordinates = new ArrayList<>();

    private void drawStaff(Canvas canvas){
        canvas.drawBitmap(someBird, 50, 75, null);
    }

   @Override
    public boolean onTouchEvent(MotionEvent event) {
        coordinates.add(new Pair<>(event.getX(), event.getY()));
        currentColor = (currentColor + 1) % 3;
        invalidate();
        return super.onTouchEvent(event);
    }
}