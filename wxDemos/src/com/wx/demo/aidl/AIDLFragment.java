package com.wx.demo.aidl;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wx.demo.R;

/** 
 * @author browserwang 
 * @version 2014年9月9日 下午4:02:49 
 * 类说明 
 */
public class AIDLFragment extends Fragment{

	Button button;
	TextView textView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.aidl_fragment, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		button = (Button) view.findViewById(R.id.aidl_btn);
		textView = (TextView) view.findViewById(R.id.aidl_tv);
		
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().bindService(new Intent("com.wx.demo.aidl.IWXService"), 
						serviceConnection, Context.BIND_AUTO_CREATE);
			}
		});
		
	}
	
	private IWXService iwxService = null;
	
	private ServiceConnection serviceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			iwxService = IWXService.Stub.asInterface(service);
			try {
				textView.setText(iwxService.getValue());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	};
}
