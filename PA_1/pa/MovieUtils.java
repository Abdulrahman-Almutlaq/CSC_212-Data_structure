public class MovieUtils {
	// Return the movie with the given id if found, null otherwise.
	public static Movie findMovieById(List<Movie> movies, int id) {
		if(movies == null||movies.empty())
			return null;
		Cond<Movie> cnd = new MovieById(id);
		if (movies.findFirstEle(cnd))
			return movies.retrieve();
		else
			return null;
	}

	// Return the list of movies having a given title. If none is found, empty list
	// is returned.
	public static List<Movie> findMovieByTitle(List<Movie> movies, String title) {
		if (title == null || movies==null)
			return movies;
		if(movies.empty())
			return new LinkedList<>();
		Cond<Movie> cnd = new MovieByName(title);
		if (movies.findFirstEle(cnd))
			return movies.findAllEle(cnd);
		else
			return new LinkedList<>();

	}

	// Return the list of movies of a given genre. If none is found, empty list is
	// returned.
	public static List<Movie> findMoviesByGenre(List<Movie> movies, String genre) {
		if (genre == null || movies==null)
			return movies;
		Cond<Movie> cnd = new MovieByGenre(genre);
		List<Movie> temp = new LinkedList<>();
		if (movies.empty())
			return temp;
		if (movies.findFirstEle(cnd))
			return movies.findAllEle(cnd);
		else
			return temp;
	}

	// Return the list of movies of given genres (a movie must have all genres to be
	// in the list). If none is found, empty list is returned. Assume genres is not
	// empty.
	public static List<Movie> findMoviesByGenres(List<Movie> movies, List<String> genres) {
		if (genres==null || genres.empty()|| movies==null)
			return movies;
		Cond<Movie> cnd = new MovieBygenres(genres);
		List<Movie> temp = new LinkedList<>();
		if (movies.empty())
			return temp;
		if (movies.findFirstEle(cnd))
			return movies.findAllEle(cnd);
		else
			return temp;

	}
}
