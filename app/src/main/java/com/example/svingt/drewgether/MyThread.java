package com.example.svingt.drewgether;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by svingt on 9/26/14.
 */
public class MyThread extends Thread {

    SurfaceHolder sh;
    MyView mview;
    Canvas canvas;
    boolean run;

    public MyThread(SurfaceHolder _holder, MyView _view){
        sh = _holder;
        mview = _view;
    }

    public void init(){
        canvas = null;
        canvas = sh.lockCanvas(null);

    }

    public void setRunnable(boolean _run){
        run = _run;
    }

    public void run(){
        while(run) {

            canvas = null;
            try {
                canvas = sh.lockCanvas(null);
                synchronized(sh) {
                    mview.update();
                }

            } finally {

                if(canvas != null) {
                    sh.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

//end class
}
