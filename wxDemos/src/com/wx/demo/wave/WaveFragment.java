package com.wx.demo.wave;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.wx.demo.R;

/** 
 * @author browserwang 
 * @version 2014年8月19日 上午10:39:45 
 * 类说明 
 */
public class WaveFragment extends Fragment implements OnClickListener{

	String TAG = "WaveFragment";
	Button siriButton, fillButton, customButton;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.wave_fragment, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		siriButton = (Button) view.findViewById(R.id.siri_btn);
		fillButton = (Button) view.findViewById(R.id.fill_btn);
		customButton = (Button) view.findViewById(R.id.custom_btn);
		
		siriButton.setOnClickListener(this);
		fillButton.setOnClickListener(this);
		customButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.siri_btn:
			toSiri();
			break;
		case R.id.fill_btn:
			toFill();
			break;
		case R.id.custom_btn:
			toCustom();

		default:
			break;
		}
	}
	
	public void toSiri() {
		Intent intent = new Intent(getActivity(), WaveActivity.class);
		startActivity(intent);
	}
	
	public void toFill() {
		Intent intent = new Intent(getActivity(), WaveViewActivity.class);
		startActivity(intent);
	}
	
	public void toCustom() {
		Intent intent = new Intent(getActivity(), WaveView2Activity.class);
		startActivity(intent);
	}
	
}
