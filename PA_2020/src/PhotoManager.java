public class PhotoManager {
	// Constructor

	BST<LinkedList<Photo>> bst;
	LinkedList<Photo> photos;

	public PhotoManager() {
		bst = new BST<>();
		photos = new LinkedList<>();
	}

	// Add a photo
	public void addPhoto(Photo p) {
		// checking if p exists or not
		photos.findFirst();
		while (!photos.last()) {
			if (photos.retrieve().path.equalsIgnoreCase(p.path))
				return;
			photos.findNext();
		}
		if (photos.retrieve().path.equalsIgnoreCase(p.path))
			return;
		// done checking

		LinkedList<String> temp = p.tags;
		temp.findFirst();

		while (!temp.last()) {
			bst.insert(temp.retrieve(), new LinkedList<Photo>());// duplicate key will be rejected so no worries
			temp.findNext();
		}
		bst.insert(temp.retrieve(), new LinkedList<Photo>());

		temp.findFirst();
		while (!temp.last()) {
			bst.findKey(temp.retrieve());
			bst.retrieve().insert(p);
			temp.findNext();
		}
		bst.findKey(temp.retrieve());
		bst.retrieve().insert(p);

		photos.insert(p);
	}

	// Delete a photo
	public void deletePhoto(String path) {
		Photo deleted = null;
		photos.findFirst();
		while (!photos.last()) {
			if (photos.retrieve().path.equalsIgnoreCase(path)) {
				deleted = photos.retrieve();
				photos.remove();
			}
			photos.findNext();
		}
		if (photos.last())
			if (!photos.retrieve().path.equalsIgnoreCase(path))
				return;
			else {
				deleted = photos.retrieve();
				photos.remove();
			}

		LinkedList<String> temp = deleted.tags;
		temp.findFirst();
		while (!temp.last()) {
			bst.findKey(temp.retrieve());
			LinkedList<Photo> ll = bst.retrieve();

			ll.findFirst();
			while (!ll.last()) {
				if (ll.retrieve().path.equalsIgnoreCase(path))
					ll.remove();
				else
					ll.findNext();
			}

			if (ll.retrieve().path.equalsIgnoreCase(path))
				ll.remove();

			temp.findNext();
		}

		bst.findKey(temp.retrieve());
		LinkedList<Photo> ll = bst.retrieve();

		ll.findFirst();
		while (!ll.last()) {
			if (ll.retrieve().path.equalsIgnoreCase(path))
				ll.remove();
			else
				ll.findNext();
		}

		if (ll.retrieve().path.equalsIgnoreCase(path))
			ll.remove();
		Flush();

	}

	private void Flush() {
		LinkedList<LinkedList<Photo>> vals = bst.getVals();
		LinkedList<String> keys = bst.getKeys();
		vals.findFirst();
		keys.findFirst();
		while (!keys.last()) {
			if (vals.retrieve().empty()) {
				keys.remove();
				vals.remove();
			} else {
				keys.findNext();
				vals.findNext();
			}
		}
		if (vals.retrieve().empty()) {
			keys.remove();
			vals.remove();
		} else {
			keys.findNext();
			vals.findNext();
		}
	}

	// Return the inverted index of all managed photos
	public BST<LinkedList<Photo>> getPhotos() {
		return bst;
	}
}
