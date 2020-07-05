
public class BSTMap<K extends Comparable<K>, T> implements Map<K, T> {

	public BSTNode<K, T> root;

	public BSTMap() {
		root = null;
	}

	@Override
	public int size() {
		if (root == null)
			return 0;
		return size(root);
	}

	private int size(BSTNode<K, T> root) {
		if (root == null)
			return 0;
		return 1 + size(root.left) + size(root.right);
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public void clear() {
		root = null;
	}

	@Override
	public boolean update(K k, T e) {
		try {
		if (root == null)
			return false;
		return update(k, e, root);
		}
		catch (Exception ee) {
			return false;
		}
	}

	private boolean update(K k, T e, BSTNode<K, T> root) {
		if (root.key.compareTo(k) == 0) {
			root.data = e;
			return true;
		} else if (root.key.compareTo(k) > 0) {
			if (root.left != null)
				return false || update(k, e, root.left);
		} else if (root.key.compareTo(k) < 0) {
			if (root.right != null)
				return false || update(k, e, root.right);
		}
		return false;

	}

	@Override
	public Pair<Boolean, T> retrieve(K k) {
		try {
		if (root == null)
			return new Pair<>(false, null);
		Pair<Boolean, T> p = null;
		if (findu(root, k) != null) {
			p = new Pair<>(true, findu(root, k).data);
			return p;
		}
		return new Pair<>(false, null);
		}catch (Exception ee) {
			return new Pair<>(false, null);
		}
		
	}

	private BSTNode<K, T> findu(BSTNode<K, T> root, K k) {
		if (root.key.compareTo(k) == 0) {
			return root;
		} else if (root.key.compareTo(k) > 0) {
			if (root.left != null)
				return findu(root.left, k);
		} else if (root.key.compareTo(k) < 0) {
			if (root.right != null)
				return findu(root.right, k);
		}
		return null;

	}

	@Override
	public boolean insert(K k, T e) {
		try {
		if (root == null) {
			root = new BSTNode<>(k, e);
			return true;
		}
		return insert(k, e, root);
		}
		catch (Exception ee) {
			return false;
		}
		
	}

	private boolean insert(K k, T e, BSTNode<K, T> root) {
		if (root.key.compareTo(k) == 0) {
			return false;
		} else if (root.key.compareTo(k) > 0) {
			if (root.left == null) {
				root.left = new BSTNode<>(k, e);
				return true;
			}
			if (insert(k, e, root.left))
				return true;
		} else if (root.key.compareTo(k) < 0) {
			if (root.right == null) {
				root.right = new BSTNode<>(k, e);
				return true;
			}
			if (insert(k, e, root.right))
				return true;

		}
		return false;
	}

	@Override
	public boolean remove(K k) {
		try {
		if (root == null || !find(root, k))
			return false;
		return remove(root, k);
		}
		catch (Exception ee) {
			return false;
		}
	}

	private boolean remove(BSTNode<K, T> root, K k) {
		if(root==null)
			return false;
		if (root.key.compareTo(k) == 0) {
			if (root.right == null && root.left == null) {
				if (size() == 1) {
					root = null;
					return true;
				}
				if (findParent(this.root, root).key.compareTo(root.key) > 0)
					findParent(this.root, root).left = null;
				else
					findParent(this.root, root).right = null;
				return true;
			} else if (root.right != null) {
				BSTNode<K, T> temp = root.right;
				while (temp.left != null)
					temp = temp.left;
				root.data = temp.data;
				root.key = temp.key;
				if (findParent(this.root, temp).left == temp)
					findParent(this.root, temp).left = temp.right;
				else
					findParent(this.root, temp).right = temp.right;
				return true;
			} else {
				BSTNode<K, T> temp = root.left;
				while (temp.right != null)
					temp = temp.right;
				root.data = temp.data;
				root.key = temp.key;
				if (findParent(this.root, temp).left == temp)
					findParent(this.root, temp).left = temp.left;
				else
					findParent(this.root, temp).right = temp.left;
				return true;
			}
		} else if (root.key.compareTo(k) > 0) {
			if (root.left != null)
				return false || remove(root.left, k);
		} else if (root.key.compareTo(k) < 0) {
			if (root.right != null)
				return false || remove(root.right, k);
		}
		return false;
	}

	private BSTNode<K, T> findParent(BSTNode<K, T> root, BSTNode<K, T> t) {
		if (root == null || root == t)
			return null;
		if (root.left == t)
			return root;
		if (root.right == t)
			return root;
		BSTNode<K, T> temp = findParent(root.left, t);
		if (temp != null)
			return temp;
		BSTNode<K, T> temp2 = findParent(root.right, t);
		if (temp2 != null)
			return temp2;
		return null;

	}

	private boolean find(BSTNode<K, T> root, K k) {
		try {
		if (root.key.compareTo(k) == 0) {
			return true;
		} else if (root.key.compareTo(k) > 0) {
			if (root.left != null)
				return  find(root.left, k);
		} else if (root.key.compareTo(k) < 0) {
			if (root.right != null)
				return  find(root.right, k);
		}
		return false;
		}
		catch (Exception ee) {
			return false;
		}
	}

	@Override
	public List<K> getKeys() {
		try {
		List<K> temp = new LinkedList<>();
		if (root == null)
			return new LinkedList<>();
		return getKeys(root, temp);
		}
		catch (Exception ee) {
			return new LinkedList<>();
		}
	}

	private List<K> getKeys(BSTNode<K, T> root, List<K> temp) {
		if (root == null)
			return temp;
		getKeys(root.left, temp);
		temp.insert(root.key);
		getKeys(root.right, temp);
		return temp;
	}

}
