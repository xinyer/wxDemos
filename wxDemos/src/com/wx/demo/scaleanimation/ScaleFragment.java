package com.wx.demo.scaleanimation;

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
 * @version 2014年9月10日 上午10:58:50 
 * 类说明 
 */
public class ScaleFragment extends Fragment{

	Button button;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.scale_fragment, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		button = (Button) view.findViewById(R.id.sf_btn);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ScaleDialog dialog = new ScaleDialog(getActivity(), 0);
				dialog.show();
			}
		});
		super.onViewCreated(view, savedInstanceState);
	}
}
