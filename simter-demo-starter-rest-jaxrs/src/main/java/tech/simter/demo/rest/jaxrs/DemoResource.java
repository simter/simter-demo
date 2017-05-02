package tech.simter.demo.rest.jaxrs;

import tech.simter.demo.po.Demo;

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
  @Path("/")
  List<Demo> find();

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/")
  Integer create(@FormParam("name") @NotNull String name, @FormParam("remark") String remark);
}