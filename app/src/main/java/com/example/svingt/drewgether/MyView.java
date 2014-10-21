package com.example.svingt.drewgether;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by svingt on 9/26/14.
 */
public class MyView extends SurfaceView implements SurfaceHolder.Callback{

    private float x;
    private float y;
    private int strokewidth;
    Paint paint = new Paint();
    MyThread thread;
    SurfaceHolder holder;
    Bitmap cmap, bmap;
    Canvas bitcanvas, canvas;
    int cwid, chie;
    int bgColor;
    int winsize;
    boolean pencil, isErase;
    Context ct;
    //private Matrix identityMatrix;

    public MyView(Context context){
        super(context);
        ct = context;
        init();
    }

    public MyView(Context context, AttributeSet attr){
        super(context, attr);
        ct = context;
        init();
    }

    public MyView(Context context, AttributeSet attr, int defStyle){
        super(context, attr, defStyle);
        ct = context;
        init();
    }

    private void init(){
        //make view a square shape
        if(getHeight() > getWidth())
            winsize = getWidth();
        else
            winsize = getHeight();
        setMeasuredDimension(winsize, winsize);

        setWillNotDraw(false);
        getHolder().addCallback(this);
        pencil = false;
        isErase = false;
        bgColor = Color.WHITE;
        strokewidth = 30;
        canvas = new Canvas();
        bitcanvas = new Canvas();
        holder = getHolder();
        thread = new MyThread(holder, this);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(strokewidth);

        x = 100;
        y = 100;
    }

    //Save bitmap as a png into cache directory as temp.png
    public void savePic(){
        try {
            new BitmapConvert(cmap, ct);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Create a Eraser Paint Color
    public void eraserPaint(){
        paint.setAlpha(0);
        isErase = true;
    }

    //Set paint to different color
    public void changePaint(int color){
        paint.setColor(color);
        isErase = false;
    }

    public void changeSize(int size){
        //change the size of strokes
        strokewidth = size;
        paint.setStrokeWidth(strokewidth);
    }

    public void changeBackground(int color){
        bgColor = color;
        canvas = null;
        canvas = holder.lockCanvas(null);
        canvas.drawColor(bgColor);
        canvas.drawBitmap(cmap,0,0,paint);
        holder.unlockCanvasAndPost(canvas);
    }

    public void clear(){
        canvas = null;
        canvas = holder.lockCanvas(null);
        cwid = getWidth();
        chie = getHeight();
        cmap = Bitmap.createBitmap(cwid, chie, Bitmap.Config.ARGB_8888);
        bitcanvas = new Canvas();
        bitcanvas.setBitmap(cmap);
        canvas.drawColor(bgColor);
        canvas.drawBitmap(cmap,0,0,paint);
        holder.unlockCanvasAndPost(canvas);
    }

    public void setXY(float _x, float _y){
        x = _x;
        y = _y;
    }

     public boolean onTouchEvent(MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    MyView.this.x = (int) event.getX();
                    MyView.this.y = (int) event.getY();
                    //paint.setColor(Color.GREEN);
                    //update(can);
                    //change = true;
                    update();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if(!isErase)
                        drawLine((int)event.getX(),(int)event.getY());
                    else
                        update();
                    //changeBackground(Color.YELLOW);
                    //change = true;
                    break;
                case MotionEvent.ACTION_UP:
                    MyView.this.x = (int) event.getX();
                    MyView.this.y = (int) event.getY();
                    update();
                    //clear();
                    //paint.setColor(Color.RED);
                    break;
            }

            return true;
        };

    public void update(){
        canvas = null;
        canvas = holder.lockCanvas(null);
        bitcanvas.drawCircle(x, y, strokewidth/2 , paint);
        canvas.drawColor(bgColor);
        canvas.drawBitmap(cmap,0,0,paint);
        holder.unlockCanvasAndPost(canvas);
    }

    public void drawLine(int _x, int _y){
        canvas = null;
        canvas = holder.lockCanvas(null);
        bitcanvas.drawLine(x, y, _x, _y, paint);
        if(!pencil)
            bitcanvas.drawCircle(_x,_y,strokewidth/2, paint);
        //fixed, remove for pencil style
        canvas.drawColor(bgColor);
        canvas.drawBitmap(cmap,0,0,paint);
        holder.unlockCanvasAndPost(canvas);
        x = _x;
        y = _y;
    }

    public Bitmap getMap(){
        return cmap;
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        cwid = getWidth();
        chie = getHeight();
        cmap = Bitmap.createBitmap(cwid, chie, Bitmap.Config.ARGB_8888);
        bitcanvas = new Canvas();
        bitcanvas.setBitmap(cmap);

        bmap = Bitmap.createBitmap(cwid,chie, Bitmap.Config.ARGB_8888);
        canvas = null;
        canvas = holder.lockCanvas(null);
        canvas.drawColor(bgColor);

        holder.unlockCanvasAndPost(canvas);

        //identityMatrix = new Matrix();
        //thread.setRunnable(true);
        //thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //thread.stop();
    }


// end class
}
