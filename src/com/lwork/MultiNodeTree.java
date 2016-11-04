/*
 * Copyright Walker Studio
 * All Rights Reserved.
 * 
 * 文件名称： MultiNodeTree.java
 */
package com.lwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 多叉树生成、遍历工具
 *
 * @author Walker
 * @version 1.0.0.0
 */
public class MultiNodeTree {
    /**
     * 树根
     */
    private MultiTreeNode root;

    /**
     * 构造函数
     */
    public MultiNodeTree() {
        root = new MultiTreeNode(new TreeNode("root"));
    }

    /**
     * 生成一颗多叉树，根节点为root
     *
     * @param treeNodes 生成多叉树的节点集合
     * @return MultiNodeTree
     */
    public MultiNodeTree createTree(List<TreeNode> treeNodes) {
        if (treeNodes == null || treeNodes.size() < 0)
            return null;

        MultiNodeTree MultiNodeTree = new MultiNodeTree();

        //将所有节点添加到多叉树中
        for (TreeNode treeNode : treeNodes) {
            if (treeNode.getParentId().equals("root")) {
                //向根添加一个节点
                MultiNodeTree.getRoot().getChildList().add(new MultiTreeNode(treeNode));
            } else {
                addChild(MultiNodeTree.getRoot(), treeNode);
            }
        }

        return MultiNodeTree;
    }

    /**
     * 向指定多叉树节点添加子节点
     *
     * @param multiTreeNode 多叉树节点
     * @param child         节点
     */
    public void addChild(MultiTreeNode multiTreeNode, TreeNode child) {
        Stack<MultiTreeNode> stack = new Stack<>();
        stack.push(multiTreeNode);

        while (!stack.empty()) {
            MultiTreeNode item = stack.pop();

            if (item.getData().getNodeId().equals(child.getParentId())) {
                //找到对应的父亲
                item.getChildList().add(new MultiTreeNode(child));
                return;
            }

            for (int index = 0; index < item.getChildList().size(); ++index) {
                stack.push(item.getChildList().get(index));
            }

        }
    }

//    递归版本
//    public void addChild(MultiTreeNode MultiTreeNode, TreeNode child) {
//        for (MultiTreeNode item : MultiTreeNode.getChildList()) {
//            if (item.getData().getNodeId().equals(child.getParentId())) {
//                //找到对应的父亲
//                item.getChildList().add(new MultiTreeNode(child));
//                break;
//            } else {
//                if (item.getChildList() != null && item.getChildList().size() > 0) {
//                    addChild(item, child);
//                }
//            }
//        }
//    }

    /**
     * 遍历多叉树
     *
     * @param multiTreeNode 多叉树节点
     * @return
     */
    public List<String> iteratorTree(MultiTreeNode multiTreeNode) {
        Stack<MultiTreeNode> stack = new Stack<>();
        stack.push(multiTreeNode);
        List<String> result = new ArrayList<>();

        while (!stack.empty()) {
            MultiTreeNode topItem = stack.pop();
            result.add(topItem.getData().getNodeId());

            int size = topItem.getChildList().size();
            for (int index = size-1; index >= 0; --index) {
                stack.push(topItem.getChildList().get(index));
            }
        }

        return result;
    }


//    递归版本
//    public String iteratorTree(MultiTreeNode MultiTreeNode) {
//        StringBuilder buffer = new StringBuilder();
//        buffer.append("\n");
//
//        if (MultiTreeNode != null) {
//            for (MultiTreeNode index : MultiTreeNode.getChildList()) {
//                buffer.append(index.getData().getNodeId() + ",");
//
//                if (index.getChildList() != null && index.getChildList().size() > 0) {
//                    buffer.append(iteratorTree(index));
//                }
//            }
//        }
//
//        buffer.append("\n");
//
//        return buffer.toString();
//    }

    public MultiTreeNode getRoot() {
        return root;
    }

    public void setRoot(MultiTreeNode root) {
        this.root = root;
    }

    public static void main(String[] args) {
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        treeNodes.add(new TreeNode("系统权限管理", "root"));
        treeNodes.add(new TreeNode("用户管理", "系统权限管理"));
        treeNodes.add(new TreeNode("角色管理", "系统权限管理"));
        treeNodes.add(new TreeNode("组管理", "系统权限管理"));
        treeNodes.add(new TreeNode("用户菜单管理", "系统权限管理"));
        treeNodes.add(new TreeNode("角色菜单管理", "系统权限管理"));
        treeNodes.add(new TreeNode("用户权限管理", "系统权限管理"));

        treeNodes.add(new TreeNode("站内信", "root"));
        treeNodes.add(new TreeNode("写信", "站内信"));
        treeNodes.add(new TreeNode("收信", "站内信"));
        treeNodes.add(new TreeNode("草稿", "站内信"));

        treeNodes.add(new TreeNode("CEO", "root"));
        treeNodes.add(new TreeNode("销售总监1", "CEO"));
        treeNodes.add(new TreeNode("销售总监2", "CEO"));
        treeNodes.add(new TreeNode("销售总监3", "CEO"));
        treeNodes.add(new TreeNode("销售经理1", "销售总监2"));
        treeNodes.add(new TreeNode("销售1", "销售经理1"));
        treeNodes.add(new TreeNode("代理1", "销售1"));

        MultiNodeTree tree = new MultiNodeTree();
        List<String> treeItems = tree.iteratorTree(tree.createTree(treeNodes).getRoot());

        for (String item : treeItems) {
            System.out.println(item);
        }
    }
}
