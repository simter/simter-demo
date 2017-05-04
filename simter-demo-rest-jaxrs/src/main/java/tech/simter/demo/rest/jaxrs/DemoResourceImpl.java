package tech.simter.demo.rest.jaxrs;

import tech.simter.demo.po.Demo;
import tech.simter.demo.service.DemoService;
import tech.simter.persistence.CommonState;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;

/**
 * @author RJ
 */
@Named
@Singleton
public class DemoResourceImpl implements DemoResource {
  @Inject
  private DemoService demoService;

  @Override
  public Demo get(Integer id) {
    // just for test
    if (id.equals(-1)) throw new SecurityException();
    if (id.equals(0)) throw new SecurityException("permitted");

    // real
    return demoService.get(id);
  }

  @Override
  public List<Demo> find(CommonState status) {
    return demoService.find(status);
  }

  @Override
  public void delete(Integer id) {
    demoService.delete(id);
  }

  @Override
  public void delete(Integer[] ids) {
    demoService.delete(ids);
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