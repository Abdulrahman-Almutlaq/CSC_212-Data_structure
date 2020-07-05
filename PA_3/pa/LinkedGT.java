
public class LinkedGT<T> implements GT<T> {

	private static class BTNode<T> {
		BTNode<T> parent;
		LinkedList<BTNode<T>> children;
		T data;

		public BTNode(BTNode<T> parent, T data) {
			children = new LinkedList<>();
			this.parent = parent;
			this.data = data;
		}
	}

	BTNode<T> root, BTcurrent;

	public LinkedGT() {
		root = BTcurrent = null;
	}

	@Override
	public boolean empty() {
		return root == null;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public T retrieve() {
		return BTcurrent.data;
	}

	@Override
	public void update(T e) {
		BTcurrent.data = e;
	}

	@Override
	public boolean insert(T e) {
		if (empty()) {
			BTcurrent = root = new BTNode<>(null, e);
			return true;
		}
		BTNode<T> temp = new BTNode<>(BTcurrent, e);
		BTcurrent.children.insert(temp);
		BTcurrent = BTcurrent.children.retrieve();
		return true;
	}

	@Override
	public int nbChildren() {
		return BTcurrent.children.nbElem();
	}

	@Override
	public boolean findChild(int i) {
		if(empty() || i<0 || i>=nbChildren())
			return false;
		if (BTcurrent.children == null || BTcurrent.children.empty())
			return false;
		int c = 0;
		BTcurrent.children.findFirst();
		while (!BTcurrent.children.last()) {
			if (c == i) {
				BTcurrent = BTcurrent.children.retrieve();
				return true;
			}
			c++;
			BTcurrent.children.findNext();
		}
		if (c == i) {
			BTcurrent = BTcurrent.children.retrieve();
			return true;
		}
		return false;
	}

	@Override
	public boolean findParent() {
		if (BTcurrent.parent != null) {
			BTcurrent = BTcurrent.parent;
			return true;
		}
		return false;
	}

	@Override
	public void findRoot() {
		BTcurrent = root;
	}

	@Override
	public void remove() {
		BTNode<T> temp = BTcurrent;
		if (!findParent()) {
			root = null;
			return;
		}
		BTcurrent.children.findFirst();
		while (!BTcurrent.children.last()) {
			if (BTcurrent.children.retrieve() == temp)
				BTcurrent.children.remove();
			BTcurrent.children.findNext();
		}
		if (BTcurrent.children.retrieve() == temp)
			BTcurrent.children.remove();

	}
}
