package com.wx.demo.t9;
/** 
 * @author browserwang 
 * @version 2014年10月27日 上午10:53:01 
 * 类说明 
 */
public class TreeNode {

	public String nodeId;
	public String parentId;
	public String value;
	
	public TreeNode(String nodeId) {
		this.nodeId = nodeId;
	}
	
	public TreeNode(String nodeId, String parentId) {
		this.nodeId = nodeId;
		this.parentId = parentId;
	}
	
}
