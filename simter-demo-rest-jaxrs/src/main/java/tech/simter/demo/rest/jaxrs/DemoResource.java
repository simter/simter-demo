package tech.simter.demo.rest.jaxrs;

import tech.simter.demo.po.Demo;
import tech.simter.persistence.CommonState;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author RJ
 */
@Path("/demo")
public interface DemoResource {
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  Demo get(@PathParam("id") @NotNull Integer id);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  List<Demo> find(@QueryParam("status") CommonState status);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Integer createByJson(@NotNull Demo demo);

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  Integer createByForm(@FormParam("name") @NotNull String name,
                       @FormParam("status") @NotNull CommonState status,
                       @FormParam("remark") String remark);
}