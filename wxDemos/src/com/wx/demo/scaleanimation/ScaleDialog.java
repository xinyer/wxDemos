package com.wx.demo.scaleanimation;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wx.demo.R;

/** 
 * @author browserwang 
 * @version 2014年9月10日 上午11:01:43 
 * 类说明 
 */
public class ScaleDialog extends Dialog{

	public ScaleDialog(Context context, int theme) {
		super(context, theme);
		initDialog();
	}

	private void initDialog() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.scale_dialog);
		setCanceledOnTouchOutside(false);
		getWindow().setBackgroundDrawable(new ColorDrawable());
		WindowManager.LayoutParams para = getWindow().getAttributes();
		para.x = 0;
		para.y = 0;
		para.width = WindowManager.LayoutParams.MATCH_PARENT;
		para.height = WindowManager.LayoutParams.MATCH_PARENT;
		para.gravity = Gravity.TOP | Gravity.LEFT;
	}
}
