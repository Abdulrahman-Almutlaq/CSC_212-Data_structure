public class MGraph<K extends Comparable<K>> implements Graph<K> {
	public Map<K, List<K>> adj; // Do not change this

	public MGraph() {
		adj = new BSTMap<>();
	}

	@Override
	public boolean addNode(K i) {
		try {
		return adj.insert(i, new LinkedList<K>());
		}
		catch (Exception ee) {
			return false;
		}
	}

	@Override
	public boolean isNode(K i) {
		try {
		return adj.retrieve(i).first;
		}
		catch (Exception ee) {
			return false;
		}
	}

	@Override
	public boolean addEdge(K i, K j) {
		try {
		if (!isNode(i)) {
			return false;
		}
		if (!isNode(j)) {
			return false;
		}

		Pair<Boolean, List<K>> p = adj.retrieve(i);
		Pair<Boolean, List<K>> p2 = adj.retrieve(j);
		if (p.second.exists(j)||p2.second.exists(i)) {
			return false;
		}
		p.second.insert(j);
		p2.second.insert(i);
		return true;
		}
		catch (Exception ee) {
			return false;
		}
}
	

	@Override
	public boolean isEdge(K i, K j) {
		try {
		Pair<Boolean, List<K>> p = adj.retrieve(i);
		if (p.second.empty())
			return false;
		if (p.second.exists(j))
			return true;
		return false;
		}
		catch (Exception ee) {
			return false;
		}
	}

	@Override
	public List<K> neighb(K i) {
		try {
		if (!isNode(i))
			return null;
		return adj.retrieve(i).second;
		}
		catch (Exception ee) {
			return new LinkedList<K>();
		}
	}

	@Override
	public int deg(K i) {
		try {
		if (!isNode(i))
			return -1;
		return adj.retrieve(i).second.size();
		}
		catch (Exception ee) {
			return -1;
		}
	}

	@Override
	public List<K> getNodes() {
		try {
		return adj.getKeys();
		}
		catch (Exception ee) {
			return new LinkedList<K>();
		}
	}

}
