package pl.edu.pja.jaz.dao;

import java.util.Set;

import pl.edu.pja.jaz.domain.Actor;
import pl.edu.pja.jaz.domain.Movie;

public class MovieDao extends AbstractDao<Movie> {
	
	@Override
	public Set<Movie> list() {
		return db.getMovies();
	}

	@Override
	protected void copy(Movie source, Movie destination) {
		for (Actor actor : destination.getActors()) {
			actor.getMovies().remove(destination);
		}
		destination.getActors().clear();
		
		for (Actor actor : source.getActors()) {
			actor.getMovies().add(destination);
		}
		destination.getActors().addAll(source.getActors());
		
		destination.setTitle(source.getTitle());
		destination.getRates().clear();
		destination.getRates().addAll(source.getRates());
		destination.getComments().clear();
		destination.getComments().addAll(source.getComments());
	}

	@Override
	protected Movie newInstance(int id) {
		return new Movie(id);
	}
	
}
