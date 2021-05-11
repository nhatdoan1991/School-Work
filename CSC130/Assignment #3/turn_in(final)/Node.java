public class Node
{
    public Node left;
    public Node right;
    public int data;
    public Node (int data, Node left, Node right)
    {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Node (int data)
    {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public void printTree(int indent)
    {
        String pad = "";

        //=== Print current node
        //This is ugly. Other languages, such as C#, have useful string methods for padding.
        for (int n = 0; n < indent; n++)     
        {
            pad += "  ";                     //2 spaces
        }
        
		  System.out.println(pad +  "+-- " + this.data);
        
        //== Recursion
        if (this.left != null)
        {
            this.left.printTree(indent + 1);
        }
  
        if (this.right != null)
        {
            this.right.printTree(indent + 1);
        }
    }

    public void printValues()
    {
        if (this.left != null)
        {
            this.left.printValues();
        }
        
        //Infix
        System.out.print(this.data + ' ');   //Just print the data (no newline) and a space

        if (this.right != null)
        {
            this.right.printValues();
        }
    }
    public void add(int value)
    {
    	if(this.contains(value))
    	{
    		return;
    	}
    	else {
    		if(value<this.data)
    		{
    			if(this.left!=null)
    			{
    				this.left.add(value);
    			}else
    			{
    				Node new_node= new Node(value);
    				this.left=new_node;
    			}
    		}else 
    		{
    			if(this.right!=null)
    			{
    				this.right.add(value);
    			}else
    			{
    				Node new_node= new Node(value);
    				this.right=new_node;
    			}
    		}
    	}
    }
    public boolean contains(int value)
    {
    	Node current = this;
    	while(current.data!=value)
    	{
    		if(current !=null)
    		{
    			if(current.data >value)
    			{
    				current=current.left;
    			}else
    			{
    				current=current.right;
    			}
    			if(current==null)
    			{	
    				return false;
    			}
    	}
    	
    
    	}
    	return true;
    }
    public void remove(int value)
    {
    	if(!this.contains(value))
    	{
    		return;
    	}
    	if(this.parents(value)!=null)
    	{
    		if(this.parents(value).data<this.data)
    		{
    			this.left.remove(value);
    		}else if(this.parents(value).data>this.data)
    		{
    			this.right.remove(value);
    		}else
    		{
    		if(this.parents(value).right!=null)
    		{
    			if(this.parents(value).left==null)
    			{
    				if(this.parents(value).right.data==value)
    				{
    					if(this.parents(value).right.left==null&&this.parents(value).right.right==null)
    					{
    						this.parents(value).right=null;
    					}
    					else if(this.parents(value).right.left!=null&&this.parents(value).right.right==null)
	    				{
    						this.parents(value).right=this.parents(value).right.left;
	    				}else if(this.parents(value).right.left==null&&this.parents(value).right.right!=null)
	    				{
	    					this.parents(value).right=this.parents(value).right.right;
	    				}else
	    				{
	    					int temp=find_Max(this.parents(value).right.left);
	    					
	    					this.parents(temp).left=null;
	    					this.parents(value).right.data=temp;
	    				}
    			
    				}
    			}else
    			{
    				if(this.parents(value).right.data==value)
    				{
    					if(this.parents(value).right.left==null&&this.parents(value).right.right==null)
    					{
    						this.parents(value).right=null;
    					}
    					else if(this.parents(value).right.left!=null&&this.parents(value).right.right==null)
	    				{
    						this.parents(value).right=this.parents(value).right.left;
	    				}else if(this.parents(value).right.left==null&&this.parents(value).right.right!=null)
	    				{
	    					this.parents(value).right=this.parents(value).right.right;
	    				}else
	    				{
	    					int temp=find_Max(this.parents(value).right.left);
	    					this.parents(temp).left=null;
	    					this.parents(value).right.data=temp;
	    			
	    				}
    				}
    				else 
    				{
    					if(this.parents(value).left.data==value)
    	    			{
    	    				if(this.parents(value).left.left==null&&this.parents(value).left.right==null)
    	    				{
    	    					this.parents(value).left=null;
    	    				}
    	    				else if(this.parents(value).left.left!=null&&this.parents(value).left.right==null)
    	    				{
    	    					this.parents(value).left=this.parents(value).left.left;
    	    				}else if(this.parents(value).left.left==null&&this.parents(value).left.right!=null)
    	    				{
    	    					this.parents(value).left=this.parents(value).left.right;
    	    				}else
    	    				{
    	    					int temp=find_Max(this.parents(value).left.left);
    	    					this.parents(temp).left=null;
    	    					this.parents(value).left.data=temp;
    	    				}
    	    			
    	    			}
    				}
    			}
    		}
    		else if(this.parents(value).left!=null)
    		{
    			if(this.parents(value).left.data==value)
    			{
    				if(this.parents(value).left.left==null&&this.parents(value).left.right==null)
    				{
    					this.parents(value).left=null;
    				}
    				else if(this.parents(value).left.left!=null&&this.parents(value).left.right==null)
    				{
    					this.parents(value).left=this.parents(value).left.left;
    				}else if(this.parents(value).left.left==null&&this.parents(value).left.right!=null)
    				{
    					this.parents(value).left=this.parents(value).left.right;
    				}else
    				{
    					int temp=find_Max(this.parents(value).left.left);
    					this.parents(temp).left=null;
    					this.parents(value).left.data=temp;
    					
    				}
    			
    			}
    		}
    		
    		}
    	}
    	else
    	{
    		if(this.left!=null)
    		{
    			int temp=find_Max(this.left);
    		
    		if(this.parents(temp).left!=null)
    		{
    			if(this.parents(temp).left.data==temp)
    			{
    				this.parents(temp).left=null;
    			}else
    			{	if(this.parents(temp).right!=null)
    				this.parents(temp).right=null;
    			}
    		}else
    		{
    			this.parents(temp).right=null;
    		}
    		this.data=temp;
    		}
    	}
    }
    
    public Node parents(int value)
    {
    	if(!this.contains(value))
    	{
    		return null;
    	}
    	if(this.data==value) return null;
    	if(this.left == null)
    	{
    		if(this.right!=null)
    		{
    			if(this.right.data==value)
    				return this;
    		}
    	}else
    	{
    		if(this.right==null)
    		{
    			if(this.left.data==value)
    				return this;
    		}
    		else
    		{
    			if(this.left.data==value)
    				return this;
    			if(this.right.data==value)
    				return this;
    		}
    	}
    	if(value<this.data)
    		return this.left.parents(value);
    	else {
    		return this.right.parents(value);
    	}
    }
    
    public int find_Max(Node node)
    {
    	if(node.right==null)
    		return node.data;
    	return find_Max(node.right);
    }
    public void clear()
    {
    	if(this.left!=null)
    	{
    		this.left.clear();
    	}
    	if(this.right!=null)
    	{
    		this.right.clear();
    	}
    	if(this.parents(this.data)==null)
    	{
    		return;
    	}
    	remove(this.data);
    }
   /* public void clear()
    {
    	Node nodeleft=this.left;
    	Node noderight=this.right;
    	this.left=null;
    	this.right=null;
    	if(nodeleft.left!=null)
    	nodeleft.left.clear();
    	if(noderight.right!=null)
    	noderight.right.clear();
    }*/
}