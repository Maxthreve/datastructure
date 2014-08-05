package edu.mcla.cs.trees;

public interface ITreeVisitor<T extends Comparable<T>>
{
	public void visit(IBinaryTree<T> tree);
}
