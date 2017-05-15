package tech.simter.demo.rest.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.awt.*;

/**
 * @author RJ
 */
@Path("/")
public interface RootResource {
  @GET
  String root();

  @GET
  @Path("data/grid.json")
  @Produces(MediaType.APPLICATION_JSON)
  String vueComponentsTestData4Grid(@QueryParam("pageNo") int pageNo,
                                    @QueryParam("pageSize") int pageSize,
                                    @QueryParam("query") String query);
}