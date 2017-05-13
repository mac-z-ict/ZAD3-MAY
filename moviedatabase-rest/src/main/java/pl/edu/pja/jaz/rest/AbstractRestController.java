package pl.edu.pja.jaz.rest;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.pja.jaz.domain.HasId;
import pl.edu.pja.jaz.service.Service;

public abstract class AbstractRestController<T extends HasId> implements RestController<T> {
	
	protected static final Response RESPONSE_404 = buildResponse(404);
	
	protected Service<T> service;

	public AbstractRestController(Service<T> service) {
		this.service = service;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Set<T> list() {
		return service.list();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public T create(T entity) {
		return service.create(entity);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public T get(@PathParam("id") int id) {
		return service.get(id);
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public T update(@PathParam("id") int id, T entity) {
		return service.update(id, entity);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") int id) {
		service.delete(id);
	}
	
	protected static Response buildResponse(int code) {
		return Response.status(code).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

}