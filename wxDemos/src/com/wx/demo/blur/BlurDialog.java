package com.wx.demo.blur;


import com.wx.demo.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/** 
 * @author browserwang 
 * @version 2014年8月26日 下午2:43:33 
 * 类说明 
 */
public class BlurDialog extends Dialog{

	public BlurDialog(Context context, int theme) {
		super(context, theme);
		initDialog();
	}

	private void initDialog() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_blur);
		setCanceledOnTouchOutside(false);
		getWindow().setBackgroundDrawable(new ColorDrawable());
		WindowManager.LayoutParams para = getWindow().getAttributes();
		para.x = 0;
		para.y = 0;
		para.width = WindowManager.LayoutParams.MATCH_PARENT;
		para.height = WindowManager.LayoutParams.MATCH_PARENT;
		para.gravity = Gravity.TOP | Gravity.LEFT;
	}
	
	public void setBackground(Bitmap bitmap) {
		getWindow().getDecorView().setBackgroundDrawable(new BitmapDrawable(bitmap));
	}
	
	@Override
	public void onContentChanged() {
		// TODO Auto-generated method stub
		System.out.println("onContentChanged");
		super.onContentChanged();
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		System.out.println("onWindowFocusChanged");
		super.onWindowFocusChanged(hasFocus);
	}
	
}
