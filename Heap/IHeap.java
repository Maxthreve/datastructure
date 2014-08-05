package sorting;

public interface IHeap<T extends Comparable<T>>
{
    public void insert(T item);
    public int size();
    public T peekTop();
    public T removeTop();
}
