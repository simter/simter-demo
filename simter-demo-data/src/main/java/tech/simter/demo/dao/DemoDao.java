package tech.simter.demo.dao;

import tech.simter.data.Page;
import tech.simter.demo.po.Demo;
import tech.simter.persistence.CommonState;

import java.util.List;

/**
 * @author RJ
 */
public interface DemoDao {
  Demo get(Integer id);

  List<Demo> find(CommonState status);

  Page<Demo> find(int pageNo, int pageSize, CommonState status);

  Demo save(Demo demo);

  int delete(Integer... ids);
}
