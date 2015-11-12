package com.wx.demo.animation;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.wx.demo.R;

/** 
 * @author browserwang 
 * @version 2014年8月29日 下午3:38:34 
 * 类说明 
 */
public class AnimationFragment extends Fragment{

	ImageView iv;
	Button downButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.anim_fragment, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		iv = (ImageView) view.findViewById(R.id.af_iv);
		downButton = (Button) view.findViewById(R.id.af_btn);
		downButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				exitAnim();
			}
		});
		enterAnim();
	}
	
	private void enterAnim() {
		Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.ptt_dialog_head_up);
		iv.startAnimation(animation);
	}
	
	private void exitAnim() {
		Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.ptt_dialog_head_down);
		iv.startAnimation(animation);
	}
	
}
