package tech.simter.demo.rest.jaxrs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.simter.demo.po.Demo;
import tech.simter.demo.service.DemoService;
import tech.simter.persistence.CommonState;

import java.util.List;

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
    return demo;
  }

  @Override
  public List<Demo> find(CommonState status) {
    return demoService.find(status);
  }

  @Override
  public Integer createByJson(Demo demo) {
    return demoService.save(demo).id;
  }

  @Override
  public Integer createByForm(String name, CommonState status, String remark) {
    Demo demo = new Demo();
    demo.name = name;
    demo.status = status;
    demo.remark = remark;
    demo = demoService.save(demo);
    return demo.id;
  }
}