package com.demo.javademo.dataStructure.tree;

import java.util.*;

public class TreeNodeDemo {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(
                new Integer[]{3, 2, 9, null, null, 10, null, null, 8, null, 4}));
        TreeNode treeNode = createBinaryTree(linkedList);
        //preOrderTravel(treeNode);
        //midOrderTravel(treeNode);
        postTravel(treeNode);
    }

    public static TreeNode createBinaryTree(LinkedList<Integer> linkedList) {
        if (linkedList == null || linkedList.isEmpty()) {
            return null;
        }

        Integer data = linkedList.removeFirst();
        if (data != null) {
            TreeNode treeNode = new TreeNode(data);
            treeNode.leftChild = createBinaryTree(linkedList);
            treeNode.rightChild = createBinaryTree(linkedList);
            return treeNode;
        }
        return null;
    }

    /**
     * 从当前节点开始遍历：（当入栈时访问节点内容，则为前序遍历）
     * 1. 若当前节点存在，就存入栈中，并访问左子树；
     * 2. 直到当前节点不存在，就出栈，并通过栈顶节点访问右子树；
     * 3. 不断重复12，直到当前节点不存在且栈空。
     */
    public static void preOrderTravel(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        while (treeNode != null || !stack.isEmpty()) {
            if (treeNode != null) {
                System.out.println(treeNode.data);
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            } else {
                treeNode = stack.pop();
                treeNode = treeNode.rightChild;
            }
        }
    }

    /**
     * 从当前节点开始遍历：（出栈时访问节点内容，则为中序遍历）
     * 1. 若当前节点存在，就存入栈中，并访问左子树；
     * 2. 直到当前节点不存在，就出栈，并通过栈顶节点访问右子树；
     * 3. 不断重复12，直到当前节点不存在且栈空。
     */
    public static void midOrderTravel(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        while (treeNode != null || !stack.isEmpty()) {
            if (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            } else {
                treeNode = stack.pop();
                System.out.println(treeNode.data);
                treeNode = treeNode.rightChild;
            }
        }
    }

    public static void postTravel(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        while (treeNode != null || !stack.isEmpty()) {
            if (treeNode != null) {
                stack.push(treeNode);
                map.put(treeNode.data, 1);
                treeNode = treeNode.leftChild;
            } else {
                treeNode = stack.peek();
                if (map.get(treeNode.data) == 2) {
                    treeNode = stack.pop();
                    System.out.println(treeNode.data);
                    treeNode = null;
                } else {
                    map.put(treeNode.data, 2);
                    treeNode = treeNode.rightChild;
                }
            }
        }
    }

    private static class TreeNode {
        TreeNode leftChild;
        TreeNode rightChild;
        private int data;

        public TreeNode(int data) {
            this.data = data;
        }
    }
}