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
		
		if(temp.length!=1)
		for(int i=0;i<temp.length;i++) {
			bst.findKey(temp[i]);
			LinkedList<Photo> ll = bst.retrieve();
			ll.findFirst();
			while(!ll.last()) {
				photos.insert(ll.retrieve());
				ll.findNext();;
			}
			photos.insert(ll.retrieve());
		}
		else 
			photos = manager.photos;
		return photos;
	}

	// Return the number of tag comparisons used to find all photos of the album
	public int getNbComps() {
		String[] temp = condition.split(" AND ");
		int counter=0;
		for(int i=0;i<temp.length;i++) {
			counter += manager.bst.NBComp(temp[i]); 
		}
		return counter;
	}
}
