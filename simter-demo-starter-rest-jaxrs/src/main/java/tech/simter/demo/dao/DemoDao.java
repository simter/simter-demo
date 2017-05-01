package tech.simter.demo.dao;

import tech.simter.demo.po.Demo;

import java.util.List;

/**
 * @author RJ 2017-04-29
 */
public interface DemoDao {
  Demo get(Integer id);

  List<Demo> find();

  Demo save(Demo demo);

  void delete(Integer... ids);
}
