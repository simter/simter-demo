package tech.simter.demo.rest.jaxrs;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author RJ
 */
@Named
@Singleton
public class RootResourceImpl implements RootResource {
  @Override
  public String root() {
    return "A Simter Demo.";
  }
}