package com.wx.demo.test;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.wx.demo.R;

/** 
 * @author browserwang 
 * @version 2014年9月29日 下午3:02:21 
 * 类说明  通话列表YO快捷回复的动画
 */
public class DaSendRecentAniamtion extends View{

	XAnimationDrawable animation;
	
	public DaSendRecentAniamtion(Context context, AttributeSet attrs) {
		super(context, attrs);
		animation = new XAnimationDrawable((AnimationDrawable) context
				.getResources().getDrawable(R.anim.da_send_rc_anim)) {
			@Override
			public void onAnimationEnd() {
			}
		};
		
		animation.setOneShot(true);
		setBackgroundDrawable(animation);
	}

	public void startAnimation() {
		animation.start();
	}
	
}
