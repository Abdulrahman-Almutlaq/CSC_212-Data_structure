public class StringEqualCond implements Cond<String> {
	private String str;

	public StringEqualCond(String str) {
		this.str = str;
	}

	@Override
	public boolean test(String s) {
		return str.equals(s);
	}
}
