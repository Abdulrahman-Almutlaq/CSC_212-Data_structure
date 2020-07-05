
public class ArrayList<T> implements List<T> {
	T data[];
	int counter;
	int current;

	@SuppressWarnings("unchecked")
	public ArrayList(int size) {
		data = (T[]) new Object[size];
		counter = 0;
		current = -1;
	}

	@Override
	public boolean empty() {
		return counter == 0;
	}

	@Override
	public boolean full() {
		return counter == data.length;
	}

	@Override
	public void findFirst() {
		current = 0;
	}

	@Override
	public void findNext() {
		current++;
	}

	@Override
	public boolean last() {
		return counter - 1 == current;
	}

	@Override
	public T retrieve() {
		return data[current];
	}

	@Override
	public void update(T e) {
		data[current] = e;
	}

	@Override
	public void insert(T e) {
		if(counter==data.length)
			return;
		for(int i =counter-1;i>current;--i) {
			data[i+1]=data[i];			
		}
		current++;
		data[current] =e;
		counter++;
	}

	@Override
	public void remove() {
		if(counter==0)
			return;
		if (counter == 1 || current == counter - 1) {
			data[current] = null;
			if (counter - 1 == current) {
				current = 0;
				counter--;
			} else {
				current = -1;
				counter--;
			}
		}
		else {
		for (int i = current; i < counter - 1; i++) {
			data[i] = data[i + 1];
		}
		counter--;
		}
	}

	@Override
	public boolean findFirstEle(Cond<T> cnd) {
		for (int i = 0; i < counter; i++)
			if (cnd.test(data[i])) {
				current = i;
				return true;
			}
		return false;
	}

	@Override
	public List<T> findAllEle(Cond<T> cnd) {
		List<T> temp = new ArrayList<>(data.length);
		for (int i = 0; i < counter; i++)
			if (cnd.test(data[i]))
				temp.insert(data[i]);
		return temp;
	}

}
