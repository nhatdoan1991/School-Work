// Notice that the BinaryTree class is technically not needed. All the major logic is in the Node. 
// This is usually the case for recursive structures. This class merely starts recursion
// with default values.

public class BinarySearchTree
{
    public Node root;

    public String about()
	{
		return "Written by Nhat Doan for CSC 130.";
	}
	
    public BinarySearchTree()
    {
        this.root = null;
    }

    public BinarySearchTree(Node root)
    {
        this.root = root;
    }

    public void printTree()
    {
        if (this.root != null)
        {
            this.root.printTree(0);   //Start with a zero indent
        }
    }

    public void printValues()
    {
        if (this.root != null)
        {
            this.root.printValues();
        }
    }
    public void add(int value)
    {
    	if(this.root==null)
    	this.root=new Node(value);
    	this.root.add(value);
    }
    public boolean contains(int value)
    {
		return this.root.contains(value);
    }
    public void remove(int value)
    {
    	if(this.root!=null)
    	this.root.remove(value);
  
    }
    public void clear()
    {
    	if(this.root!=null)
    		this.root.clear();
    }
}