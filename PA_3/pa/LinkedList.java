public class LinkedList<T> {
	private static class Node<T> {
		Node<T> next;
		T data;

		public Node(Node<T> next, T data) {
			this.next = next;
			this.data = data;
		}
	}

	private Node<T> head;
	private Node<T> current;
	private int size;

	public LinkedList() {
		head = current = null;
		size = 0;
	}

	public int nbElem() {
		return size;
	}

	public boolean empty() {
		return head == null;
	}

	public boolean full() {
		return false;
	}

	public void findFirst() {
		current = head;
	}

	public void findNext() {
		current = current.next;
	}

	public boolean last() {
		return current.next == null;
	}

	public T retrieve() {
		return current.data;
	}

	public void update(T e) {
		current.data = e;
	}

	public void insert(T e) {
		if (head == null) {
			current = head = new Node<>(null, e);
			size++;
		} else {
			Node<T> temp = new Node<>(current.next, e);
			current.next = temp;
			size++;
			current = current.next;
		}
	}

	public void remove() {
		if (head == current)
			head = head.next;
		else {
			Node<T> prev = head;
			for (int i = 0; prev.next != current; i++)
				prev = prev.next;
			prev.next = current.next;
			if (current.next != null)
				current = current.next;
			else
				current = head;
		}
		size--;
	}

}
