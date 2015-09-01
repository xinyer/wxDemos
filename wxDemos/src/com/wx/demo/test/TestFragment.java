package com.wx.demo.test;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.wx.demo.R;

/** 
 * @author browserwang 
 * @version 2014年10月8日 下午3:57:50 
 * 类说明 
 */
public class TestFragment extends Fragment{

	//ImageView imageView;
	Button button;
	DaReceiveRecentAnimation animation;
	DaSendRecentAniamtion aniamtion2;
	
	//AnimationDrawable animationDrawable;
	//XAnimationDrawable xAnimationDrawable;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.test_fragment, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		//imageView = (ImageView) view.findViewById(R.id.test_iv);
		button = (Button) view.findViewById(R.id.test_btn);
		animation = (DaReceiveRecentAnimation) view.findViewById(R.id.test_anim);
		aniamtion2 = (DaSendRecentAniamtion) view.findViewById(R.id.test_anim2);
		//imageView.setBackgroundResource(R.anim.da_receive_rc_anim_1);
		//animationDrawable = (AnimationDrawable)imageView.getBackground();
		/*xAnimationDrawable = new XAnimationDrawable(animationDrawable) {
			
			@Override
			public void onAnimationEnd() {
				System.out.println("........");
			}
		};
		
		animationDrawable.setOneShot(true);
		*/
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				if (xAnimationDrawable.isRunning()) {
//					xAnimationDrawable.stop();
//				} else {
//					xAnimationDrawable.start();
//				}
				animation.startXAnimation(1);
				//aniamtion2.startAnimation();
			}
		});
	}
}
