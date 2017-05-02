package tech.simter.demo.service;

import tech.simter.demo.dao.DemoDao;
import tech.simter.demo.po.Demo;
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
    return demoDao.save(demo);
  }

  @Override
  public void delete(Integer id) {
    demoDao.delete(id);
  }
}
