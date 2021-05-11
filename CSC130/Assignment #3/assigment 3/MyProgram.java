class MyProgram
{
    public static void main(String[] args)
    {
        BinarySearchTree tree = new BinarySearchTree();

        //====================== First example
        System.out.println("The following shows the tree structure printed by printTree().");
        tree.add(10);
        tree.add(10);
        tree.add(43);
        tree.add(18);
        tree.add(6);
        tree.add(50);
        tree.add(8);
        tree.remove(8);
        tree.printTree();
       
    }
}