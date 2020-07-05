
public class MovieById implements Cond<Movie> {
	private int id;

	public MovieById(int id) {
		this.id = id;
	}

	@Override
	public boolean test(Movie e) {
		return id == e.id;
	}

}
