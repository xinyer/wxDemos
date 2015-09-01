package com.wx.demo.blur;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.wx.demo.R;

/**
 * @author browserwang
 * @version 2014年8月26日 上午11:03:59 类说明
 */
public class BlurFragment extends Fragment implements OnSeekBarChangeListener {

	ViewGroup mRoot;
	ImageView mImageView;
	SeekBar mSeekBar;
	Button mButton;
	TextView mTextView;

	Bitmap bgBitmap;
	BlurDialog dialog;
	int mProgress = 20;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.blur_fragment, null);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		dialog = new BlurDialog(getActivity(), 0);

		mRoot = (ViewGroup) view.findViewById(R.id.bf_root);
		mImageView = (ImageView) view.findViewById(R.id.bf_iv);
		mSeekBar = (SeekBar) view.findViewById(R.id.bf_seekbar);
		mSeekBar.setOnSeekBarChangeListener(this);
		mButton = (Button) view.findViewById(R.id.bf_btn);
		mTextView = (TextView) view.findViewById(R.id.bf_tv);
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				 bgBitmap = takeScreenShot(getActivity());
				 savePic(bgBitmap, "sdcard/xx.png");

				MyTask task = new MyTask();
				task.execute(mProgress);
			}
		});

	}

	public Bitmap loadBitmapFromView(View v, int width, int height) {
		Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(b);
		v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
		v.draw(c);
		return b;
	}

	// 获取指定Activity的截屏，保存到png文件
	private Bitmap takeScreenShot(Activity activity) {
		// View是你需要截图的View
		// View是你需要截图的View
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap b1 = view.getDrawingCache();

		// 获取状态栏高度
		Rect frame = new Rect();
		getActivity().getWindow().getDecorView()
				.getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		System.out.println(statusBarHeight);

		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;

		System.out.println(screenWidth + "--" + screenHeight);
		// 去掉标题栏
		// Bitmap b = Bitmap.createBitmap(b1, 0, 25, 320, 455);
		Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, screenWidth,
				screenHeight - statusBarHeight);
		view.destroyDrawingCache();
		return b;
	}

	// 保存到sdcard
	private void savePic(Bitmap b, String strFileName) {
		File f = new File(strFileName);

		FileOutputStream fos = null;
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			fos = new FileOutputStream(strFileName);
			if (null != fos) {
				b.compress(Bitmap.CompressFormat.PNG, 90, fos);
				fos.flush();
				fos.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		mProgress = progress;
		mTextView.setText("" + mProgress);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	}

	class MyTask extends AsyncTask<Integer, Integer, Void> {

		@Override
		protected Void doInBackground(Integer... params) {
			bgBitmap = takeScreenShot(getActivity());
			StackBlur.fastblur(bgBitmap, params[0]);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			dialog.setBackground(bgBitmap);
			dialog.show();
		}
	}
	
	@Override
	public void onPause() {
		System.out.println("BlurFragment onPause");
	
		super.onPause();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		System.out.println("BlurFragment onResume");		
		super.onResume();
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		System.out.println("BlurFragment onAttach");
		super.onAttach(activity);
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		System.out.println("BlurFragment onStart");
		super.onStart();
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		System.out.println("BlurFragment onStop");
		super.onStop();
	}
	
}
