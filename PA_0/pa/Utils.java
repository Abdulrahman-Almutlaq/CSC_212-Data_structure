
public class Utils {
	// Return the reverse of s without changing s.
	public static <U extends Separable<U>> U reverse(U s) {
		if(s==null || s.length()<=0)
			return s; 
		if (s.length() == 1)
			return s;
		return s.concat(reverse(s.rest()), s.first());
	}

	// Return the last part of s without changing s.
	public static <U extends Separable<U>> U last(U s) {
		if(s==null || s.length()<=0)
			return s; 
		if (s.length() == 1)
			return s;
		return last(s.rest());

	}
}
