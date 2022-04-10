package project.ressource; 
import java.net.*; import javax.ws.rs.*;
import javax.ws.rs.core.*;

import project.data.User;
import project.service.UserService;

@Path("/users") public class UserResource { 
	UserService service = new UserService(); 
	@Context UriInfo uriInfo;
	@POST @Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.APPLICATION_XML) 
	public Response addStudent(User s) {
		User user = service.addStudent(s);
		if(user == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		URI uri = uriInfo.getRequestUri(); 
		String newUri = uri.getPath() + "/" + user.getId(); 
		return Response.status(Response.Status.CREATED) .entity(user) .contentLocation(uri.resolve(newUri)) .build(); 
		}

@DELETE @Path("/{id}")
@Produces(MediaType.APPLICATION_XML) 
public Response deleteStudent(@PathParam("id") int id) {
	if(service.deleteStudent(id) == false) { 
		return Response.status(Response.Status.NOT_FOUND).build(); 
	} 
	return Response.status(Response.Status.OK).build(); 
}
@GET @Path("/{id}") 
@Produces(MediaType.APPLICATION_XML) 
public Response getStudent(@PathParam("id") int id , String name) { 
	User student = service.getUser(id, name); 
	if(student == null) { 
		return Response.status(Response.Status.NOT_FOUND).build(); 
	} 
	Link link = Link.fromUri(uriInfo.getRequestUri()).rel("self").type("application/xml").build(); 
	return Response.status(Response.Status.OK).entity(student).links(link).build();
	} 
}