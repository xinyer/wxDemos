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
public class WaveView2 extends View {
    
    private Paint borderPaint = new Paint();
    private Paint wavePaint = new Paint();
    
    private final int default_wave_color = Color.parseColor("#12a86b");
    private final int default_border_color = Color.parseColor("#12a86b");
    private final int default_progress = 50;

    private int waveToTop;
    private int waveColor;
    private int borderColor;
    private int progress;

    
    /** offset of X */
    private final float offset = 0.5f;

    /* offset of Y */
    private float animOffset = 0.15f;
    
    /* wave animation */
    float xOffset;
    float offsetChange;
    
    /* numbers of wave */
    private int numOfWaves = 5;

    /* refresh thread */
    private RefreshProgressRunnable mRefreshProgressRunnable;

    public WaveView2(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.waveViewStyle);
    }

    public WaveView2(Context context, AttributeSet attrs, int defStyle) {
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
    float[] xOffsets = new float[]{0, 3.5f, 3f, 2.5f, 2f};
    int[] xZooms = new int[] {100, 100, 100, 100, 100};
    int[] yZooms = new int[] {16, 16, 16, 16, 16};
    
    private void updatePath(Canvas canvas) {
    	for (int i=0; i<numOfWaves; i++) {
    		
    		if (i==0) {
				wavePaint.setAlpha(0x66);
				borderPaint.setStrokeWidth(1.5f);
				
			} else {
				wavePaint.setAlpha(0x33);
				if (i==1) {
					borderPaint.setStrokeWidth(1.0f);
				} else {
					borderPaint.setStrokeWidth(0.5f);
				}
			}
    		
    		//init path
    		tmpPath.reset();
    		tmpPath.moveTo(getLeft(), getHeight());
    		for (float j = 0; xZooms[i] * j <= getRight() + xZooms[i] * offset; j += offset) {
                tmpPath.lineTo((xZooms[i] * j), (float) (yZooms[i] * Math.cos(j + xOffsets[i]+offsetChange)) + waveToTop);
            }
    		tmpPath.lineTo(getRight(), getHeight());
    		canvas.drawPath(tmpPath, wavePaint);
    		canvas.drawPath(tmpPath, borderPaint);
    		
    	
    	}
    }

    public void setProgress(int progress) {
        this.progress = progress > 100 ? 100 : progress;
    }
    
    public void setWaveLevel(int[] yZoom) {
    	//this.yZoom = level;
    	if (yZoom.length!=numOfWaves) {
			return;
		}
    	for (int i=0;i<numOfWaves;i++) {
    		yZooms[i] = yZoom[i];
    	}
    }
    
    public void setXZoom(int[] xZoom) {
    	//this.xZoom = x;
    	if (xZoom.length!=numOfWaves) {
			return;
		}
    	for (int i=0;i<numOfWaves;i++) {
    		xZooms[i] = xZoom[i];
    	}
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
            synchronized (WaveView2.this) {
                waveToTop = (int) (getHeight() * (1f - progress / 100f));
                getWaveOffset();
                invalidate();
                getHandler().postDelayed(this, 20);
            }
        }
    }

}
