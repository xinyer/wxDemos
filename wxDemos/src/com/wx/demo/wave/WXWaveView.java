package com.wx.demo.wave;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/** 
 * @author browserwang 
 * @version 2014年8月19日 下午1:18:50 
 * 类说明 
 */
public class WXWaveView extends View{

	private final static String TAG = "WaveView";
	private final static int NUM = 8;
	
	public int[] WaveLevel = new int[]{1,2,3,4,5,6,7,8};
	
	private int mLevel;
	
	Paint mPaint;
	Path mPath1, mPath2, mPath3, mPath4;

	int[] xPoint;
	int yMax, yMin, yMid, YD;
	
	int waveColor = Color.parseColor("#12a86b");
	int backgroundColor = Color.parseColor("#f7f7f1");
	
	public WXWaveView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		mLevel = 0;
		
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setColor(waveColor);
		
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		xPoint = new int[NUM];
		int width = getWidth();
		int heigh = getHeight();
		int x = width/NUM;
		for (int i=0;i<NUM;i++) {
			xPoint[i] = x*(i+1);
			Log.d(TAG, "xPoint:"+xPoint[i]);
		}
		yMid = heigh/2;
		yMin = -heigh/2;
		yMax = heigh*3/2;
		YD = heigh/NUM;
		Log.d(TAG, "height/2:" + yMid +"\t yMin:" + yMin + "\t yMax:" + yMax);
		
		//makePath(mPath1, mLevel);
	}
	
	private void makePath() {
		Log.d(TAG, "mLevel:" + mLevel);
		//yMin = yMin*mLevel/WaveLevel.length;
		//yMax = yMax*mLevel/WaveLevel.length;
		
		mPath1 = new Path();
		mPath2 = new Path();
		mPath3 = new Path();
		mPath4 = new Path();
		
		mPath1.moveTo(0, yMid);
		mPath1.quadTo(xPoint[0], yMin+YD*mLevel, xPoint[1], yMid);
		mPath1.quadTo(xPoint[2], yMax-YD*mLevel, xPoint[3], yMid);
		mPath1.quadTo(xPoint[4], yMin+YD*mLevel, xPoint[5], yMid);
		mPath1.quadTo(xPoint[6], yMax-YD*mLevel, xPoint[7], yMid);
		
		mPath2.moveTo(0, yMid);
		mPath2.quadTo(xPoint[0], yMin+YD*(mLevel+1), xPoint[1], yMid);
		mPath2.quadTo(xPoint[2], yMax-YD*(mLevel+1), xPoint[3], yMid);
		mPath2.quadTo(xPoint[4], yMin+YD*(mLevel+1), xPoint[5], yMid);
		mPath2.quadTo(xPoint[6], yMax-YD*(mLevel+1), xPoint[7], yMid);
		
		mPath3.moveTo(0, yMid);
		mPath3.quadTo(xPoint[0], yMin+YD*(mLevel+2), xPoint[1], yMid);
		mPath3.quadTo(xPoint[2], yMax-YD*(mLevel+2), xPoint[3], yMid);
		mPath3.quadTo(xPoint[4], yMin+YD*(mLevel+2), xPoint[5], yMid);
		mPath3.quadTo(xPoint[6], yMax-YD*(mLevel+2), xPoint[7], yMid);
		
		mPath4.moveTo(0, yMid);
		mPath4.quadTo(xPoint[0], yMin+YD*(mLevel+3), xPoint[1], yMid);
		mPath4.quadTo(xPoint[2], yMax-YD*(mLevel+3), xPoint[3], yMid);
		mPath4.quadTo(xPoint[4], yMin+YD*(mLevel+3), xPoint[5], yMid);
		mPath4.quadTo(xPoint[6], yMax-YD*(mLevel+3), xPoint[7], yMid);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(backgroundColor);
		makePath();
		
		Paint p = mPaint;

		p.setStrokeWidth(4);
		canvas.drawPath(mPath1, p);
		
		p.setStrokeWidth(2);
		canvas.drawPath(mPath2, p);
		
		canvas.drawPath(mPath3, p);
		canvas.drawPath(mPath4, p);
	}
	
	public void setLevel(int level) {//0~7
		if (level<0) {
			level = 0;
		} else if (level>=WaveLevel.length) {
			level = WaveLevel.length-1;
		}
		
		mLevel = WaveLevel[level];
		invalidate();
	}

}
