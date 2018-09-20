
// Exercise 24.2 by Aditya Mehta

public class MyLinkedList<E> extends MyAbstractList<E> {
	private Node<E> head, tail;

	public MyLinkedList() {
	}

	public MyLinkedList(E[] x) {
		super(x);
	}

	// returns first element
	public E getFirst() {
		if (size != 0) {
			return head.element;
		} else {

			return null;
		}
	}

	public E getLast() {
		if (size != 0) {

			return tail.element;
		} else {
			return null;
		}
	}

	public void addFirst(E e) {
		Node<E> nodeNew = new Node<>(e);
		nodeNew.next = head;
		head = nodeNew;
		size = size + 1;

		if (tail == null)
			tail = head;
	}

	public void addLast(E e) {
		Node<E> nodeNew = new Node<>(e);
		if (tail != null) {
			tail.next = nodeNew;
			tail = tail.next;
		} else {

			head = tail = nodeNew;
		}

		size++;
	}

	@Override
	public void add(int z, E e) {
		if (z == 0)
			addFirst(e);
		else if (z >= size)
			addLast(e);
		else {
			Node<E> now = head;
			for (int i = 1; i < z; i++)
				now = now.next;
			Node<E> a = now.next;
			now.next = new Node<>(e);
			(now.next).next = a;
			size++;
		}
	}

	public E removeFirst() {
		if (size != 0) {
			Node<E> a = head;
			head = head.next;
			size--;
			return a.element;
		} else {

			return null;
		}
	}

	public E removeLast() {
		if (size == 0)
			return null;
		else if (size == 1) {
			Node<E> a = head;
			head = tail = null;
			size = 0;
			return a.element;
		} else {
			Node<E> now = head;

			for (int i = 0; i < size - 2; i++)
				now = now.next;

			Node<E> a = tail;
			tail = now;
			tail.next = null;
			size--;
			return a.element;
		}
	}

	@Override
	public E remove(int z) {
		if (!(z > 0 || z <= size))
			return null;
		else if (z == 0)
			return removeFirst();
		else if (z == size - 1)
			return removeLast();
		else {
			Node<E> previous = head;

			int i = 1;
			while (i < z) {
				previous = previous.next;
				i = i + 1;
			}

			Node<E> now = previous.next;
			previous.next = now.next;
			size--;
			return now.element;
		}
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("[");

		Node<E> now = head;
		for (int i = 0; i < size; i++) {
			result.append(now.element);
			now = now.next;
			if (now != null) {
				result.append(", ");
			} else {
				result.append("]");
			}
		}

		return result.toString();
	}

	@Override
	public void clear() {
		size = 0;
		head = tail = null;
	}

	@Override
	public boolean contains(E e) {
		if (size == 0)
			return false;
		else {
			Node<E> now = head;

			while (now != null) {
				if (now.element == e)
					return true;
				now = now.next;
			}
		}
		return false;
	}

	@Override
	public E get(int z) {
		if (z < 0 || z >= size)
			return null;
		else if (z == 0)
			return getFirst();
		else if (z == size - 1)
			return getLast();
		else {
			Node<E> now = head.next;

			for (int i = 1; i < z; i++)
				now = now.next;
			return now.element;
		}

	}

	@Override
	public int indexOf(E e) {
		if (head.element == e)
			return 0;
		else if (tail.element == e)
			return size - 1;
		else {
			Node<E> now = head.next;
			int z = 1;
			while (now != null) {
				if (now.element == e)
					return z;
				now = now.next;
				z++;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E e) {
		int z = -1;
		Node<E> now = head;
		for (int i = 0; i < size; i++) {
			if (now.element == e)
				z = i;
			now = now.next;
		}

		return z;
	}

	@Override
	public E set(int z, E e) {
		if (z < 0 || z > size - 1)
			return null;
		else {
			Node<E> now = head;
			for (int i = 0; i < z; i++)
				now = now.next;

			now.element = e;
			return now.element;
		}
	}

	@Override
	public java.util.Iterator<E> iterator() {
		return new LinkedListIterator();
	}

	public class LinkedListIterator implements java.util.Iterator<E> {
		private Node<E> now = head;

		@Override
		public boolean hasNext() {
			return (now != null);
		}

		@Override
		public E next() {
			E e = now.element;
			now = now.next;
			return e;
		}

		@Override
		public void remove() {
			System.out.println("Implementation left as an exercise");
		}
	}

	private static class Node<E> {
		E element;
		Node<E> next;

		public Node(E element) {
			this.element = element;
		}
	}
}