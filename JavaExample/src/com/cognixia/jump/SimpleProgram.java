package com.cognixia.jump;
import java.util.*;
public class SimpleProgram {
	public static void main(String[] args) {
		System.out.print("Enter your name     : ");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		String reverseName = new StringBuilder(name).reverse().toString();
		System.out.println("Using StringBuilder : " + reverseName);
		System.out.println("Created function    : " + reverseString(name.toCharArray()));
		sc.close();
		// Create the first tree
		TreeNode tree = new TreeNode(1,
				new TreeNode(2,
						new TreeNode(4,
								new TreeNode(8, null, null),
								new TreeNode(9, null, null)),
						new TreeNode(5,
								new TreeNode(10, null, null),
								new TreeNode(11, null, null))),
				new TreeNode(3,
						new TreeNode(6,
								new TreeNode(12, null, null),
								new TreeNode(13, null, null)),
						new TreeNode(7,
								new TreeNode(14, null, null),
								new TreeNode(15, null, null)))
		);
		// Create the second tree
		TreeNode tree2 = new TreeNode(1,
				new TreeNode(2, null, null),
				new TreeNode(3, null, null)
		);
		TreeNode tree3 = new TreeNode(1,
				new TreeNode(2,
						new TreeNode(4,
								new TreeNode(8, null, null),
								new TreeNode(9, null, null)),
						new TreeNode(5,
								new TreeNode(10, null, null),
								new TreeNode(11, null, null))),
				new TreeNode(3,
						new TreeNode(6,
								new TreeNode(12, null, null),
								new TreeNode(13, null, null)),
						new TreeNode(7,
								new TreeNode(14, null, null),
								new TreeNode(15, null, null)))
		);
		System.out.println("\nPrinting tree by level");
		printTreeByLevel(tree);
		System.out.println("\nPrinting tree2 by level");
		printTreeByLevel(tree2);
		System.out.println("\nPrinting tree3 by level");
		printTreeByLevel(tree3);
		System.out.println("\nAre tree and tree2 the same? " + (isSameTree(tree, tree2) ? "Yes" : "No"));
		System.out.println("Are tree and tree3 the same? " + (isSameTree(tree, tree3) ? "Yes" : "No"));

	}
	public static String reverseString(char[] originalString){
		int length = originalString.length;
		char[] reversedCharacters = new char[length];
		// Iterate over the original string in reverse order
		for (int i = length - 1, j = 0; i >= 0; i--, j++) {
			// Copy each character to the reversed string
			reversedCharacters[j] = originalString[i];
		}
		return new String(reversedCharacters);
	}
	public static void printTreeByLevel(TreeNode root) {
		// If the root is null, there's nothing to print
		if (root == null) {
			return;
		}

		// Queue to hold nodes at the current level
		Queue<TreeNode> levelNodes = new LinkedList<>();
		levelNodes.add(root); // Start with the root node

		// Loop while there are nodes to process
		while (!levelNodes.isEmpty()) {
			// Get the number of nodes at the current level
			int levelSize = levelNodes.size();

			// Process all nodes at the current level
			for (int i = 0; i < levelSize; i++) {
				TreeNode currentNode = levelNodes.poll();
				// Check if currentNode is null before trying to access its value
				if (currentNode != null) {
					// Print the value of the current node
					System.out.print(currentNode.val + " ");
					// Add left child to the queue if it exists
					if (currentNode.left != null) {
						levelNodes.add(currentNode.left);
					}
					// Add right child to the queue if it exists
					if (currentNode.right != null) {
						levelNodes.add(currentNode.right);
					}
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