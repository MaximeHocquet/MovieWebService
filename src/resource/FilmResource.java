package resource ; 

import java.net.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import data.*;
import service.*;

@Path("/film")

public class FilmResource {
	
	FilmService service = new FilmService();
	
	@Context
	UriInfo uriInfo;

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	
	public Response addFilm(Film f) {
		
		Film film = service.addFilm(f);
		
		if (film == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		URI uri = uriInfo.getRequestUri();
		String newUri = uri.getPath() + "/" + film.getFilm_id();
		return Response.status(Response.Status.CREATED).entity(film).contentLocation(uri.resolve(newUri)).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	
	public Response deleteFilm(@PathParam("id") int id) {
		
		if (service.deleteFilm(id) == false) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		return Response.status(Response.Status.OK).build();
	}

	@GET
	@Path("/{title}")
	@Produces(MediaType.APPLICATION_XML)
	
	public Response getFilm(@PathParam("title") String title) {
		
		Film film = service.getFilm(title);
		
		if (film == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		Link link = Link.fromUri(uriInfo.getRequestUri()).rel("self").type("application/xml").build();
		return Response.status(Response.Status.OK).entity(film).links(link).build();
	}
}