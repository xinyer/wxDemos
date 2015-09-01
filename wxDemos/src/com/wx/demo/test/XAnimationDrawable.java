package com.wx.demo.test;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;

/**
 * @author browserwang
 * @version 2014年8月28日 下午4:30:11 
 * 可以监听动画结束的AnimationDrawable
 */

public abstract class XAnimationDrawable extends AnimationDrawable {
	Handler finishHandler; // 判断结束的Handler
	int repeatTime = 1;
	//AnimationDrawable mad;
	public XAnimationDrawable(AnimationDrawable ad) {
		// 这里得自己把每一帧加进去
		//mad = ad;
		for (int i = 0; i < ad.getNumberOfFrames(); i++) {
			this.addFrame(ad.getFrame(i), ad.getDuration(i));
		}
	}

	@Override
	public void start() {
		super.start();
		//mad.start();
		/**
		 * 首先用父类的start() 然后启动线程，来调用onAnimationEnd()
		 */
		finishHandler = new Handler();
		finishHandler.postDelayed(new Runnable() {
			public void run() {
				onAnimationEnd();
			}
		}, getTotalDuration());
	}

	/**
	 * 这个方法获得动画的持续时间（之后调用onAnimationEnd()）
	 */
	public int getTotalDuration() {
		int durationTime = 0;
		for (int i = 0; i < this.getNumberOfFrames(); i++) {
			durationTime += this.getDuration(i);
		}
		return durationTime*repeatTime;
	}
	
	public void setRepeatTime(int time) {
		this.repeatTime = time;
	}

	/**
	 * 结束时调用的方法，一定要实现
	 */
	public abstract void onAnimationEnd();
}
