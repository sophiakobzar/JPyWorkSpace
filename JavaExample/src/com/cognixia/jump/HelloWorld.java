package com.cognixia.jump;
import java.util.*;
class Tree {
    // Node class for the binary tree
    public int val; // Value of the node
    public Tree left; // Left child of the node
    public Tree right; // Right child of the node

    // Constructor to initialize the node
    public Tree(int val, Tree left, Tree right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class HelloWorld {
	public static void main(String[] args) {
        // Printing a greeting message
        System.out.println("Hello, World! ");
        
        // Creating a sample tree
        Tree tree = new Tree(1,
            new Tree(2, 
                new Tree(6, new Tree(12, null, null), null), 
                new Tree(7, null, null)),
            new Tree(3,
                new Tree(8, null, null),
                new Tree(9, null, null))
        );
        
        // Calling the function to print the tree by level
        printTreeByLevel(tree);
    }
    
    // Function to print the tree by level
    public static void printTreeByLevel(Tree root) {
        // If the root is null, there's nothing to print
        if (root == null) {
            return;
        }

        // Queue to hold nodes at the current level
        Queue<Tree> queue = new LinkedList<>();
        queue.add(root); // Start with the root node

        // Loop while there are nodes to process
        while (!queue.isEmpty()) {
            int size = queue.size(); // Number of nodes at the current level

            // Process all nodes at the current level
            for (int i = 0; i < size; i++) {
                Tree currentNode = queue.poll(); // Get the next node

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
}
