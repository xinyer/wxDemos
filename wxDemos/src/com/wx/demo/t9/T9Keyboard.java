package com.wx.demo.t9;


import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.wx.demo.R;

/** 
 * @author browserwang 
 * @version 2014年10月28日 下午5:50:34 
 * 类说明 
 */
public class T9Keyboard extends Dialog implements OnClickListener{

	private Button key0, key1, key2, key3, key4, key5, key6, key7, key8, key9,
			keyDismiss, keyDelete;
	
	private Queue<String> keywords = new LinkedBlockingQueue<String>();
	
	public T9Keyboard(Context context) {
		super(context);
	}

	public T9Keyboard(Context context, int style){
		super(context, style);
		initUI();
		initKey();
	}
	
	private void initUI() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(new ColorDrawable());
		setContentView(R.layout.t9_keyboard);
		WindowManager.LayoutParams para = getWindow().getAttributes();
		para.width = WindowManager.LayoutParams.MATCH_PARENT;
		para.height = WindowManager.LayoutParams.WRAP_CONTENT;
		para.gravity = Gravity.BOTTOM;
		//getWindow().setWindowAnimations(R.style.find_friend_dialog_animation);
		
		setCanceledOnTouchOutside(true);
	}
	
	private void initKey() {
		key0 = (Button) findViewById(R.id.key0);
		key1 = (Button) findViewById(R.id.key1);
		key2 = (Button) findViewById(R.id.key2);
		key3 = (Button) findViewById(R.id.key3);
		key4 = (Button) findViewById(R.id.key4);
		key5 = (Button) findViewById(R.id.key5);
		key6 = (Button) findViewById(R.id.key6);
		key7 = (Button) findViewById(R.id.key7);
		key8 = (Button) findViewById(R.id.key8);
		key9 = (Button) findViewById(R.id.key9);
		keyDelete = (Button) findViewById(R.id.key_del);
		keyDismiss = (Button) findViewById(R.id.key_dismiss);
		key0.setOnClickListener(this);
		key1.setOnClickListener(this);
		key2.setOnClickListener(this);
		key3.setOnClickListener(this);
		key4.setOnClickListener(this);
		key5.setOnClickListener(this);
		key6.setOnClickListener(this);
		key7.setOnClickListener(this);
		key8.setOnClickListener(this);
		key9.setOnClickListener(this);
		keyDelete.setOnClickListener(this);
		keyDismiss.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.key0:
			addKeywords("0");
			break;
		case R.id.key1:
			addKeywords("1");
			break;
		case R.id.key2:
			addKeywords("2","a","b","c");
			break;
		case R.id.key3:
			addKeywords("3","d","e","f");
			break;
		case R.id.key4:
			addKeywords("4","g","h","i");
			break;
		case R.id.key5:
			addKeywords("5","j","k","l");
			break;
		case R.id.key6:
			addKeywords("6","m","n","o");
			break;
		case R.id.key7:
			addKeywords("7","p","q","r","s");
			break;
		case R.id.key8:
			addKeywords("8","t","u","v");
			break;
		case R.id.key9:
			addKeywords("6","w","x","y","z");
			break;
		case R.id.key_del:
			printKeywords();
			break;
		case R.id.key_dismiss:
			dismiss();
			break;
		default:
			break;
		}
	}
	
	private void addKeywords(String... newKeys) {
		int size = keywords.size();
		if (size == 0) {
			for(String s : newKeys) {
				keywords.add(s);
			}
		} else {
			while (size>0) {
				String top = keywords.poll();
				for (String s : newKeys) {
					keywords.add(top+s);
				}
				size--;
			}
		}
	}
	
	private void printKeywords() {
		System.out.println("-------------------------------------");
		for (String s : keywords) {
			System.out.print(s+",");
		}
		System.out.println("=====================================");
	}
}
