package pl.edu.pja.jaz.dao;

import java.util.HashSet;
import java.util.Set;

import pl.edu.pja.jaz.domain.Actor;
import pl.edu.pja.jaz.domain.Movie;

public class Db {
	
	public static final Db INSTANCE = new Db();
	
	private Db() {}
	
	private final Set<Movie> movies = new HashSet<>();
	private final Set<Actor> actors = new HashSet<>();

	public final Set<Movie> getMovies() {
		return movies;
	}
	
	public final Set<Actor> getActors() {
		return actors;
	}
	
}
