package tech.simter.demo.rest.jaxrs.javatime;

import java.util.List;

/**
 * @author RJ
 */
public class ParentDto {
  public String name;
  public ChildDto firstChild;
  public List<ChildDto> children;
}
