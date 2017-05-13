package pl.edu.pja.jaz.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.pja.jaz.dao.MovieDao;
import pl.edu.pja.jaz.domain.Actor;
import pl.edu.pja.jaz.domain.Comment;
import pl.edu.pja.jaz.domain.Movie;
import pl.edu.pja.jaz.service.MovieService;

@Path("/movies")
public class MoviesRestController extends AbstractRestController<Movie> {
	
	protected final MovieService movieService;
	
	public MoviesRestController() {
		super(new MovieService(new MovieDao()));
		movieService = (MovieService) service;
	}
	
	@GET
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public void insert() {
		for(int i = 0; i < 10; i++) {
			Movie m = new Movie();
			m.setTitle("test movie " + (i+1));
			service.create(m);
		}
	}
	
	@Override
	public void delete(int id) {
		Movie movie = get(id);
		if (movie == null) {
			return;
		}
		
		for (Actor actor : movie.getActors()) {
			actor.getMovies().remove(movie);
		}
		super.delete(id);
	}
	
	@GET
	@Path("/{id}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listComments(@PathParam("id") int id) {
		Movie m = get(id);
		if (m == null) {
			return RESPONSE_404;
		}
		return Response.ok(m.getComments()).build();
	}
	
	@POST
	@Path("/{id}/comments")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createComment(@PathParam("id") int id, Comment comment) {
		Movie m = get(id);
		if (m == null) {
			return RESPONSE_404;
		}
		
		Comment newComment = movieService.createComment(id, comment);
		return Response.ok(newComment).build();
	}
	
	@DELETE
	@Path("/{id}/comments/{commentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteComments(@PathParam("id") int id, @PathParam("commentId") int commentId) {
		Movie m = get(id);
		if (m == null) {
			return RESPONSE_404;
		}
		
		return Response.ok(movieService.deleteComment(id, commentId)).build();
	}
	
	@GET
	@Path("/{id}/actors")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listActors(@PathParam("id") int id) {
		Movie m = get(id);
		if (m == null) {
			return RESPONSE_404;
		}
		return Response.ok(m.getActors()).build();
	}
	
	@POST
	@Path("/{id}/rate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response rate(@PathParam("id") int id, int rating) {
		Movie m = get(id);
		if (m == null) {
			return RESPONSE_404;
		}
		return Response.ok(movieService.rate(id, rating)).build();
	}
	
}
