package com.wx.demo.notify;

import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.wx.demo.R;

/** 
 * @author browserwang 
 * @version 2014年9月25日 下午3:03:46 
 * 类说明 
 */
public class NotifyFragment extends Fragment implements OnClickListener{

	Button button1, button2;
	
	NotificationManager notificationManager;
	Notification notification;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.notify_fragment, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		button1 = (Button) view.findViewById(R.id.nf_btn1);
		button2 = (Button) view.findViewById(R.id.nf_btn2);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		
		init();
		super.onViewCreated(view, savedInstanceState);
	}
	
	private void init() {
		notificationManager = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
		notification = new Notification();
		notification.icon = R.drawable.ic_launcher;
		notification.tickerText = "Hello， notification!";
	}
	
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.nf_btn1) {
			notificationManager.notify(0, notification);
		} else if (v.getId() == R.id.nf_btn2) {
			
		}
	}
}
