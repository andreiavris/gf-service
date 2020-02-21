package ro.andrei.jersey.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExampleExceptionMapper implements ExceptionMapper<ExampleException> {

    @Override
    public Response toResponse(ExampleException e) {
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
}
