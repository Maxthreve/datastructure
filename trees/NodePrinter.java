package trees;

public class NodePrinter<T extends Comparable<T>> implements ITreeVisitor<T> 
{
	@Override
	public void visit(IBinaryTree<T> tree) 
	{
		System.out.printf("Level: %d Item %s%n", tree.getLevel(), tree.getItem());
	}
}
