package com.wx.demo.fallswall;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.huewu.pla.lib.MultiColumnListView;
import com.wx.demo.R;

/** 
 * @author browserwang 
 * @version 2015年3月2日 下午4:59:54 
 * 类说明 
 */
public class FallsWallFragment extends Fragment{

	private PLAAdapter mAdapter;
	private FWAdapter mAdapter2;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.sample_frag, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		MultiColumnListView listView = (MultiColumnListView) view.findViewById(R.id.list);
        //mAdapter = new PLAAdapter(getActivity());
        //fillAdapter(mAdapter, 30);
        mAdapter2 = new FWAdapter(getActivity());
        mAdapter2.loadData();
		listView.setAdapter(mAdapter2);
	}
	
	
	private void fillAdapter(PLAAdapter adapter, int count) {
        for (int i = 0; i < count; ++i) {
            StringBuilder builder = new StringBuilder();
            for (int j = adapter.getCount(), max = (i * 1234) % 500; j < max; j++)
                builder.append(i).append(' ');
            adapter.add(builder.toString());
        }
    }
	
	 private static class PLAAdapter extends ArrayAdapter<String> {
	        public PLAAdapter(Context context) {
	            super(context, R.layout.sample_item, android.R.id.text1);
	        }
	    }
}
