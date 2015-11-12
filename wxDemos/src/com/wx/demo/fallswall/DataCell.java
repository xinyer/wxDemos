package com.wx.demo.fallswall;
/** 
 * @author browserwang 
 * @version 2015年3月3日 上午10:16:16 
 * 类说明 
 */
public class DataCell {

	public String mName;
	public int mRank;
	public String mImgUrl;
	public boolean isShowMyRank = false;
	
	public DataCell(String name, int rank, String imgUrl) {
		mName = name;
		mRank = rank;
		mImgUrl = imgUrl;
	}
}
