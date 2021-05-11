//Nhat Doan 217523684
//Assignment #2 Binary Tree: print tree and values
public class main extends BinaryTree {

	public static void main(String[] args) {
		//print out my info
		System.out.printf(About());
		//create a binary tree by hardcoding
		Node root = new Node(1,
				(new Node(
						3,
						new Node(4,
								null,
								null),
						new Node(6
										,null,
										null))),
				new Node(7,
												new Node(5,
														null,
														null),
												new Node(9,
																null,
																null)));
		//create an object bt to store the tree data and use its functions
		BinaryTree bt= new BinaryTree(root);
		//call function print the tree
		System.out.print("\n Binary Tree: \n");
		printTree();
		//call function to print values of the tree
		System.out.print("\n Value of the Binary Tree: ");
		printValues();
	}
}
