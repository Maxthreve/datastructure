package StackAssignment;

public interface IStack<T> 
{
	public void push(T item);
	public T pop();
	public T peek();
	public int size();
	public boolean isEmpty();
}
