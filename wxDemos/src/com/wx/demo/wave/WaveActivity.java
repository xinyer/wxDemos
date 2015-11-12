package com.wx.demo.wave;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.wx.demo.R;

/** 
 * @author browserwang 
 * @version 2014年8月20日 下午5:17:58 
 * 类说明 
 */
public class WaveActivity extends Activity{

	SiriWaveView siriWaveView;
	
	Timer timer;
	TimerTask timerTask;
	
	private ClipDrawable mMicphoneDrawable;
	private static final int[] VOICE_LEVELS = new int[] { 10000 * 44 / 310,
		10000 * 88 / 310, 10000 * 132 / 310, 10000 * 176 / 310,
		10000 * 220 / 310, 10000 * 264 / 310, 10000 };
	private SeekBar seekBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wave);
		siriWaveView = (SiriWaveView) findViewById(R.id.siri_wave_view);
		timer = new Timer();
		timerTask = new TimerTask() {
			@Override
			public void run() {
				Message msg = new Message();
				msg.what = 0;
				handler.sendMessage(msg);
			}
		};
		
		ImageView microIv = (ImageView) findViewById(R.id.microphone_iv);
		mMicphoneDrawable = (ClipDrawable)microIv.getDrawable();
		mMicphoneDrawable.setLevel(VOICE_LEVELS[0]);
		seekBar = (SeekBar) findViewById(R.id.wave_seekbar);
		seekBar.setMax(VOICE_LEVELS.length-1);
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				mMicphoneDrawable.setLevel(VOICE_LEVELS[progress]);
			}
		});
	}
	
	float i=0;
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				i+=0.005;
				if (i>1) {
					i=0;
				}
				siriWaveView.updateWithLevel(i);
			}
		}
	};
	
	@Override
	public void onResume() {
		super.onResume();
		timer.schedule(timerTask, 0, 50);
	}
}
