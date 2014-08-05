package sorting;

import java.util.ArrayList;
import java.util.List;

public class Heap<T extends Comparable<T>> implements IHeap<T>
{
    private List<T> m_items;
    
    public Heap()
    {
       m_items = new ArrayList<T>();
    }
    
    @Override
    public void insert(T item)
    {
        m_items.add(item);
        moveUp(m_items.size()-1);
    }

    @Override
    public int size()
    {
        return m_items.size();
    }

    @Override
    public T peekTop()
    {
        return m_items.get(0);
    }

    @Override
    public T removeTop()
    {
        T root = m_items.remove(0);
        if (m_items.size() > 0)
        {
            T farLeft = m_items.remove(m_items.size()-1);
            m_items.add(0, farLeft);
            moveDown(0);
        }
        return root;
    }
    
    @Override
    public String toString()
    {
        return "Heap: " + m_items.toString();
    }
    
    private void moveUp(int i)
    {
        if (i > 0)
        {
            if (m_items.get(i).compareTo(m_items.get(getParent(i))) == 1)
            {
                swap(i, getParent(i));
                moveUp(getParent(i));
            }
        }
    }

    private void moveDown(int i)
    {
        T me = m_items.get(i);
        // no children, can't move down...
        if (getLeft(i) >= m_items.size() && getRight(i) >= m_items.size())
        {
            return;
        }
        // two children
        else if (getLeft(i) < m_items.size() && getRight(i) < m_items.size())
        {
            T left = m_items.get(getLeft(i));
            T right = m_items.get(getRight(i));

            // I am bigger than both children, can't move down...
            if (me.compareTo(left) == 1 && me.compareTo(right) == 1)
            {
                return;
            }
            // I am smaller than the left, so down to the left... 
            else if (me.compareTo(right) != -1 && me.compareTo(left) == -1)
            {
                swap(i, getLeft(i));
                moveDown(getLeft(i));
            }
            // I am smaller than the right, so down to the right...
            else if (me.compareTo(right) == -1 && me.compareTo(left) != -1)
            {
                swap(i, getRight(i));
                moveDown(getRight(i));
            }
            // I am smaller than both children, so move towards the larger child...
            else if (me.compareTo(right) == -1 && me.compareTo(left) == -1)
            {
                if (left.compareTo(right) == 1)
                {
                    swap(i, getLeft(i));
                    moveDown(getLeft(i));
                }
                else
                {
                    swap(i, getRight(i));
                    moveDown(getRight(i));
                }
            }
        }
        // one child to left, move left...
        else if (getLeft(i) < m_items.size())
        {
            T left = m_items.get(getLeft(i));
            if (me.compareTo(left) == -1)
            {
                swap(i, getLeft(i));
                moveDown(getLeft(i));
            }
        }
        // one child to right, move right...
        else if (getRight(i) < m_items.size())
        {
            T right = m_items.get(getRight(i));
            if (me.compareTo(right) == -1)
            {
                swap(i, getRight(i));
                moveDown(getRight(i));
            }
        }

    }
    
    private int getParent(int index)
    {
        return (index == 0) ? -1 : (index - 1)/2;
    }

    private int getLeft(int index)
    {
        return (2 * index) + 1; 
    }

    private int getRight(int index)
    {
        return (2 * index) + 2;
    }

    private void swap(int i1, int i2)
    {
        T item1 = m_items.get(i1);
        T item2 = m_items.get(i2);
        m_items.set(i1, item2);
        m_items.set(i2, item1);
    }
}
