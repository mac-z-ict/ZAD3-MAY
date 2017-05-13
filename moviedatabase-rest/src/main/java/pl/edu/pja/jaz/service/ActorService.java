package pl.edu.pja.jaz.service;

import pl.edu.pja.jaz.dao.Dao;
import pl.edu.pja.jaz.domain.Actor;
import pl.edu.pja.jaz.domain.Movie;

public class ActorService extends AbstractService<Actor> {
	
	protected final Service<Movie> movieService;

	public ActorService(Dao<Actor> dao, Service<Movie> movieService) {
		super(dao);
		this.movieService = movieService;
	}
	
	public Actor assignMovie(int id, int movieId) {
		Actor a = get(id);
		Movie m = movieService.get(movieId);
		if (a == null || m == null) {
			return null;
		}
		
		a.getMovies().add(m);
		m.getActors().add(a);
		return a;
	}

}
