package tech.simter.demo.service;

import tech.simter.data.Page;
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
  public List<Demo> find(CommonState status, String search) {
    return demoDao.find(status, search);
  }

  @Override
  public Page<Demo> find(int pageNo, int pageSize, CommonState status) {
    return demoDao.find(pageNo, pageSize, status);
  }

  @Override
  @Transactional
  public Demo save(Demo demo) {
    boolean isNew = demo.id == null;
    Demo saved = demoDao.save(demo);
    if (isNew) metaService.addCreation(Demo.class, saved.id);
    else metaService.addModification(Demo.class, saved.id);
    return saved;
  }

  @Override
  @Transactional
  public void delete(Integer... ids) {
    demoDao.delete(ids);
  }
}
