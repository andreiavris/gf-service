package ro.andrei.jersey.rest.resource;

import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ro.andrei.jersey.rest.exception.ExampleException;
import ro.andrei.jersey.rest.dto.Student;
import ro.andrei.jersey.service.StudentService;

@Path("student")
public class StudentResource {

    @EJB
    private StudentService studentService;

    @Context
    private HttpHeaders httpHeaders;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Student> getAll() {

        try {
            return studentService.getAll();
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            //log accordingly, not like this
            e.printStackTrace();
            throw new InternalServerErrorException();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") Long id, @QueryParam("exception") boolean exception, @Context HttpServletRequest request) {
        System.out.println(request.getRequestURI());
        System.out.println(String.format("headers: %s%n", httpHeaders.getRequestHeaders()));

        if(exception) {
            throw new ExampleException();
        }

        try {
            return Response.ok(studentService.getById(id)).build();
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            //log accordingly, not like this
            e.printStackTrace();
            throw new InternalServerErrorException();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createStudent(Student student) {
        try {
            studentService.createStudent(student);
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            //log accordingly, not like this
            e.printStackTrace();
            throw new InternalServerErrorException();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        try {
            studentService.deleteById(id);
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            //log accordingly, not like this
            e.printStackTrace();
            throw new InternalServerErrorException();
        }
        return Response.ok().build();
    }

}
