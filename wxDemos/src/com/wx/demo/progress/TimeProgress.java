package com.wx.demo.progress;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.wx.demo.R;
import com.wx.demo.utils.DeviceUtil;

import android.os.Handler;

/**
 * Description:
 * Created by browserwang on 2015/11/9.
 */
public class TimeProgress extends View {

    Paint mPaint;
    int mProgress;//0-100
    Bitmap backgroundBitmap;

    private float startAngle = -90;
    private float sweepAngle = 0;

    int validateFrequency = 50;
    float radius;

    public TimeProgress(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);

        backgroundBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.aio_pic_timing_bg);
    }

    private void getAngle() {
        startAngle = 360f*mProgress/100-90;
        sweepAngle = 360-3.6f*mProgress;
    }

    public void setProgress(int progress) {
        if (progress<0 || progress>100) return;

        mProgress = progress;
        invalidate();
    }

    /**
     *
     * @param startProgress 开始进度
     * @param endProgress   结束进度
     * @param duration      从开始进度到结束进度经历的时间，单位ms
     */
    float miniProgress;
    int mEndProgress;
    public void setProgressGradually(int startProgress, int endProgress, int duration) {
        mProgress = startProgress;
        int count = duration/validateFrequency;
        mEndProgress = endProgress;
        miniProgress = (endProgress-startProgress)*1f/count;
        mHandler.post(runnable);
    }

    private Handler mHandler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mProgress<mEndProgress) {
                mProgress+=miniProgress;
                invalidate();
                mHandler.postDelayed(this, validateFrequency);
            }
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getAngle();

        //draw background
        canvas.drawBitmap(backgroundBitmap, 0, 0, null);
        int w = backgroundBitmap.getWidth();
        int h = backgroundBitmap.getHeight();

        //draw progress
        RectF rect = new RectF(w/2-30, h/2-30, w/2+30, h/2+30);

        canvas.drawArc(rect, startAngle, sweepAngle, true, mPaint);
    }
}
