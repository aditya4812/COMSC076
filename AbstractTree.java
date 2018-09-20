//Chapter 25 Programming Project by Aditya Mehta

public abstract class AbstractTree<E> implements Tree<E> {
	@Override
	public void inorder() {
	}

	@Override
	public void postorder() {
	}

	@Override
	public void preorder() {
	}

	@Override
	public boolean isEmpty() {
		return getSize() == 0;
	}
}