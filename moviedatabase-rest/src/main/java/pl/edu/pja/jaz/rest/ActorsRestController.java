package pl.edu.pja.jaz.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.pja.jaz.dao.ActorDao;
import pl.edu.pja.jaz.dao.MovieDao;
import pl.edu.pja.jaz.domain.Actor;
import pl.edu.pja.jaz.domain.Movie;
import pl.edu.pja.jaz.service.ActorService;
import pl.edu.pja.jaz.service.MovieService;

@Path("/actors")
public class ActorsRestController extends AbstractRestController<Actor> {
	
	protected final ActorService actorService;

	public ActorsRestController() {
		super(new ActorService(new ActorDao(), new MovieService(new MovieDao())));
		actorService = (ActorService) service;
	}
	
	@Override
	public void delete(int id) {
		Actor actor = get(id);
		if (actor == null) {
			return;
		}
		
		for (Movie movie : actor.getMovies()) {
			movie.getActors().remove(actor);
		}
		super.delete(id);
	}
	
	@GET
	@Path("/{id}/movies")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listMovies(@PathParam("id") int id) {
		Actor a = get(id);
		if (a == null) {
			return RESPONSE_404;
		}
		
		return Response.ok(a.getMovies()).build();
	}
	
	@POST
	@Path("/{id}/movies")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response assignMovie(@PathParam("id") int id, int movieId) {
		Actor a = get(id);
		if (a == null) {
			return RESPONSE_404;
		}
		return Response.ok(actorService.assignMovie(id, movieId)).build();
	}

}
