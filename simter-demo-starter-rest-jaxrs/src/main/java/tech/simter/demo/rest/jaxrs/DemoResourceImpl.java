package tech.simter.demo.rest.jaxrs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.simter.demo.po.Demo;
import tech.simter.demo.service.DemoService;

/**
 * @author RJ
 */
@Component
public class DemoResourceImpl implements DemoResource {
  @Autowired
  private DemoService demoService;

  @Override
  public Demo get(Integer id) {
    // just for test
    if (id.equals(-1)) throw new SecurityException();
    if (id.equals(0)) throw new SecurityException("permitted");

    // real
    Demo demo = demoService.get(id);
    com.owlike.genson.ext.jaxrs.GensonJsonConverter c;
    return demo;
  }

  @Override
  public Integer create(String name, String remark) {
    Demo demo = new Demo();
    demo.name = name;
    demo.remark = remark;
    demo = demoService.save(demo);
    return demo.id;
  }
}