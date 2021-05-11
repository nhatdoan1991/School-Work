
public class Node extends BinaryTree{
		public int value;
		public Node left;
		public Node right;
		public Node() {	
		}
		public Node(int e, Node l,Node r) {
			value = e;
			left = l;
			right=r;
		}

		public static void traverseInOrder(Node node) {
		    if (node != null) {
		        traverseInOrder(node.left);  
		        System.out.print(" " + node.value);
		        traverseInOrder(node.right);
		    }else
		    {
		    	return;
		    }
		}
		
		public static void printValues() {
			traverseInOrder(root);
		}
		
		public void printTree(int indent) {
			Node temp = root; 
			if (root != null) {
				 	for(int i = 0;i<=indent;i++)
				 	{
				 		System.out.print("  ");
				 	}
			        System.out.print("+-- " + root.value+"\n");
			       
			        root=root.left;
			        printTree(indent+1);
			        root=temp;
			        root=root.right;
			        printTree(indent+1);
			    }
			root=temp;
		}
		
		
	
}
