package com.cognixia.jump;
import java.util.*;

class TreeNode {
    // Node class for the binary tree
    public int val; // Value of the node
    public TreeNode left; // Left child of the node
    public TreeNode right; // Right child of the node

    // Constructor to initialize the node
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class HelloWorld {
	public static void main(String[] args) {
        // Creating a sample tree
        TreeNode tree = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4, null, null),
                        new TreeNode(5, null, null)),
                new TreeNode(3,
                        new TreeNode(6, null, null),
                        new TreeNode(7, null, null))
        );

        TreeNode tree2 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4, null, null),
                        new TreeNode(5, null, null)),
                new TreeNode(3,
                        new TreeNode(6, null, null),
                        new TreeNode(7, null, null))
        );
        // Calling the function to print the tree by level
        System.out.println("Printing tree by level");
        printTreeByLevel(tree);
        System.out.println("\nPrinting tree2 by level");
        printTreeByLevel(tree2);
        System.out.println("\nAre they same? " + HelloWorld.leafSimilar(tree, tree2));
        TreeNode tree3 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(6, null, null),
                        new TreeNode(7, null, null)),
                new TreeNode(3,
                        new TreeNode(8, null, null),
                        new TreeNode(9, null, null))
        );

        TreeNode tree4 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4, null, null),
                        new TreeNode(5, null, null)),
                new TreeNode(3,
                        new TreeNode(6, null, null),
                        new TreeNode(7, null, null))
        );
        // Calling the function to print the tree by level
        System.out.println("\nPrinting tree3 by level");
        printTreeByLevel(tree3);
        System.out.println("\nPrinting tree4 by level");
        printTreeByLevel(tree4);
        System.out.println("\nAre they same? " + HelloWorld.leafSimilar(tree3, tree4));
    }
    
    // Function to print the tree by level
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
    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();
        dfs(root1, leaves1);
        dfs(root2, leaves2);
        return leaves1.equals(leaves2);
    }

    private static void dfs(TreeNode node, List<Integer> leaves) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                leaves.add(node.val);
            }
            dfs(node.left, leaves);
            dfs(node.right, leaves);
        }
    }
}