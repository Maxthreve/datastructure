package edu.mcla.cs.trees;

public interface IBinaryTree<T extends Comparable<T>>
{
	// immutable methods...
	public IBinaryTree<T> getParent();
	public IBinaryTree<T> getLeft();
	public IBinaryTree<T> getRight();
	public T getItem();
	public IBinaryTree<T> search(T item);
	public IBinaryTree<T> min();
	public IBinaryTree<T> max();
	public void inorder();
	public void inorder(ITreeVisitor<T> visitor);
	public void levelOrder();
	public void levelOrder(ITreeVisitor<T> visitor);
	public int getLevel();
	
	// mutable methoeds...
	public boolean insert(T item);
	public boolean delete(T item);
}

