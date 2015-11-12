package com.wx.demo.t9;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wx.demo.R;

/** 
 * @author browserwang 
 * @version 2014年10月24日 下午5:53:23 
 * 类说明 
 */
public class T9Fragment extends Fragment implements OnClickListener{

	Button t9Button;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.t9_fragment, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initUI(view);
	}
	
	private void initUI(View view) {
		t9Button = (Button) view.findViewById(R.id.t9_btn);
		t9Button.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		showT9Keyboard();
	}
	
 	private void showT9Keyboard() {
 		T9Keyboard keyboard = new T9Keyboard(getActivity(),0);
 		keyboard.show();
 	}
}
