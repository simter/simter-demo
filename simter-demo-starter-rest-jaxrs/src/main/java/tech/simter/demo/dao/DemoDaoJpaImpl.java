package tech.simter.demo.dao;

import tech.simter.demo.po.Demo;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

/**
 * @author RJ 2017-04-29
 */
@Component
public class DemoDaoJpaImpl implements DemoDao {
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Demo get(Integer id) {
    return entityManager.find(Demo.class, id);
  }

  @Override
  public List<Demo> find() {
    return entityManager.createQuery("select * from Demo order by name", Demo.class).getResultList();
  }

  @Override
  public Demo save(Demo demo) {
    Objects.requireNonNull(demo);
    if (demo.id != null) entityManager.merge(demo);
    else entityManager.persist(demo);
    return null;
  }

  @Override
  public void delete(Integer... ids) {
    entityManager.createQuery("delete from Demo where id in (:ids)").executeUpdate();
  }
}
