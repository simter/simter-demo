package tech.simter.demo.rest.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author RJ
 */
@Path("/")
public interface RootResource {
  @GET
  String root();
}