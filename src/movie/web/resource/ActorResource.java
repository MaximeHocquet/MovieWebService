package movie.web.resource ; 

import java.net.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import movie.web.data.*;
import movie.web.service.*;

@Path("/actor")

/**
* 
* @author Alexandre GOMES DA COSTA <alexandre.gomes-da-costa@etu.cyu.fr> & Maxime HOCQUET <maxime.hocquet@etu.cyu.fr>
*
*/

public class ActorResource {
	
	ActorService service = new ActorService();
	
	@Context
	UriInfo uriInfo;

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	
	/**
	* 
	* @param a L'acteur que l'on souhaite ajouter au service
	* @return La réponse à la requête d'ajout de l'acteur a
	*
	*/
	
	public Response addActor(Actor a) {
		
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
	
	/**
	* 
	* @param id L'identifiant de l'acteur que l'on souhaite supprimer du service
	* @return La réponse à la requête de suppresion de l'acteur dont l'identifiant est id
	*
	*/
	
	public Response deleteActor(@PathParam("id") int id) {
		
		if (service.deleteActor(id) == false) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		return Response.status(Response.Status.OK).build();
	}

	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_XML)
	
	/**
	* 
	* @param name Le nom de l'acteur que l'on souhaite récupérer à partir du service
	* @return La réponse à la requête de récupération de l'acteur dont le nom est name
	*
	*/
	
	public Response getActor(@PathParam("name") String name) {
		
		Actor actor = service.getActor(name);
		
		if (actor == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		Link link = Link.fromUri(uriInfo.getRequestUri()).rel("self").type("application/xml").build();
		return Response.status(Response.Status.OK).entity(actor).links(link).build();
	}
}