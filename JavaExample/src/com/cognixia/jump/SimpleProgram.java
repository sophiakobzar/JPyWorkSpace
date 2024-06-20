package com.cognixia.jump;
import java.util.*;


public class SimpleProgram {
	public static void main(String[] args) {
		// scanner
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter your name  : ");
		String name = sc.nextLine();
		String reverseName = new StringBuilder(name).reverse().toString();
		System.out.println("Using built in   : " + reverseName);
		System.out.println("Created function : " + reverseString(name.toCharArray()));
		sc.close();
		// Create the first tree
		TreeNode tree = new TreeNode(1,
				new TreeNode(2, null, null),
				null
		);
		// Create the second tree
		TreeNode tree2 = new TreeNode(1,
				new TreeNode(2, null, null),
				null
		);
		System.out.println("Printing tree by level");
		printTreeByLevel(tree);
		System.out.println("\nAre they same? " + SimpleProgram.isSameTree(tree, tree2));

	}
	public static String reverseString(char[] str)
	{
		char[] reCharacters = new char[str.length];
		int index = 0;
		for(int i = (str.length-1); i >=0; i--)
		{
			reCharacters[index] = str[i];
			index++;
		}
		return new String(reCharacters);
	}

	public static void printTreeByLevel(TreeNode root)
	{
		// If the root is null, there's nothing to print
		if (root == null) {
			return;
		}
		// Queue to hold nodes at the current level
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root); // Start with the root node
		// Loop while there are nodes to process
		while (!queue.isEmpty()) {
			// Process all nodes at the current level
			for (int i = 0; i < queue.size(); i++) {
				TreeNode currentNode = queue.poll();
				// Print the value of the current node
				System.out.print(currentNode.val + " ");
				// Add left child to the queue if it exists
				if (currentNode.left != null) {
					queue.add(currentNode.left);
				}
				// Add right child to the queue if it exists
				if (currentNode.right != null) {
					queue.add(currentNode.right);
				}
			}
			// Move to the next line after printing all nodes at the current level
			System.out.println();
		}
	}
	public static boolean isSameTree(TreeNode p, TreeNode q) {
		// Both trees are null, so they are the same
		if (p == null && q == null) return true;
		// One of the trees is null, and the other isn't, so they aren't the same
		if (p == null || q == null) return false;
		// The values of the nodes are different, so the trees aren't the same
		if (p.val != q.val) return false;
		// Recursively check the left and right subtrees
		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}
}