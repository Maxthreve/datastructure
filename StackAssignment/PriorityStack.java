package StackAssignment;

public class PriorityStack<T> implements IStack<T>
{
	
	private BasicStack<PriorityItem> m_stack;

	public PriorityStack()
	{
		m_stack = new BasicStack<PriorityItem>();
	}
	
	public void pushPriority(T item)
	{
		m_stack.push(new PriorityItem (item, true));
	}

	@Override
	public void push(T item) 
	{
		m_stack.push(new PriorityItem (item, false));
	}

	@Override
	public T pop()
	{
		T ret = null;
		BasicStack<PriorityItem> temp = new BasicStack<PriorityItem>();
		boolean found = false;
		
		// empty the stack and keep track of the first high priority item 
		// if it exists...
		while (!m_stack.isEmpty())
		{
			PriorityItem item = m_stack.pop();
			if (item.m_high && !found)
			{
				ret = item.m_value;
				found = true;
			}
			else
			{
				temp.push(item);
			}
		}
		
		// put everything back into the stack, except for the
		// first high priority item, if one was found...
		while (!temp.isEmpty())
		{
			m_stack.push(temp.pop());
		}

		if (ret != null)
			m_stack.push(new PriorityItem(ret, true));
		
		return m_stack.pop().m_value;
	}

	
	@Override
	public T peek() 
	{
		return m_stack.peek().m_value;
	}

	@Override
	public int size() 
	{
		return m_stack.size();
	}

	@Override
	public boolean isEmpty() 
	{
		return m_stack.isEmpty();
	}
	
	@Override
	public String toString()
	{
		return m_stack.toString();
	}
	
	private class PriorityItem
	{
		private PriorityItem(T value, boolean high)
		{
			m_value = value;
			m_high = high;
		}
		
		public String toString()
		{
			if (m_high)
				return m_value + "*";
			else
				return m_value.toString();
		}
		private T m_value;
		private boolean m_high; 
	}
}
