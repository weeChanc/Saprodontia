package com.example.saprodontia.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.saprodontia.R;
import com.example.saprodontia.Utils.LogUtil;

/**
 * Created by 铖哥 on 2017/7/17.
 */

public class MyProgressBar extends View {

    private long max = 1;
    private Long curProgress = 1L;
    private Long destProgress = 1L;
    private TypedArray typedArray ;
    private Paint mPaint;
    private boolean enable = false;

    public MyProgressBar(Context context) {
        super(context);
    }

    public MyProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyProgressBar);
        mPaint.setColor(typedArray.getColor(R.styleable.MyProgressBar_color, Color.argb(160 ,233, 182, 199)));
        mPaint.setStyle((Paint.Style.FILL));
        mPaint.setAntiAlias(true);
    }

    public MyProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(enable) {
            canvas.drawRect(0, 0, (float) ((curProgress * getWidth() +0.0 )/( max + 0.0)), getHeight(), mPaint);
            LogUtil.e(curProgress + "  ");
        }

    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        enable = true;
        this.max = max;
    }

    public long getProgress() {
        return curProgress;
    }

    public void setProgress(long destProgress) {
        LogUtil.e("DESTPROGRESS  "+destProgress);
            this.destProgress = destProgress;
                startAnimation((destProgress - curProgress)/100);
    }

    private  void startAnimation(final long velocity){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(curProgress < destProgress){
                    curProgress += velocity;
                    try {
                        Thread.sleep(10);
                        if(curProgress>destProgress){
                            curProgress = destProgress;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
                LogUtil.e("DONE");
                postInvalidate();

            }
        }).start();

    }


}
