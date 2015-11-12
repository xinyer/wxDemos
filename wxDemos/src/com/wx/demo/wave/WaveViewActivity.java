package com.wx.demo.wave;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.wx.demo.R;

/** 
 * @author browserwang 
 * @version 2014年8月21日 上午11:16:51 
 * 类说明 
 */
public class WaveViewActivity extends Activity{

	private SeekBar seekBar, seekBar1, seekBar2;
	private TextView textView, textView1, textView2;
	private WaveView waveView;
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.waveview_activity);

        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        seekBar1 = (SeekBar) findViewById(R.id.seek_bar1);
        seekBar2 = (SeekBar) findViewById(R.id.seek_bar2);
        textView = (TextView) findViewById(R.id.wa_text);
        textView1 = (TextView) findViewById(R.id.wa_text1);
        textView2 = (TextView) findViewById(R.id.wa_text2);
        waveView = (WaveView) findViewById(R.id.wave_view);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                waveView.setProgress(progress);
                textView.setText("progress:" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                waveView.setXZoom(progress);
                textView1.setText("x-zoom:" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                waveView.setWaveLevel(progress);
                textView2.setText("y-zoom:" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
	}
}
