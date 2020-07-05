
public class MovieBygenres implements Cond<Movie> {
	private List<String> genres;

	public MovieBygenres(List<String> genres) {
		if(genres==null||genres.empty())
			return;
		this.genres = genres;
	}

	@Override
	public boolean test(Movie e) {
		if(genres==null||e==null)
			return false;
		
		genres.findFirst();
		MovieByGenre cnd = new MovieByGenre(genres.retrieve());
		while (!genres.last()) {
			if (!cnd.test(e)) {
				return false;
			}
			genres.findNext();
			cnd.genre = genres.retrieve();
		}
		cnd.genre = genres.retrieve();
		if (cnd.test(e))
			return true;
		return false;

	}

}
