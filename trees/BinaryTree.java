package edu.mcla.cs.trees;

import java.util.LinkedList;
import java.util.Queue;


public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T> 
{
	private IBinaryTree<T> m_parent;
	private IBinaryTree<T> m_left;
	private IBinaryTree<T> m_right;
	private T m_item;
	private int m_level;

	public BinaryTree(T item)
	{
		m_item = item;
		m_parent = null;
		m_left = null;
		m_right = null;
		m_level = 0;
	}

	@Override
	public IBinaryTree<T> getParent() 
	{
		return m_parent;
	}

	@Override
	public IBinaryTree<T> getLeft() 
	{
		return m_left;
	}

	@Override
	public IBinaryTree<T> getRight() {
		return m_right;
	}

	@Override
	public T getItem() 
	{
		return m_item;
	}
	
	@Override
	public IBinaryTree<T> search(T item) 
	{
		return _search(this, item);
	}

	@Override
	public void inorder() 
	{
		_inorder(this, null);
	}

	@Override
	public void inorder(ITreeVisitor<T> visitor) 
	{
		_inorder(this, visitor);
	}

    @Override
    public void levelOrder()
    {
        _levelOrder(this, null);
    }

    @Override
    public void levelOrder(ITreeVisitor<T> visitor)
    {
        _levelOrder(this, visitor);
    }   
	
	
	@Override
	public boolean insert(T item) 
	{
		return _insert(this, item);
	}
	
	@Override 
	public int getLevel()
	{
	    return m_level;
	}

	@Override
	public IBinaryTree<T> min() 
	{
		IBinaryTree<T> ret = this;
		while (ret.getLeft() != null)
			ret = ret.getLeft();
		return ret;
	}

	@Override
	public IBinaryTree<T> max() 
	{
		IBinaryTree<T> ret = this;
		while (ret.getRight() != null)
			ret = ret.getRight();
		return ret;
	}

	@Override
	public boolean delete(T item) 
	{
		IBinaryTree<T> tree = search(item);
		return _delete(tree);
	}
	
	private void setLeft(IBinaryTree<T> tree)
	{
		if (tree != null && getItem().compareTo(tree.getItem()) == -1)
			throw new Error(
					String.format("Can't add a larger item (%d) to the left of this item (%d)", 
							tree.getItem(), getItem()));
		m_left = tree;
	}
	
	private void setRight(IBinaryTree<T> tree)
	{
		if (tree != null && getItem().compareTo(tree.getItem()) == 1)
			throw new Error(
					String.format("Can't add a larger item (%d) to the left of this item (%d)", 
							tree.getItem(), getItem()));

		m_right = tree;
	}
	
	private boolean _delete(IBinaryTree<T> t)
	{
		BinaryTree<T> tree = (BinaryTree<T>)t;
		boolean ret = (tree != null);
		if (tree != null)
		{
			BinaryTree<T> parent = (BinaryTree<T>)tree.getParent();
			
			// node is a leaf, so just delete the node
			if (tree.getLeft() == null && tree.getRight() == null)
			{
				if (parent.getLeft() == tree)
				{
					parent.setLeft(null);
				}
				else
				{
					parent.setRight(null);
				}
			}
			// node has one child on the left
			else if (tree.getLeft() != null && tree.getRight() == null)
			{
			    BinaryTree<T> left = (BinaryTree<T>)parent.getLeft();
			    tree.m_item = left.getItem();
			    tree.setLeft(null);
			}
			// node has one child on the right
			else if (tree.getLeft() == null && tree.getRight() != null)
			{
	            BinaryTree<T> right = (BinaryTree<T>)parent.getRight();
	            tree.m_item = right.getItem();
	            tree.setRight(null);
			}
			// node has two children!  Yikes!  Rotate...
			else 
			{
				BinaryTree<T> largestToLeft = (BinaryTree<T>)tree.getLeft().max();
				tree.m_item = largestToLeft.getItem();
				_delete(largestToLeft);
			}
		}
		return ret;
	}

	private boolean _insert(IBinaryTree<T> p, T item) 
	{
		BinaryTree<T> parent = (BinaryTree<T>)p;
		boolean ret = true;
		if (item.compareTo(parent.getItem()) == -1)
		{
			IBinaryTree<T> child = parent.getLeft();
			if (child == null)
			{
				BinaryTree<T> newNode = new BinaryTree<T>(item);
				newNode.setLevel(parent.getLevel()+1);
				newNode.m_parent = parent;
				parent.setLeft(newNode);
			}
			else
			{
				ret = _insert(child, item);
			}
		} 
		else
		{
			IBinaryTree<T> child = parent.getRight();
			if (child == null)
			{
				BinaryTree<T> newNode = new BinaryTree<T>(item);
				newNode.setLevel(parent.getLevel()+1);
				newNode.m_parent = parent;
				parent.setRight(newNode);
			}
			else
			{
				ret = _insert(child, item);
			}
		}
		return ret;
	}
	
	private IBinaryTree<T> _search(IBinaryTree<T> tree, T item) 
	{
		IBinaryTree<T> ret = null;
		if (tree != null)
		{
			if (tree.getItem().equals(item))
			{
				ret = tree;
			}
			else
			{
				if (item.compareTo(tree.getItem()) == -1)
				{
					ret = _search(tree.getLeft(), item);
				}
				else
				{
					ret = _search(tree.getRight(), item);
				}
			}
		}
		return ret;
	}
	
	private void _inorder(IBinaryTree<T> tree, ITreeVisitor<T> visitor) 
	{
		if (tree != null)
		{
			_inorder(tree.getLeft(), visitor);
			if (visitor == null)
			{
				System.out.println(tree.getItem());
			}
			else
			{
				visitor.visit(tree);
			}
			_inorder(tree.getRight(), visitor);
		}
		return;
	}
	
	private void setLevel(int level)
	{
	    m_level = level;
	}

	private void _levelOrder(IBinaryTree<T> tree, ITreeVisitor<T> visitor)
    {
        Queue<IBinaryTree<T>> currentLevel = new LinkedList<IBinaryTree<T>>();
        Queue<IBinaryTree<T>> nextLevel = new LinkedList<IBinaryTree<T>>();
        
        currentLevel.offer(tree);
        
        while (!currentLevel.isEmpty()) 
        {
            IBinaryTree<T> node = currentLevel.poll();
            if (visitor == null)
            {
                System.out.print(node.getItem() + " ");
            }
            else
            {
                visitor.visit(node);
            }
            
            if (node.getLeft() != null)
            {
                nextLevel.offer(node.getLeft());
            }
            
            if (node.getRight() != null)
            {
                nextLevel.offer(node.getRight());
            }
            
            if (currentLevel.isEmpty()) 
            {
                Queue<IBinaryTree<T>> temp = currentLevel;
                currentLevel = nextLevel;
                nextLevel = temp;
            }
        }
    }
}
