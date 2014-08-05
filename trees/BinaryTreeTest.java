package edu.mcla.cs.trees;

public class BinaryTreeTest 
{
	public static void main(String[] args) 
	{
		IBinaryTree<Integer> tree = new BinaryTree<Integer>(3);
		tree.insert(1);
		tree.insert(2);
		tree.insert(4);
		tree.insert(5);

		System.out.println("in order");
        tree.inorder(new NodePrinter<Integer>());
        System.out.println();
		
        System.out.println("level order");
        tree.levelOrder(new NodePrinter<Integer>());
        System.out.println();
		
		System.out.println("Max: " + tree.max().getItem());
		System.out.println("Min: " + tree.min().getItem());
		NodeCounter<Integer> counter = new NodeCounter<Integer>();
		tree.inorder(counter);
		System.out.println("Size: " + counter.getCount());
	    System.out.println();
		
	    System.out.println("deleting 3, 1, 5...");
	    System.out.println();
	    
		tree.delete(3);
		tree.delete(1);
		tree.delete(5);

        System.out.println("in order");
        tree.inorder(new NodePrinter<Integer>());
        System.out.println();
        
        System.out.println("level order");
        tree.levelOrder(new NodePrinter<Integer>());
        System.out.println();
		
		System.out.println("Max: " + tree.max().getItem());
		System.out.println("Min: " + tree.min().getItem());
		counter = new NodeCounter<Integer>();
		tree.inorder(counter);
		System.out.println("Size: " + counter.getCount());
	    System.out.println();		
	}

}
