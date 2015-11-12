package com.wx.demo.test;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.wx.demo.R;
import com.wx.demo.progress.TimeProgress;
import com.wx.demo.svg.PathView;
import com.wx.demo.utils.DeviceUtil;


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

	TimeProgress timeProgress;

	int progress=0;

	PathView pathView;
	
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
		timeProgress = (TimeProgress) view.findViewById(R.id.time);

		pathView = (PathView) view.findViewById(R.id.pathView);

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
				//animation.startXAnimation(1);

//				progress+=10;
//				timeProgress.setProgress(progress);
//				if (progress>=100) progress=0;

//				timeProgress.setProgressGradually(20, 100, 3000);
//				DeviceUtil deviceUtil = new DeviceUtil(getActivity());
//				String s = DeviceUtil.getDmDensityDpi() + "-" + DeviceUtil.scale;
//				Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();

				mHandler.post(runnable);
			}
		});
	}



	Handler.Callback callback = new Handler.Callback() {
		@Override
		public boolean handleMessage(Message message) {
			pathView.setPercentage(mProgress);
			return false;
		}
	};

	Handler mHandler = new Handler(Looper.getMainLooper());

	float mProgress = 0f;
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			mProgress+=0.05;

			mHandler.postDelayed(this, 50);
			if (mProgress>1) mProgress=0f;
			pathView.setPercentage(mProgress);
		}
	};
}
