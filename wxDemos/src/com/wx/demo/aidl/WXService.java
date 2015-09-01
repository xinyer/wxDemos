package com.wx.demo.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/** 
 * @author browserwang 
 * @version 2014年9月9日 下午3:57:04 
 * 类说明 
 */
public class WXService extends Service{

	public class WXServiceImpl extends IWXService.Stub {

		@Override
		public String getValue() throws RemoteException {
			return "从AIDL服务获得的值";
		}
		
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return new WXServiceImpl();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Thread thr = new Thread(null, new ServiceWorker(), "BackgroundService");  
        thr.start(); 
	}

	private boolean mCanRun = true;
	class ServiceWorker implements Runnable {
		long counter = 0;

		@Override
		public void run() {
			// do background processing here.....
			while (mCanRun) {
				Log.d("wx", "" + counter);
				counter++;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void onDestroy() {
		mCanRun = false;
		super.onDestroy();
	}
}
