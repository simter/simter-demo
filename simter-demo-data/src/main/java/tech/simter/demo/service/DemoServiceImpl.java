package tech.simter.demo.service;

import tech.simter.demo.dao.DemoDao;
import tech.simter.demo.po.Demo;
import tech.simter.meta.service.MetaService;
import tech.simter.persistence.CommonState;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author RJ
 */
@Named
@Singleton
public class DemoServiceImpl implements DemoService {
  @Inject
  private MetaService metaService;
  @Inject
  private DemoDao demoDao;

  @Override
  public Demo get(Integer id) {
    return demoDao.get(id);
  }

  @Override
  public List<Demo> find(CommonState status) {
    return demoDao.find(status);
  }

  @Override
  @Transactional
  public Demo save(Demo demo) {
    Demo savedDemo = demoDao.save(demo);
    if (demo.id == null) metaService.addCreation(Demo.class, savedDemo.id);
    else metaService.addModification(Demo.class, savedDemo.id);
    return savedDemo;
  }

  @Override
  @Transactional
  public void delete(Integer... ids) {
    demoDao.delete(ids);
  }
}
