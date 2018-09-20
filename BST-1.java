import java.util.Iterator;
import java.util.Scanner;

//Chapter 25 Programming Project by Aditya Mehta

public class BST<E extends Comparable<E>> extends AbstractTree<E> {
	protected TreeNode<E> root;
	protected int size = 0;

	public BST() {
	}

	public BST(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			insert(objects[i]);
	}

	@Override
	public boolean search(E e) {
		TreeNode<E> current = root;

		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else
				return true;
		}

		return false;
	}

	@Override
	public boolean insert(E e) {
		if (root == null)
			root = createNewNode(e);
		else {

			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while (current != null) {
				if (e.compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				} else
					return false;
			}

			if (e.compareTo(parent.element) < 0)
				parent.left = createNewNode(e);
			else
				parent.right = createNewNode(e);
		}

		size = size + 1;
		return true;
	}

	protected TreeNode<E> createNewNode(E e) {
		return new TreeNode<>(e);
	}

	public static class TreeNode<E extends Comparable<E>> {
		protected E element;
		protected TreeNode<E> left;
		protected TreeNode<E> right;

		public TreeNode(E e) {
			element = e;
		}
	}

	@Override
	public int getSize() {
		return size;
	}

	public TreeNode<E> getRoot() {
		return root;
	}

	public java.util.ArrayList<TreeNode<E>> path(E e) {
		java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<>();
		TreeNode<E> current = root;

		while (current != null) {
			list.add(current);
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else
				break;
		}

		return list;
	}

	@Override
	public boolean delete(E e) {
		TreeNode<E> parent = null;
		TreeNode<E> current = root;
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				parent = current;
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				parent = current;
				current = current.right;
			} else
				break;
		}

		if (current == null)
			return false;

		// Case 1
		if (current.left == null) {

			if (parent == null) {
				root = current.right;
			} else {
				if (e.compareTo(parent.element) < 0)
					parent.left = current.right;
				else
					parent.right = current.right;
			}
		} else {
			// Case 2
			TreeNode<E> parentOfRightMost = current;
			TreeNode<E> rightMost = current.left;

			while (rightMost.right != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.right;
			}

			current.element = rightMost.element;

			if (parentOfRightMost.right == rightMost)
				parentOfRightMost.right = rightMost.left;
			else
				// Exception case
				parentOfRightMost.left = rightMost.left;
		}

		size--;
		return true;
	}

	@Override
	public java.util.Iterator<E> iterator() {
		return new InorderIterator();
	}

	private class InorderIterator implements java.util.Iterator<E> {

		private java.util.ArrayList<E> list = new java.util.ArrayList<>();
		private int current = 0;

		public InorderIterator() {
			inorder();
		}

		private void inorder() {
			inorder(root);
		}

		private void inorder(TreeNode<E> root) {
			if (root == null)
				return;
			inorder(root.left);
			list.add(root.element);
			inorder(root.right);
		}

		@Override
		public boolean hasNext() {
			if (current < list.size())
				return true;

			return false;
		}

		@Override
		public E next() {
			return list.get(current++);
		}

		@Override
		public void remove() {
			delete(list.get(current));
			list.clear();
			inorder();
		}
	}

	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * 
	 * EXERCISE 25.1
	 * 
	 */
	public void breadthFirstTraversal() {
		if (root == null) {
			return;
		}
		java.util.Queue<TreeNode<E>> q = new java.util.LinkedList<>();
		q.add(root);
		while (q.isEmpty() == false) {
			TreeNode<E> current = q.element();
			if (!(current.right == null)) {
				q.add(current.right);
			}
			if (!(current.left == null)) {
				q.add(current.left);
			}
			System.out.print(q.remove().element + " ");
		}
	}

	public int height() {
		return height(root);
	}

	public int height(TreeNode<E> root) {
		if (root == null) {
			return 0;
		} else {
			return (Math.max(height(root.left), height(root.right)) + 1);
		}

	}

	/**
	 * EXERCISE 25.2
	 */
	public boolean isFullBST() {
		return size == Math.pow(2, height()) - 1 ? true : false;
	}

	/**
	 * EXERCISE 25.3
	 */
	@Override
	public void inorder() {
		inorder(root);
	}

	protected void inorder(TreeNode<E> root) {
		if (root == null)
			return;
		inorder(root.left);
		System.out.print(root.element + " ");
		inorder(root.right);
	}

	/**
	 * EXERCISE 25.4
	 */
	@Override
	public void preorder() {
		preorder(root);
	}

	protected void preorder(TreeNode<E> root) {
		if (root == null)
			return;
		System.out.print(root.element + " ");
		preorder(root.left);
		preorder(root.right);
	}

	/**
	 * EXERCISE 25.5
	 */
	@Override
	public void postorder() {
		postorder(root);
	}

	protected void postorder(TreeNode<E> root) {
		if (root == null)
			return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.element + " ");
	}

	/**
	 * EXERCISE 25.6
	 */
	public int getNumberOfLeaves() {
		return getNumberOfLeaves(root);
	}

	protected int getNumberOfLeaves(TreeNode<E> root) {
		if (root == null)
			return 0;

		return root.left == null && root.right == null ? 1
				: getNumberOfLeaves(root.left) + getNumberOfLeaves(root.right);
	}

	/**
	 * EXERCISE 25.7
	 */
	public int getNumberOfNonLeaves() {
		return getNumberOfNonLeaves(root);
	}

	protected int getNumberOfNonLeaves(TreeNode<E> root) {
		if (root == null)
			return 0;

		return (root.left == null && root.right == null) ? 0
				: 1 + getNumberOfNonLeaves(root.left) + getNumberOfNonLeaves(root.right);
	}

	/**
	 * EXERCISE 25.10
	 */
	public java.util.Iterator<E> preorderIterator() {
		return new PreorderIterator();
	}

	private class PreorderIterator implements java.util.Iterator<E> {

		private java.util.ArrayList<E> list = new java.util.ArrayList<>();
		private int current = 0;

		public PreorderIterator() {
			preorder();
		}

		private void preorder() {
			preorder(root);
		}

		private void preorder(TreeNode<E> root) {
			if (root == null)
				return;
			list.add(root.element);
			preorder(root.left);
			preorder(root.right);
		}

		@Override
		public boolean hasNext() {
			if (current < list.size())
				return true;

			return false;
		}

		@Override
		public E next() {
			return list.get(current++);
		}

		@Override
		public void remove() {
			delete(list.get(current));
			list.clear();
			preorder();
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// 25.1
		System.out.println("EXERCISE 25.1: ");
		// begin implementation
		BST<String> tree = new BST<>();
		tree.insert("George");
		tree.insert("Micheal");
		tree.insert("Tom");
		tree.insert("Adam");
		tree.insert("Jones");
		tree.insert("Peter");
		tree.insert("Daniel");
		System.out.print("\nBreadth-first: ");
		tree.breadthFirstTraversal();
		System.out.print("\nHeight of tree: ");
		System.out.println(tree.height());
		Integer[] numOne = { 2, 4, 3, 1, 8, 5, 6, 7 };
		BST<Integer> intTreeOne = new BST<>(numOne);
		intTreeOne.breadthFirstTraversal();
		System.out.print("\nHeight of tree: ");
		System.out.println(intTreeOne.height());
		System.out.println();

		// 25.2
		System.out.println("EXERCISE 25.2: ");
		// begin implementation
		Integer[] numbers = { 2, 4, 3, 1, 8, 5, 6, 7 };
		Integer[] numbers2 = { 4, 2, 1, 3, 8, 5, 9 };
		Integer[] numbers3 = { 10, 4, 2, 1, 3, 8, 5, 9, 15, 12, 11, 13, 21, 19, 25 };
		BST<Integer> intTree = new BST<>(numbers);
		BST<Integer> intTree2 = new BST<>(numbers2);
		BST<Integer> intTree3 = new BST<>(numbers3);
		System.out.print("\nIs tree #1 a full binary tree? ");
		System.out.println(intTree.isFullBST());
		System.out.print("\nIs tree #2 a full binary tree? ");
		System.out.println(intTree2.isFullBST());
		System.out.print("\nIs tree #3 a full binary tree? ");
		System.out.println(intTree3.isFullBST());
		System.out.println();

		// 25.3
		System.out.println("EXERCISE 25.3: ");
		// begin implementation
		Integer[] numThree = new Integer[10];
		System.out.print("Enter 10 integers: ");
		int i = 0;
		while (i < numThree.length) {
			numThree[i] = input.nextInt();
			i++;
		}
		BST<Integer> intTreeThree = new BST<>(numThree);
		System.out.print("Tree inorder: ");
		intTreeThree.inorder();
		System.out.println();
		System.out.println();

		// 25.4
		System.out.println("EXERCISE 25.4: ");
		// begin implementation
		Integer[] numFour = new Integer[10];
		System.out.print("Enter 10 integers: ");
		int x = 0;
		while (x < numFour.length) {
			numFour[x] = input.nextInt();
			x++;
		}
		BST<Integer> intTreeFour = new BST<>(numFour);
		System.out.print("Tree preorder: ");
		intTreeFour.preorder();
		System.out.println();
		System.out.println();

		// 25.5
		System.out.println("EXERCISE 25.5: ");
		// begin implementation
		Integer[] numFive = new Integer[10];
		System.out.print("Enter 10 integers: ");
		int y = 0;
		while (y < numFive.length) {
			numFive[y] = input.nextInt();
			y++;
		}
		BST<Integer> intTreeFive = new BST<>(numFive);
		System.out.print("Tree postorder: ");
		intTreeFive.postorder();
		System.out.println();
		System.out.println();

		// 25.6
		System.out.println("EXERCISE 25.6: ");
		// begin implementation
		Integer[] numSix = { 60, 55, 45, 47, 59, 100, 76, 107, 101 };
		BST<Integer> intTreeSix = new BST<>(numSix);
		System.out.println("Number of leaf nodes: " + intTreeSix.getNumberOfLeaves());
		System.out.println();

		// 25.7
		System.out.println("EXERCISE 25.7: ");
		// begin implementation
		Integer[] numSeven = { 60, 55, 45, 47, 59, 100, 76, 107, 101 };
		BST<Integer> intTreeSeven = new BST<>(numSeven);
		System.out.println("Number of nonleaf nodes: " + intTreeSeven.getNumberOfNonLeaves());
		System.out.println();

		// 25.10
		System.out.println("EXERCISE 25.10: ");
		// begin implementation
		BST<String> binaryTree = new BST<>();
		binaryTree.insert("George");
		binaryTree.insert("Michael");
		binaryTree.insert("Tom");
		binaryTree.insert("Adam");
		binaryTree.insert("Jones");
		binaryTree.insert("Peter");
		binaryTree.insert("Daniel");
		Iterator<String> str = binaryTree.preorderIterator();
		while (str.hasNext()) {
			System.out.print(str.next().toUpperCase() + " ");
		}
		System.out.println();
	}
}