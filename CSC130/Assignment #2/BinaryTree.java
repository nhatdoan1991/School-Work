//class Binary Tree
public class BinaryTree{
	public static Node root;
	public BinaryTree() {
	}
	public BinaryTree(Node root)
	{
		BinaryTree.root=root;
	}

	public static void printValues() {
	root.printValues();
	}
	public static void printTree() {
		root.printTree(0);
	}
	public static String About() {
		return "-----Nhat Doan-217523684 ------\n"
				+ "CSC130 - Prof Cook \n"
				+ "Binary Tree.\n";
	}
}
