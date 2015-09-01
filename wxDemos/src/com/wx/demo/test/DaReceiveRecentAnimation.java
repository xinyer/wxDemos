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
public class DaReceiveRecentAnimation extends ImageView{
	
	XAnimationDrawable animation1, animation2, animation3;
	ImageView view = null;
	int repeatNum = 1;
	
	private onAnimationEndListener mListener;

	public DaReceiveRecentAnimation(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAnimation(context);
	}

	public void setOnAnimationEndListener(onAnimationEndListener listener) {
		mListener = listener;
	}
	
	private void initAnimation(Context context) {
		/*animation1 = new XAnimationDrawable((AnimationDrawable)context
				.getResources().getDrawable(R.drawable.da_receive_rc_anim_1)) {
			
			@Override
			public void onAnimationEnd() {
				//setVisibility(View.GONE);
				if (mListener!=null) {
					mListener.onAnimationEnd();
				}
			}
		};
		animation1.setOneShot(false);
		
		animation2 = new XAnimationDrawable((AnimationDrawable)context
				.getResources().getDrawable(R.drawable.da_receive_rc_anim_1)) {
			
			@Override
			public void onAnimationEnd() {
				//setVisibility(View.GONE);
				if (mListener!=null) {
					mListener.onAnimationEnd();
				}
			}
		};
		animation2.setOneShot(true);
		
		animation3 = new XAnimationDrawable((AnimationDrawable)context
				.getResources().getDrawable(R.drawable.da_receive_rc_anim_1)) {
			
			@Override
			public void onAnimationEnd() {
				//setVisibility(View.GONE);
				if (mListener!=null) {
					mListener.onAnimationEnd();
				}
			}
		};
		animation3.setOneShot(true);*/
	}
	
	public void startXAnimation(int repeatCount) {
		/*if (repeatCount<1) {
			repeatCount = 1;
		}
		
		if (repeatCount==1) {
			//setImageResource(R.anim.da_receive_rc_anim_1);
			setBackgroundDrawable(animation1);
			animation1.start();
		} else if (repeatCount==2) {
			//setImageResource(R.anim.da_receive_rc_anim_1);
			setBackgroundDrawable(animation1);
			animation2.start();
		} else if (repeatCount>=3) {
			setBackgroundDrawable(animation1);
			//setImageResource(R.anim.da_receive_rc_anim_1);
			animation3.start();
		}*/
	}
	
	public interface onAnimationEndListener {
		public void onAnimationEnd();
	}
}
