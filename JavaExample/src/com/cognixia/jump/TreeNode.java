package com.cognixia.jump;

public class TreeNode {
	// Node class for the binary tree
	public int val; // Value of the node
	public com.cognixia.jump.TreeNode left; // Left child of the node
	public com.cognixia.jump.TreeNode right; // Right child of the node

	// Constructor to initialize the node
	public TreeNode(int val, com.cognixia.jump.TreeNode left, com.cognixia.jump.TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}