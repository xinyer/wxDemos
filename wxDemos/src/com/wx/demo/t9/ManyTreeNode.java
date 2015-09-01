package com.wx.demo.t9;

import java.util.ArrayList;
import java.util.List;

/** 
 * @author browserwang 
 * @version 2014年10月27日 上午10:55:50 
 * 类说明 
 */
public class ManyTreeNode {

	public TreeNode data;
	private List<ManyTreeNode> childList;
	
	public ManyTreeNode(TreeNode data) {
		this.data = data;
		childList = new ArrayList<ManyTreeNode>();
	}
	
	public List<ManyTreeNode> getChildList() {
		return childList;
	}
	
	public void addChild(ManyTreeNode node) {
		this.childList.add(node);
	}
	
}
