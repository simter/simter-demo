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
@Path("/nested-dto")
public class NestedDtoResource {
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public ParentDto test(@NotNull ParentDto dto) {
    return dto;
  }
}
