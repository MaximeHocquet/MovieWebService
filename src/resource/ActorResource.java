package resource ; 

import java.net.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import data.*;
import service.*;

@Path("/actor")

public class ActorResource {
	
	ActorService service = new ActorService();
	
	@Context
	UriInfo uriInfo;

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	
	public Response addFilm(Actor a) {
		
		Actor actor = service.addActor(a);
		
		if (actor == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		URI uri = uriInfo.getRequestUri();
		String newUri = uri.getPath() + "/" + actor.getActor_id();
		return Response.status(Response.Status.CREATED).entity(actor).contentLocation(uri.resolve(newUri)).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	
	public Response deleteActor(@PathParam("id") int id) {
		
		if (service.deleteActor(id) == false) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		return Response.status(Response.Status.OK).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	
	public Response getActor(@PathParam("id") int id) {
		
		Actor actor = service.getActor(id);
		
		if (actor == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		Link link = Link.fromUri(uriInfo.getRequestUri()).rel("self").type("application/xml").build();
		return Response.status(Response.Status.OK).entity(actor).links(link).build();
	}
}