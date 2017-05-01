package tech.simter.demo.service;

import tech.simter.demo.po.Demo;

import java.util.List;

/**
 * @author RJ
 */
public interface DemoService {
  Demo get(Integer id);

  List<Demo> find();

  Demo save(Demo demo);

  void delete(Integer id);
}
