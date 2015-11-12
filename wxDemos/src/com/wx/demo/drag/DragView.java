package com.wx.demo.drag;

import com.wx.demo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/** 
 * @author browserwang 
 * @version 2014年9月12日 下午6:05:57 
 * 类说明 
 */
public class DragView extends View{

	Bitmap mBitmap;
	Paint mPaint;
	
	float mTop, mLeft;
	float mWidth, mHeight;
	boolean isShow = false;
	
	public DragView(Context context) {
		super(context);
		
	}
	
	public DragView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		mPaint = new Paint();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mWidth = mBitmap.getWidth();
		mHeight = mBitmap.getHeight();
	}

	
	@Override
	protected void onDraw(Canvas canvas) {
		if (isShow) {
			canvas.drawBitmap(mBitmap, mLeft, mTop, mPaint);
		}
		super.onDraw(canvas);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		float x = event.getRawX();
		float y = event.getRawY();
		mTop = y-mHeight;
		mLeft = x-mWidth/2;
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			isShow = true;
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			isShow = false;
			break;
		}
		invalidate();
		return true;
	}
	
}
