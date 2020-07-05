import java.io.File;
import java.util.Scanner;

public class Recommender {

	// Return the top k recommended friends for user i using the popular nodes
	// method. If i does not exist, return null. In case of a tie, users with the
	// lowest id are selected.
	public static <K extends Comparable<K>> PQK<Double, K> recommendPop(Graph<K> g, K i, int k) {
		try {
		if(g==null)
			return new PQKImp<>(k);
		
		if (!g.isNode(i)||k<0) {
			return null;
		}

		List<K> l = g.getNodes();
		if (l.exists(i)) {
			l.findFirst();
			while (!l.last()) {
				if (l.retrieve().compareTo(i) == 0) {
					l.remove();
					break;
				}
				l.findNext();
			}
			if (l.retrieve().compareTo(i) == 0)
				l.remove();

		}
		K t;
		List<K> l2 = g.neighb(i);
		
		l2.findFirst();
		while (!l2.last()) {
			if (l.exists(t = l2.retrieve())) {
				l.findFirst();
				while (!l.last()) {
					if (l.retrieve().compareTo(t) == 0) {
						l.remove();
						break;
					}
					l.findNext();
				}
				if (l.retrieve().compareTo(t) == 0)
					l.remove();
			}
			l2.findNext();
		}
		if (l.exists(l2.retrieve()))
			if (l.exists(t = l2.retrieve())) {
				l.findFirst();
				while (!l.last()) {
					if (l.retrieve().compareTo(t) == 0) {
						l.remove();
						break;
					}
					l.findNext();
				}
				if (l.retrieve().compareTo(t) == 0)
					l.remove();
			}

		PQKImp<Double, K> p = new PQKImp<>(k);
		l.findFirst();
		while (!l.last()) {
			p.enqueue((double) g.deg(l.retrieve()), l.retrieve());
			l.findNext();
		}
		p.enqueue((double) g.deg(l.retrieve()), l.retrieve());

		return p;
		}
		catch (Exception e) {
			return new PQKImp<>(k);
		}
	}

	// Return the top k recommended friends for user i using common neighbors
	// method. If i does not exist, return null. In case of a tie, users with the
	// lowest id are selected.
	public static <K extends Comparable<K>> PQK<Double, K> recommendCN(Graph<K> g, K i, int k) {
		try {
		if(g==null)
			return new PQKImp<>(k);
		if (!g.isNode(i)||k<0)
			return null;
		List<K> l = g.getNodes();
		if(l.empty())
			return new PQKImp<>(k);
		if (l.exists(i)) {
			l.findFirst();
			while (!l.last()) {
				if (l.retrieve().compareTo(i) == 0) {
					l.remove();
					break;
				}
				l.findNext();
			}
			if (l.retrieve().compareTo(i) == 0)
				l.remove();
		}
		List<K> l2 = g.neighb(i);
		if(l2.empty())
			return new PQKImp<>(k);
		l2.findFirst();
		K t;
		while (!l2.last()) {
			if (l.exists(t = l2.retrieve())) {
				l.findFirst();
				while (!l.last()) {
					if (l.retrieve().compareTo(t) == 0) {
						l.remove();
						break;
					}
					l.findNext();
				}
				if (l.retrieve().compareTo(t) == 0)
					l.remove();
			}
			l2.findNext();
		}
		if (l.exists(t = l2.retrieve())) {
			l.findFirst();
			while (!l.last()) {
				if (l.retrieve().compareTo(t) == 0) {
					l.remove();
					break;
				}
				l.findNext();
			}
			if (l.retrieve().compareTo(t) == 0)
				l.remove();
		}
		PQKImp<Double, K> p = new PQKImp<>(k);
		l.findFirst();
		while (!l.last()) {
			List<K> l3 = g.neighb(l.retrieve());
			if (!l3.empty()) {
				double c = 0;
				l3.findFirst();
				while (!l3.last()) {
					if (l2.exists(l3.retrieve()))
						c++;
					l3.findNext();
				}
				if (l2.exists(l3.retrieve()))
					c++;
				p.enqueue(c, l.retrieve());
			}
			l.findNext();
		}
		List<K> l3 = g.neighb(l.retrieve());
		if (!l3.empty()) {
			double c = 0;
			l3.findFirst();
			while (!l3.last()) {
				if (l2.exists(l3.retrieve()))
					c++;
				l3.findNext();
			}
			if (l2.exists(l3.retrieve()))
				c++;
			p.enqueue(c, l.retrieve());
		}
		l.findNext();
		return p;
		}
		catch (Exception e) {
			return new PQKImp<>(k);
		}
	}

	// Read graph from file. The file is a text file where each line contains an
	// edge. The end and start of the edge are separated by space(s) or tabs.
	public static Graph<Integer> read(String fileName) {

		try {
			Graph<Integer> g = new MGraph<Integer>();
			Scanner scanner = new Scanner(new File(fileName));
			while (scanner.hasNextInt()) {
				int i = scanner.nextInt();
				g.addNode(i);
				int j = scanner.nextInt();
				g.addNode(j);
				g.addEdge(i, j);
			}
			scanner.close();
			return g;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
