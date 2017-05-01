package tech.simter.demo.service;

import tech.simter.demo.dao.DemoDao;
import tech.simter.demo.po.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RJ
 */
@Service
public class DemoServiceImpl implements DemoService {
  @Autowired
  private DemoDao demoDao;

  @Override
  public Demo get(Integer id) {
    return demoDao.get(id);
  }

  @Override
  public List<Demo> find() {
    return demoDao.find();
  }

  @Override
  public Demo save(Demo demo) {
    return demoDao.save(demo);
  }

  @Override
  public void delete(Integer id) {
    demoDao.delete(id);
  }
}
