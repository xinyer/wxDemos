package com.wx.demo.test;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.wx.demo.R;

/** 
 * @author browserwang 
 * @version 2014年9月29日 下午5:00:17 
 * 类说明 通话列表收到YO时动画
 */
public class DaReceiveRecentAnimation2 extends ImageView{
	
	XAnimationDrawable animation1, animation2, animation3;
	ImageView view = null;
	int repeatNum = 1;
	
	private onAnimationEndListener mListener;

	
	public DaReceiveRecentAnimation2(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAnimation(context);
	}

	public void setOnAnimationEndListener(onAnimationEndListener listener) {
		mListener = listener;
	}
	
	private void initAnimation(Context context) {
		animation1 = new XAnimationDrawable((AnimationDrawable)context
				.getResources().getDrawable(R.anim.da_receive_rc_anim_1)) {
			
			@Override
			public void onAnimationEnd() {
				
			}
		};
		animation1.setOneShot(true);
		
	}
	
	public void startXAnimation(int repeatCount) {
		setBackgroundDrawable(animation1);
		animation1.start();
	}
	
	public interface onAnimationEndListener {
		public void onAnimationEnd();
	}
}
