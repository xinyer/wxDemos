package com.wx.demo.wave;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.wx.demo.R;

/**
 * 
 * Title: WaveView.java
 * @desc PTT录制音量效果
 * @author browserwang
 * @date 2014年8月20日
 *
 */
public class WaveView extends View {
    
    private Paint borderPaint = new Paint();
    private Paint wavePaint = new Paint();
    
    private final int default_wave_color = Color.parseColor("#12a86b");
    private final int default_border_color = Color.parseColor("#12a86b");
    private final int default_progress = 50;

    private int waveToTop;
    private int waveColor;
    private int borderColor;
    private int progress;

    /* wave length */
    private int xZoom = 100;

    /* wave crest */
    private int yZoom = 16;
    
    /** offset of X */
    private final float offset = 0.5f;
    private final float max_right = xZoom * offset;

    /* offset of Y */
    private float animOffset = 0.15f;
    
    /* wave animation */
    float xOffset;
    float offsetChange;
    
    /* numbers of wave */
    private int numOfWaves = 5;

    /* refresh thread */
    private RefreshProgressRunnable mRefreshProgressRunnable;

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.waveViewStyle);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WaveView, defStyle, 0);
        waveColor = attributes.getColor(R.styleable.WaveView_wave_color, default_wave_color);
        borderColor = attributes.getColor(R.styleable.WaveView_border_color, default_border_color);
        progress = attributes.getInt(R.styleable.WaveView_progress, default_progress);

        setProgress(progress);
        initializePainters();
    }
    
   private void initializePainters() {
    	
    	wavePaint.setColor(waveColor);
    	wavePaint.setStyle(Paint.Style.FILL);
    	wavePaint.setAntiAlias(true);
    	wavePaint.setDither(true);
    	wavePaint.setStrokeJoin(Paint.Join.ROUND);
    	wavePaint.setStrokeCap(Paint.Cap.ROUND);
//    	wavePaint.setPathEffect(new CornerPathEffect(10)); 
        
        borderPaint.setColor(borderColor);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setAntiAlias(true);
        borderPaint.setDither(true);
        borderPaint.setStrokeJoin(Paint.Join.ROUND);
        borderPaint.setStrokeCap(Paint.Cap.ROUND);
        borderPaint.setPathEffect(new CornerPathEffect(10)); 
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
    }

    private int measure(int measureSpec, boolean isWidth) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int padding = isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = isWidth ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight();
            result += padding;
            if (mode == MeasureSpec.AT_MOST) {
                if (isWidth) {
                    result = Math.max(result, size);
                } else {
                    result = Math.min(result, size);
                }
            }
        }
        return result;
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        updatePath(canvas);
    }

    Path tmpPath = new Path();
    float tmpYZoom, tmpXZoom;
    
    private void updatePath(Canvas canvas) {
    	for (int i=0; i<numOfWaves; i++) {
    		//init paint
    		if (i==0) {
				xOffset = 0;//0
				wavePaint.setAlpha(0x66);
				borderPaint.setStrokeWidth(1.5f);
				tmpYZoom = yZoom;
			} else {
				xOffset = 4f-i*0.5f;//4.0, 3.5, 3.0, 2.5
				wavePaint.setAlpha(0x33);
				if (i==1) {
					borderPaint.setStrokeWidth(1.0f);
					tmpYZoom = yZoom-5<0?0:yZoom-5;
				} else {
					borderPaint.setStrokeWidth(0.5f);
					tmpYZoom = yZoom-7<0?0:yZoom-7;
				}
			}
    		tmpXZoom = xZoom + tmpYZoom;
    		//init path
    		tmpPath.reset();
    		tmpPath.moveTo(getLeft(), getHeight());
    		for (float j = 0; xZoom * j <= getRight() + max_right; j += offset) {
                tmpPath.lineTo((xZoom * j), (float) (tmpYZoom * Math.cos(j + xOffset+offsetChange)) + waveToTop);
            }
    		tmpPath.lineTo(getRight(), getHeight());
    		canvas.drawPath(tmpPath, wavePaint);
    		canvas.drawPath(tmpPath, borderPaint);
    		
    		tmpXZoom = 0;
    		tmpYZoom = 0;
    	}
    }

    public void setProgress(int progress) {
        this.progress = progress > 100 ? 100 : progress;
    }
    
    public void setWaveLevel(int level) {
    	this.yZoom = level;
    }
    
    public void setXZoom(int x) {
    	this.xZoom = x;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mRefreshProgressRunnable = new RefreshProgressRunnable();
        getHandler().post(mRefreshProgressRunnable);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getHandler().removeCallbacks(mRefreshProgressRunnable);
    }

    private void getWaveOffset(){
        if(offsetChange > Float.MAX_VALUE - 100){
            offsetChange = 0;
        }else{
            offsetChange += animOffset;
        }
    }

    private class RefreshProgressRunnable implements Runnable {
        public void run() {
            synchronized (WaveView.this) {
                waveToTop = (int) (getHeight() * (1f - progress / 100f));
                getWaveOffset();
                invalidate();
                getHandler().postDelayed(this, 20);
            }
        }
    }

}
