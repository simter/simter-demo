package tech.simter.demo.service;

import tech.simter.data.Page;
import tech.simter.demo.po.Demo;
import tech.simter.persistence.CommonState;

import java.util.List;

/**
 * @author RJ
 */
public interface DemoService {
  Demo get(Integer id);

  List<Demo> find(CommonState status, String search);

  Page<Demo> find(int pageNo, int pageSize, CommonState status);

  Demo save(Demo demo);

  void delete(Integer... ids);
}
