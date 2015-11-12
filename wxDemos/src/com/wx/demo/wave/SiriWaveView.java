package com.wx.demo.wave;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/** 
 * @author browserwang 
 * @version 2014年8月19日 下午6:01:07 
 * 类说明 
 */
public class SiriWaveView extends View{
	
	private static final String TAG = "SiriWaveView";
	
	
	float phase;
    float frequency = 1.5f;
    
    float amplitude = 1.0f;
    float idleAmplitude = 0.01f;
    
    int numberOfWaves = 5;
    float phaseShift = -0.15f;
    float density = 1f;
    
    int waveColor = Color.parseColor("#12a86b");
    int backgroundColor = Color.parseColor("#f7f7f1");
    float primaryWaveLineWidth = 3.0f;
    float secondaryWaveLineWidth = 1.0f;
    
    Paint mPaint;
	float halfHeight, width, mid;

	public SiriWaveView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		mPaint = new Paint();
		mPaint.setColor(waveColor);
		mPaint.setDither(true);                    // set the dither to true
		mPaint.setStyle(Paint.Style.STROKE);       // set to STOKE
		mPaint.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
		mPaint.setStrokeCap(Paint.Cap.ROUND);      // set the paint cap to round too
		mPaint.setPathEffect(new CornerPathEffect(10) );   // set the path effect when they join.
		mPaint.setAntiAlias(true);
		
		//mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		halfHeight = getHeight() / 2.0f;
		width = getWidth();
		mid = width / 2.0f;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(backgroundColor);
		Paint p = mPaint;
		for(int i=0; i < numberOfWaves; i++) {
			
			if (i==0) {
				p.setStrokeWidth(primaryWaveLineWidth);
			} else {
				p.setStrokeWidth(secondaryWaveLineWidth);
			}
			
			final float maxAmplitude = halfHeight - 4.0f;
			
			// Progress is a value between 1.0 and -0.5, determined by the current wave idx, which is used to alter the wave's amplitude.
			float progress = 1.0f - (float)i / numberOfWaves;
			float normedAmplitude = (1.5f * progress - 0.5f) * amplitude;
			
	        float multiplier = Math.min(1.0f, (progress / 3.0f * 2.0f) + (1.0f / 3.0f));
	        //p.setAlpha((int)(multiplier*255));
	      
	        Path path = new Path();
 			for(float x = 0; x<width + density; x += density) {
				float scaling = -(float)Math.pow(1 / mid * (x - mid), 2) + 1;
				float y = scaling * maxAmplitude * normedAmplitude * (float)Math.sin(2 * Math.PI *(x / width) * frequency + phase) + halfHeight;
				
				if (x==0) {
					path.moveTo(x, y);
	            } else {
	            	path.lineTo(x, y);
	            }
			}
 			canvas.drawPath(path, p);
		}
	}
	
	public void updateWithLevel(float level) {
		phase += phaseShift;
		amplitude = Math.max(level, idleAmplitude);
		invalidate();
	}
	
}
