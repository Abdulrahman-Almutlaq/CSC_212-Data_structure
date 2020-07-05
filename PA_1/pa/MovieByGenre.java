public class MovieByGenre implements Cond<Movie> {
	public String genre;

	public MovieByGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public boolean test(Movie e) {
		if (e == null||e.genres==null)
			return false;
		List<String> genres = e.genres;
		StringEqualCond cnd = new StringEqualCond(genre);
		return genres.findFirstEle(cnd);
	}
}
