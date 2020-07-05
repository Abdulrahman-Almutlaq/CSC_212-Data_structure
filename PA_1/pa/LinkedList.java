
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
		return current.next == null;
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

	@Override
	public void remove() {
		if (size == 1)
			head = current = null;
		Node<T> prev = head;
		for (int i = 0; prev.next != current; i++)
			prev = prev.next;
		prev.next = current.next;
		if (current.next != null)
			current = current.next;
		else
			current = head;
		size--;
	}

	@Override
	public boolean findFirstEle(Cond<T> cnd) {
		if (empty())
			return false;
		Node<T> temp = head;
		for (int i = 0; temp != null; i++) {
			if (cnd.test(temp.data)) {
				current = temp;
				return true;
			}
			temp = temp.next;
		}
		return false;
	}

	@Override
	public List<T> findAllEle(Cond<T> cnd) {
		List<T> temp = new LinkedList<>();
		if (empty())
			return temp;
		Node<T> jump = head;
		for (int i = 0; jump != null; i++) {
			if (cnd.test(jump.data))
				temp.insert(jump.data);
			jump = jump.next;
		}
		return temp;
	}

}
