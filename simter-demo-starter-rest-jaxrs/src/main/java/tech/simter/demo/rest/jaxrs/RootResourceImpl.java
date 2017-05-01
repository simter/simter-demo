package tech.simter.demo.rest.jaxrs;

import org.springframework.stereotype.Component;

/**
 * @author RJ
 */
@Component
public class RootResourceImpl implements RootResource {
  @Override
  public String root() {
    return "A Simter Demo.";
  }
}