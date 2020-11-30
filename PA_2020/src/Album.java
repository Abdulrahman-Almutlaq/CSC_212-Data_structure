public class Album {
	String name;
	String condition;
	PhotoManager manager;

	// Constructor
	public Album(String name, String condition, PhotoManager manager) {
		this.name = name;
		this.condition = condition;
		this.manager = manager;
	}

	// Return the name of the album
	public String getName() {
		return name;
	}

	// Return the condition associated with the album
	public String getCondition() {
		return condition;
	}

	// Return the manager
	public PhotoManager getManager() {
		return manager;
	}

	// Return all photos that satisfy the album condition
	public LinkedList<Photo> getPhotos() {
		String[] temp = condition.split(" AND ");
		BST<LinkedList<Photo>> bst = manager.bst;
		LinkedList<Photo> photos = new LinkedList<>();
		if (condition.length() == 0)
			return manager.photos;

		else if (temp.length != 1) {
			bst.findKey(temp[0]);
			LinkedList<Photo> ll = bst.retrieve();
			ll.findFirst();
			while (!ll.last()) {
				photos.insert(ll.retrieve());
				ll.findNext();
				;
			}
			photos.insert(ll.retrieve());

			LinkedList<Photo> ll_2 = new LinkedList<>();
			for (int i = 1; i < temp.length; i++) {
				bst.findKey(temp[i]);
				ll = bst.retrieve();
				ll.findFirst();
				while (!ll.last()) {
					ll_2.insert(ll.retrieve());
					ll.findNext();
					;
				}
				ll_2.insert(ll.retrieve());
				photos = getIntersection(photos, ll_2);
			}

		}

		else {
			bst.findKey(temp[0]);
			LinkedList<Photo> ll = bst.retrieve();
			ll.findFirst();
			while (!ll.last()) {
				if (!Exist(photos, ll.retrieve()))
					photos.insert(ll.retrieve());
				ll.findNext();
				;
			}
			if (!Exist(photos, ll.retrieve()))
				photos.insert(ll.retrieve());
		}
		return photos;
	}

	private LinkedList<Photo> getIntersection(LinkedList<Photo> photos, LinkedList<Photo> ll_2) {
		LinkedList<Photo> returned = new LinkedList<>();
		photos.findFirst();
		while (!photos.last()) {
			ll_2.findFirst();
			while (!ll_2.last()) {
				if (ll_2.retrieve().path.equalsIgnoreCase(photos.retrieve().path))
					returned.insert(ll_2.retrieve());
				ll_2.findNext();
			}
			if (ll_2.retrieve().path.equalsIgnoreCase(photos.retrieve().path))
				returned.insert(ll_2.retrieve());
			photos.findNext();
		}

		ll_2.findFirst();
		while (!ll_2.last()) {
			if (ll_2.retrieve().path.equalsIgnoreCase(photos.retrieve().path))
				returned.insert(ll_2.retrieve());
			ll_2.findNext();
		}
		if (ll_2.retrieve().path.equalsIgnoreCase(photos.retrieve().path))
			returned.insert(ll_2.retrieve());

		return returned;
	}

	private boolean Exist(LinkedList<Photo> photos, Photo p) {
		photos.findFirst();
		while (!photos.last())
			if (photos.retrieve().path.equalsIgnoreCase(p.path))
				return true;
		if (photos.retrieve().path.equalsIgnoreCase(p.path))
			return true;

		return false;
	}

	// Return the number of tag comparisons used to find all photos of the album
	public int getNbComps() {
		String[] temp = condition.split(" AND ");
		int counter = 0;
		for (int i = 0; i < temp.length; i++) {
			counter += manager.bst.NBComp(temp[i]);
		}
		return counter;
	}
}
