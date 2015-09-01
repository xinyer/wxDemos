package com.wx.demo.t9;

import java.util.List;

/** 
 * @author browserwang 
 * @version 2014年10月27日 上午10:57:45 
 * 类说明 
 */
public class ManyNodeTree {

	public ManyTreeNode root;
	
	public ManyNodeTree() {
		root = new ManyTreeNode(new TreeNode("root"));
	}
	
	public ManyNodeTree createTree(List<TreeNode> treeNodes) {
		if (treeNodes == null || treeNodes.size() == 0) {
			return null;
		}
		
		ManyNodeTree tree = new ManyNodeTree();
		for (TreeNode treeNode : treeNodes) {
			if (treeNode.parentId.equals("root")) {
				tree.root.getChildList().add(new ManyTreeNode(treeNode));
			} else {
				addChild(tree.root, treeNode);
			}
		}
		return tree;
	}
	
	public void addChild(ManyTreeNode manyTreeNode, TreeNode child) {
		for (ManyTreeNode item : manyTreeNode.getChildList()) {
			if (item.data.nodeId.equals(child.parentId)) {
				item.getChildList().add(new ManyTreeNode(child));
			} else {
				if (item.getChildList()!=null && item.getChildList().size()>0) {
					addChild(item, child);
				}
			}
		}
	}
	
	public String iteratorTree(ManyTreeNode manyTreeNode) {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		
		if (manyTreeNode != null) {
			for (ManyTreeNode index : manyTreeNode.getChildList()) {
				builder.append(index.data.nodeId + ",");
				if (index.getChildList()!=null && index.getChildList().size()>0) {
					builder.append(iteratorTree(index));
				}
			}
		}
		builder.append("\n");
		return builder.toString();
	}
	
}
