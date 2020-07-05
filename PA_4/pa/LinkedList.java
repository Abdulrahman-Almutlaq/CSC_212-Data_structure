
public class LinkedList<T> implements List<T> {
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

	@Override
	public boolean empty() {
		return head == null;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public void findFirst() {
		current = head;
	}

	@Override
	public void findNext() {
		current = current.next;
	}

	@Override
	public boolean last() {
		try {
		return current.next == null;
		}
		catch (Exception ee) {
			return false;
		}
	}

	@Override
	public T retrieve() {
		return current.data;		
	}

	@Override
	public void update(T e) {
		current.data = e;
	}

	@Override
	public void insert(T val) {
		try {
		Node<T> tmp;
		if (empty()) {
			current = head = new Node<T>(null, val);
			size++;
		} else {
			tmp = current.next;
			current.next = new Node<T>(tmp, val);
			current = current.next;
			size++;
		}
		}
		catch (Exception ee) {
			
		}
	}

	@Override
	public void remove() {
		try {
		if (current == head) {
			head = head.next;
		} else {
			Node<T> tmp = head;

			while (tmp.next != current)
				tmp = tmp.next;

			tmp.next = current.next;
		}

		size--;
		if (current.next == null)
			current = head;
		else
			current = current.next;
		}
		catch (Exception ee) {
			
		}

	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean exists(T e) {
		try {
		if (head == null)
			return false;
		Node<T> curr = head;
		while (curr!=null) {
			if (curr.data == e) 				
				return true;
			curr=curr.next;
		}
		return false;
		}
		catch (Exception ee) {
			return false;
		}
	}
}
