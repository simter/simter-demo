package tech.simter.demo.rest.jaxrs.javatime;

import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author RJ
 */
@Named
@Path("/ym")
public class YearMonthResource {
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public TestDto test(@NotNull TestDto dto) {
    return dto;
  }
}
