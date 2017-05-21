package tech.simter.demo;

import tech.simter.vo.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

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

  @GET
  @Path("user")
  @Produces(MediaType.APPLICATION_JSON)
  List<User> demoUser();
}