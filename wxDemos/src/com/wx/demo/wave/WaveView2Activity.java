package com.wx.demo.wave;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.wx.demo.R;

/** 
 * @author browserwang 
 * @version 2014年8月21日 上午11:16:51 
 * 类说明 
 */
public class WaveView2Activity extends Activity implements OnSeekBarChangeListener, OnClickListener{

	private SeekBar seekBar, seekBar1, seekBar2, seekBar3, seekBar4, 
					seekBar5, seekBar6, seekBar7, seekBar8, seekBar9;
	private TextView textView, textView1, textView2, textView3, textView4,
					textView5, textView6, textView7, textView8, textView9;
	private Button resetButton1, resetButton2;
	private WaveView2 waveView;
	
	int[] xZooms = new int[] {100, 100, 100, 100, 100};
    int[] yZooms = new int[] {16, 16, 16, 16, 16};
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.waveview2_activity);

        seekBar = (SeekBar) findViewById(R.id.wa2_seek_bar);
        seekBar1 = (SeekBar) findViewById(R.id.wa2_seek_bar1);
        seekBar2 = (SeekBar) findViewById(R.id.wa2_seek_bar2);
        seekBar3 = (SeekBar) findViewById(R.id.wa2_seek_bar3);
        seekBar4 = (SeekBar) findViewById(R.id.wa2_seek_bar4);
        seekBar5 = (SeekBar) findViewById(R.id.wa2_seek_bar5);
        seekBar6 = (SeekBar) findViewById(R.id.wa2_seek_bar6);
        seekBar7 = (SeekBar) findViewById(R.id.wa2_seek_bar7);
        seekBar8 = (SeekBar) findViewById(R.id.wa2_seek_bar8);
        seekBar9 = (SeekBar) findViewById(R.id.wa2_seek_bar9);
        
        seekBar.setOnSeekBarChangeListener(this);
        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar3.setOnSeekBarChangeListener(this);
        seekBar4.setOnSeekBarChangeListener(this);
        seekBar5.setOnSeekBarChangeListener(this);
        seekBar6.setOnSeekBarChangeListener(this);
        seekBar7.setOnSeekBarChangeListener(this);
        seekBar8.setOnSeekBarChangeListener(this);
        seekBar9.setOnSeekBarChangeListener(this);
        
        textView = (TextView) findViewById(R.id.wa2_text);
        textView1 = (TextView) findViewById(R.id.wa2_text1);
        textView2 = (TextView) findViewById(R.id.wa2_text2);
        textView3 = (TextView) findViewById(R.id.wa2_text3);
        textView4 = (TextView) findViewById(R.id.wa2_text4);
        textView5 = (TextView) findViewById(R.id.wa2_text5);
        textView6 = (TextView) findViewById(R.id.wa2_text6);
        textView7 = (TextView) findViewById(R.id.wa2_text7);
        textView8 = (TextView) findViewById(R.id.wa2_text8);
        textView9 = (TextView) findViewById(R.id.wa2_text9);
        
        resetButton1 = (Button) findViewById(R.id.wa2_reset1);
        resetButton2 = (Button) findViewById(R.id.wa2_reset2);
        resetButton1.setOnClickListener(this);
        resetButton2.setOnClickListener(this);
        
        waveView = (WaveView2) findViewById(R.id.wave_view2);
        
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		switch (seekBar.getId()) {
		case R.id.wa2_seek_bar:
			xZooms[0] = progress;
			waveView.setXZoom(xZooms);
			textView.setText(""+progress);
			break;
		case R.id.wa2_seek_bar1:
			xZooms[1] = progress;
			waveView.setXZoom(xZooms);
			textView1.setText(""+progress);
			break;
		case R.id.wa2_seek_bar2:
			xZooms[2] = progress;
			waveView.setXZoom(xZooms);
			textView2.setText(""+progress);
			break;
		case R.id.wa2_seek_bar3:
			xZooms[3] = progress;
			waveView.setXZoom(xZooms);
			textView3.setText(""+progress);
			break;
		case R.id.wa2_seek_bar4:
			xZooms[4] = progress;
			waveView.setXZoom(xZooms);
			textView4.setText(""+progress);
			break;
		case R.id.wa2_seek_bar5:
			yZooms[0] = progress;
			waveView.setWaveLevel(yZooms);
			textView5.setText(""+progress);
			break;
		case R.id.wa2_seek_bar6:
			yZooms[1] = progress;
			waveView.setWaveLevel(yZooms);
			textView6.setText(""+progress);
			break;
		case R.id.wa2_seek_bar7:
			yZooms[2] = progress;
			waveView.setWaveLevel(yZooms);
			textView7.setText(""+progress);
			break;
		case R.id.wa2_seek_bar8:
			yZooms[3] = progress;
			waveView.setWaveLevel(yZooms);
			textView8.setText(""+progress);
			break;
		case R.id.wa2_seek_bar9:
			yZooms[4] = progress;
			waveView.setWaveLevel(yZooms);
			textView9.setText(""+progress);
			break;
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		
	}
	
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.wa2_reset1:
			/*for(int i=0;i<xZooms.length;i++) {
				xZooms[i] = 100;
			}*/
			
			seekBar.setProgress(100);
			seekBar1.setProgress(100);
			seekBar2.setProgress(100);
			seekBar3.setProgress(100);
			seekBar4.setProgress(100);
			break;
		case R.id.wa2_reset2:
			/*for(int i=0;i<yZooms.length;i++) {
				yZooms[i] = 16;
			}
			waveView.setWaveLevel(yZooms);*/
			seekBar5.setProgress(16);
			seekBar6.setProgress(16);
			seekBar7.setProgress(16);
			seekBar8.setProgress(16);
			seekBar9.setProgress(16);
			break;
		default:
			break;
		}
	}
}
