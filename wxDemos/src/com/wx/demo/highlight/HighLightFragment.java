package com.wx.demo.highlight;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wx.demo.R;

/** 
 * @author browserwang 
 * @version 2014年12月11日 下午4:16:04 
 * 类说明 
 */
public class HighLightFragment extends Fragment{

	TextView textView;
	EditText editText;
	Button button;
	
	int[] namePinyinLen;
	String name = "王jk鑫";
	String namePinyinQuan;
	String namePinyinQuanNum = "926455946";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.highlight_fragment, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		initName();
		
		textView = (TextView) view.findViewById(R.id.text);
		editText = (EditText) view.findViewById(R.id.edittext);
		button = (Button) view.findViewById(R.id.button);
		
		textView.setText(name +"[" + namePinyinQuan + "]");
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				int index = namePinyinQuanNum.indexOf(editText.getText().toString());
				boolean isMatch = isMatchQuanPin(namePinyinLen, index);
				textView.append("index:" + index + "\t isMatch:" + isMatch);
			}
		});
		
	}
	
	private void initName() {
		namePinyinQuan = ChnToSpell.MakeSpellCode(name, ChnToSpell.TRANS_MODE_QUAN_PIN);
		namePinyinLen = new int[name.length()];
        for (int i=0;i<name.length();i++) {
            String pinyin = ChnToSpell.MakeSpellCode(String.valueOf(name.charAt(i)), ChnToSpell.TRANS_MODE_QUAN_PIN);
            namePinyinLen[i] = pinyin.length();
        }
	}
	
	public boolean isMatchQuanPin(int[] pinyinLen, int index) {
        if (index==0) return true;
        int[] newLen = new int[pinyinLen.length];
        newLen[0] = 0;
        for (int i=1;i<newLen.length;i++) {
            newLen[i] = newLen[i-1] + pinyinLen[i-1];
            if (index==newLen[i-1]){
                return true;
            }
        }
        return false;
    }
	
}
