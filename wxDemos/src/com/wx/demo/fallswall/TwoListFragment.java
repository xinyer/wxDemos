package com.wx.demo.fallswall;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.wx.demo.R;

/** 
 * @author browserwang 
 * @version 2015年3月3日 下午12:52:37 
 * 类说明 
 */
public class TwoListFragment extends Fragment implements OnTouchListener, OnScrollListener{

	ListView leftListView, rightListView;
	FWAdapter mAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.twolist_fragment, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		leftListView = (ListView) view.findViewById(R.id.left_list);
		rightListView = (ListView) view.findViewById(R.id.right_list);
		
		View header = LayoutInflater.from(getActivity()).inflate(R.layout.my_rank, null);
		rightListView.addHeaderView(header);
		
		mAdapter = new FWAdapter(getActivity());
        mAdapter.loadData();
		leftListView.setAdapter(mAdapter);
		rightListView.setAdapter(mAdapter);
		
		leftListView.setOnTouchListener(this);
		rightListView.setOnScrollListener(this);

	}
	
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		rightListView.dispatchTouchEvent(event);
		return false;
	}
	
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
//		System.out.println("firstVisibleItem:" + firstVisibleItem + "\t visibleItemCount:" + visibleItemCount + "\t totalItemCount:" + totalItemCount);
		if (firstVisibleItem==0) {
			View v=view.getChildAt(0);
			if(v != null)
		        leftListView.setSelectionFromTop(firstVisibleItem, v.getTop());
		} else {
			int h = getResources().getDimensionPixelOffset(R.dimen.my_rank_height);
			View v=view.getChildAt(0);
			if(v != null)
		        leftListView.setSelectionFromTop(firstVisibleItem-1<0?0:firstVisibleItem-1, v.getTop()-300);
		}
	    
	}
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		
	}
	
}
