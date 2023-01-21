package com.example.myviewapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    private final DrawThread drawThread;
    private MyCircle circle;

    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        drawThread = new DrawThread();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        this.surfaceHolder = holder;
        drawThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    class MyCircle {

        float x, y, radius;
        float mx, my;
        Paint paint;

        public MyCircle(float x, float y) {

            this.x = x;
            this.y = y;
            mx=x;
            my=y;
            this.radius = 50;
            paint = new Paint();
            paint.setColor(Color.RED);
        }
        void draw(Canvas canvas) {
            canvas.drawCircle(x, y, radius, paint);
        }
        void update() {
            if (mx>x) x+=20;
            else x-=20;
            if (my>y) y+=20;
            else y-=20;
        }
    }


    class DrawThread extends Thread {
        private volatile boolean running = true;
        private  Canvas canvas;

        int circleCount=0;
        public void run() {

            while (running) {
                try {
                    sleep(100);
                    canvas = surfaceHolder.lockCanvas();
                    if(circleCount==0) circle = new MyCircle(canvas.getWidth()/2f, canvas.getHeight()/2f);
                    circleCount=1;
                    canvas.drawColor(Color.DKGRAY);
                    circle.draw(canvas);
                    circle.update();
                } catch (Exception e) {
                }
                finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        circle.mx=event.getX();
        circle.my=event.getY();
        return super.onTouchEvent(event);
    }



}