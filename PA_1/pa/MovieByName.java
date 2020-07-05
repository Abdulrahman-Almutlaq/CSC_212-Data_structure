
public class MovieByName implements Cond<Movie> {
	private String title;

	public MovieByName(String title) {
		this.title = title;
	}

	@Override
	public boolean test(Movie e) {
		return title.equals(e.title);
	}
}
