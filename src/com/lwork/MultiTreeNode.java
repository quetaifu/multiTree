/*
 * Copyright Walker Studio
 * All Rights Reserved.
 * 
 * 文件名称： MultiTreeNode.java
 */
package com.lwork;

import java.util.ArrayList;
import java.util.List;

/**
 * 多叉树节点
 */
public class MultiTreeNode {
	/** 树节点*/
	private TreeNode data;
	/** 子树集合*/
	private List<MultiTreeNode> childList;
	
	/**
	 * 构造函数
	 * 
	 * @param data 树节点
	 */
	public MultiTreeNode(TreeNode data)
	{
		this.data = data;
		this.childList = new ArrayList<MultiTreeNode>();
	}
	
	/**
	 * 构造函数
	 * 
	 * @param data 树节点
	 * @param childList 子树集合
	 */
	public MultiTreeNode(TreeNode data, List<MultiTreeNode> childList)
	{
		this.data = data;
		this.childList = childList;
	}

	public TreeNode getData() {
		return data;
	}

	public void setData(TreeNode data) {
		this.data = data;
	}

	public List<MultiTreeNode> getChildList() {
		return childList;
	}

	public void setChildList(List<MultiTreeNode> childList) {
		this.childList = childList;
	}

}
