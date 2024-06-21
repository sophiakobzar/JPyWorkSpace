package com.cognixia.jump;
import java.util.*;
public class SimpleProgram {
	public static void main(String[] args) {
		//System.out.print("Enter your name     : ");
		Scanner sc = new Scanner(System.in);
		//String name = sc.nextLine();
		String name = "Sophia Kobzar";
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

		int[] nums1 = {1, 2, 3, 4, 5, 6};
		int[] nums2 = {4, 5, 6, 7, 8, 9};
		System.out.println("\nFinding difference between num1 and num2: "+findDifference(nums1, nums2));
		// Creating a linked list with values 1 -> 2 -> 3 -> 4 -> 5
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		System.out.print("\nOG List: ");
		printList(head);
		head = reverseList(head);
		System.out.print("reverseList: ");
		printList(head);
		System.out.println("\ntribonacci n=3: "+tribonacci(3));
		System.out.println("fibonacci n=6: "+fibonacci(6));
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
	public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
		HashSet<Integer> set1 = new HashSet<>();
		HashSet<Integer> set2 = new HashSet<>();

		// Adding elements of first array to HashSet set1
		for(int num : nums1) {
			set1.add(num);
		}

		// Adding elements of second array to HashSet set2
		for(int num : nums2) {
			set2.add(num);
		}

		// Creating lists to store distinct integers
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();

		// Checking for integers present in set1 but not in set2
		for(int num : set1) {
			if(!set2.contains(num)) {
				list1.add(num); // Adding the distinct integers to list1
			}
		}

		// Checking for integers present in set2 but not in set1
		for(int num : set2) {
			if(!set1.contains(num)) {
				list2.add(num); // Adding the distinct integers to list2
			}
		}

		// Creating a list of lists to store the results
		List<List<Integer>> result = new ArrayList<>();
		result.add(list1); // Adding list1 to the result
		result.add(list2); // Adding list2 to the result

		return result; // Returning the result
	}

	/*Inside the loop, we first store the next node of current in nextTemp.
	This is necessary because we’re about to change current.next in the next step,
	and we don’t want to lose the reference to the rest of the list.
	Then, we reverse the next pointer of current to point to prev,
	effectively moving one node from the original list to the reversed list.
	Finally, we move both prev and current one step forward along the list.
	We set prev to current (the node we just processed), and current to nextTemp
	(the next node to be processed).

	Return Value: After the loop finishes, current is null
	(we’ve reached the end of the list), and prev is the head of the
	reversed list. So we return prev
	*/
	public static ListNode reverseList(ListNode head) {
		ListNode prev = null; // pointers
		ListNode current = head; // pointers
		while (current != null) {
			ListNode nextTemp = current.next;
			current.next = prev;
			prev = current;
			current = nextTemp;
		}
		return prev;
	}
	public static void printList(ListNode head) {
		ListNode current = head;
		while (current != null) {
			System.out.print(current.val + " ");
			current = current.next;
		}
		System.out.println();
	}
	public static int tribonacci(int n) {
		if (n < 2) {
			return n;
		}
		if (n == 2) {
			return 1;
		}

		int[] trib = new int[n + 1];
		trib[0] = 0;
		trib[1] = 1;
		trib[2] = 1;

		for (int i = 3; i <= n; i++) {
			//The Tribonacci sequence is defined such that
			// each number after the first three is the sum
			// of the three preceding ones. That’s why we have:
			trib[i] = trib[i - 1] + trib[i - 2] + trib[i - 3];
		}
		return trib[n];
	}
	public static int fibonacci(int n) {
		if (n <= 1) {
			return n;
		}

		int[] fib = new int[n + 1];
		fib[0] = 0;
		fib[1] = 1;

		for (int i = 2; i <= n; i++) {
			fib[i] = fib[i - 1] + fib[i - 2];
		}

		return fib[n];
	}
}