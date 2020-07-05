class GArray<T> implements Separable<GArray<T>> {
	private T[] data;

	@SuppressWarnings("unchecked")
	public GArray(int n) {
		if (n < 0) {
			data=null;
			return;
		}
		data = (T[]) new Object[n];
	}

	// Return the element at position i
	public T get(int i) {
		if(data == null|| data.length==0)
			return null;
		if (i >= 0 || i < data.length)
			return data[i];
		return null;
	}

	// Set the element at position i
	public void set(int i, T e) {
		if(data==null || data.length==0)
			return ;
		if (i <0 || i>=data.length)
			return;
			data[i] = e;
	}

	@Override
	public int length() {
		if(data==null)
			return -1;
		return data.length; // Change this
	}

	@Override
	public GArray<T> first() {
		if (this.length() <= 0) 
			return this;
			GArray<T> temp = new GArray<T>(1);
		temp.set(0, data[0]);
		return temp; // Changed
	}

	@Override
	public GArray<T> rest() {
		if (this.length() == 0) 
			return this;		
		if(this.length()>=1) {
		GArray<T> temp = new GArray<T>(data.length - 1);
		for (int i = 0; i < temp.length(); i++)
			temp.set(i, data[i + 1]);
		return temp;
		}
		return this;
		
	}

	@Override
	public GArray<T> concat(GArray<T> s1, GArray<T> s2) {
		int starter = 0;
		GArray<T> temp = new GArray<>(s1.length() + s2.length());
		for (int i = 0; i < s1.length(); i++) {
			temp.set(i, s1.get(i));
			if (i == s1.length() - 1)
				starter = i;
		}

		for (int j = 0; j < s2.length(); j++) {
			temp.set(j + starter + 1, s2.get(j));
		}
		return temp;
	}
}
