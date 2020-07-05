
public class LinkedQueue<T> implements Queue<T> {
	private static class Node<T> {
		private Node<T> next;
		private T data;

		public Node(Node<T> next, T data) {
			this.next = next;
			this.data = data;
		}
	}

	private Node<T> head;
	private Node<T> tail;
	private int size;

	public LinkedQueue() {
		head = tail = null;
		size = 0;
	}

	@Override
	public int length() {
		return size;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public void enqueue(T e) {
		if (tail == null) {
			Node<T> temp = new Node<>(null, e);
			head = tail = temp;
		} else {
			Node<T> temp = new Node<>(null, e);
			tail.next = temp;
			tail = temp;
		}
		size++;
	}

	@Override
	public T serve() {
		T data = head.data;
		head = head.next;
		size--;
		if (size == 0)
			tail = null;
		return data;
	}

}
