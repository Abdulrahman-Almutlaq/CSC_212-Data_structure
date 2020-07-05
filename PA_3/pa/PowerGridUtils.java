public class PowerGridUtils {

	// Return the IDs of all elements in the power grid pg in pre-order.
	public static Queue<Integer> collectPreorder(GT<PGElem> pg) {
		if (pg == null || pg.empty())
			return new LinkedQueue<>();
		pg.findRoot();
		return collectPreorder(pg, pg.retrieve());
	}

	private static Queue<Integer> collectPreorder(GT<PGElem> pg, PGElem t) {
		int i = pg.nbChildren(), j = 0;
		Queue<Integer> temp = new LinkedQueue<>();
		temp.enqueue(t.getId());
		if (pg.nbChildren() == 0)
			return temp;
		while (j < i) {
			pg.findChild(j);
			flush(temp, collectPreorder(pg, pg.retrieve()));
			j++;
			pg.findParent();
		}
		return temp;
	}

	private static void flush(Queue<Integer> t1, Queue<Integer> t2) {
		if (t2 == null || t2.length() == 0)
			return;
		int j = 0;
		while (j < t2.length()) {
			t1.enqueue(t2.serve());
		}
	}

	// Searches the power grid pg for the element with ID id. If found, it is made
	// current and true is returned, otherwise false is returned.
	public static boolean find(GT<PGElem> pg, int id) {
		if (pg == null || pg.empty())
			return false;
		pg.findRoot();
		return find(pg, id, pg.retrieve());
	}

	private static boolean find(GT<PGElem> pg, int id, PGElem e) {
		if (pg.retrieve().getId() == id) {
			return true;
		}
		int i = pg.nbChildren(), j = 0;
		if (i == 0) {
			return false;
		}

		while (j < i) {
			pg.findChild(j);
			if (find(pg, id, pg.retrieve())) {
				return true;
			}
			j++;
			pg.findParent();
		}
		return false;
	}

	// Add the generator element gen to the power grid pg. This can only be done if
	// the grid is empty. If successful, the method returns true. If there is
	// already a generator, or gen is not of type Generator, false is returned.
	public static boolean addGenerator(GT<PGElem> pg, PGElem gen) {
		if (pg == null || gen == null)
			return false;
		if (!pg.empty() || gen.getType() != ElemType.Generator)
			return false;
		pg.insert(gen);
		return true;
	}

	// Attaches pgn to the element id and returns true if successful. Note that a
	// consumer can only be attached to a transmitter, and no element can be be
	// attached to it. The tree must not contain more than one generator located at
	// the root. If id does not exist, or there is already aelement with the same ID
	// as pgn, pgn is not attached, and the method retrurns false.
	public static boolean attach(GT<PGElem> pg, PGElem pgn, int id) {
		if (pg == null || pg.empty() || pgn == null)
			return false;
		if (find(pg, pgn.getId()) || !find(pg, id)) {
			return false;
		}
		find(pg, id);
		if (pg.retrieve().getType() == ElemType.Generator || pg.retrieve().getType() == ElemType.Transmitter) {
			if (pg.retrieve().getType() == ElemType.Generator && pgn.getType() == ElemType.Transmitter) {
				pg.insert(pgn);
				return true;
			}
			if (pg.retrieve().getType() == ElemType.Transmitter
					&& (pgn.getType() == ElemType.Consumer || pgn.getType() == ElemType.Transmitter)) {
				pg.insert(pgn);
				return true;
			}
		}
		return false;
	}

	// Removes element by ID, all corresponding subtree is removed. If removed, true
	// is returned, false is returned otherwise.
	public static boolean remove(GT<PGElem> pg, int id) {
		if (pg == null || pg.empty())
			return false;
		if (!find(pg, id)) {
			return false;
		}
		pg.remove();
		return true;
	}

	// Computes total power that consumed by a element (and all its subtree). If id
	// is incorrect, -1 is returned.
	public static double totalPower(GT<PGElem> pg, int id) {
		if (!find(pg, id)) {
			return -1;
		}
		return totalPower(pg, id, pg.retrieve());
	}

	private static double totalPower(GT<PGElem> pg, int id, PGElem t) {
		if (pg == null || pg.empty() || t == null)
			return -1;
		int i = pg.nbChildren(), j = 0;
		double total = 0;
		if (i == 0) {
			if (t.getType() == ElemType.Consumer)
				return t.getPower();
			return 0;
		}
		while (j < i) {
			pg.findChild(j);
			double tem = totalPower(pg, id, pg.retrieve());
			if (tem != -1)
				total += tem;
			j++;
			pg.findParent();
		}
		return total;
	}

	// Checks if the power grid contains an overload. The method returns the ID of
	// the first element preorder that has an overload and -1 if there is no
	// overload.
	public static int findOverload(GT<PGElem> pg) {
		if (pg == null || pg.empty())
			return -1;
		pg.findRoot();
		return findOverload(pg, pg.retrieve());
	}

	private static int findOverload(GT<PGElem> pg, PGElem t) {
		int i = pg.nbChildren(), j = 0;
		if (t.getPower() < totalPower(pg, t.getId()))
			return t.getId();
		while (j < i) {
			pg.findChild(j);
			int temp = pg.retrieve().getId();
			if (findOverload(pg, pg.retrieve()) != -1)
				return temp;
			j++;
			pg.findParent();
		}
		return -1;
	}

}
