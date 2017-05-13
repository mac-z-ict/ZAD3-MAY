package pl.edu.pja.jaz.dao;

import java.util.Set;

import pl.edu.pja.jaz.domain.Actor;
import pl.edu.pja.jaz.domain.Movie;

public class ActorDao extends AbstractDao<Actor> {

	@Override
	public Set<Actor> list() {
		return db.getActors();
	}

	@Override
	protected void copy(Actor source, Actor destination) {
		for (Movie movie : destination.getMovies()) {
			movie.getActors().remove(destination);
		}
		destination.getMovies().clear();
		
		for (Movie movie : source.getMovies()) {
			movie.getActors().add(destination);
		}
		destination.getMovies().addAll(source.getMovies());
		
		destination.setFirstName(source.getFirstName());
		destination.setLastName(source.getLastName());
	}

	@Override
	protected Actor newInstance(int id) {
		return new Actor(id);
	}

}
