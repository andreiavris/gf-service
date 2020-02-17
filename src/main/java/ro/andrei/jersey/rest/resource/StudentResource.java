package ro.andrei.jersey.rest.resource;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ro.andrei.jersey.rest.dto.Student;
import ro.andrei.jersey.service.StudentService;

@Path("student")
public class StudentResource {

    @EJB
    private StudentService studentService;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
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
    public Response getById(@PathParam("id") Long id) {
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
