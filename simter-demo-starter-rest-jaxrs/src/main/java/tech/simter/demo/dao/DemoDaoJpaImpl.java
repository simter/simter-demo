package tech.simter.demo.dao;

import org.springframework.stereotype.Component;
import tech.simter.demo.po.Demo;
import tech.simter.persistence.CommonState;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
  public List<Demo> find(CommonState status) {
    String jpql = "select d from Demo d";
    if (status != null) jpql += " where status = :status";
    jpql += " order by name";

    TypedQuery<Demo> query = entityManager.createQuery(jpql, Demo.class);
    if (status != null) query.setParameter("status", status);

    return query.getResultList();
  }

  @Override
  public Demo save(Demo demo) {
    Objects.requireNonNull(demo);
    if (demo.id != null) demo = entityManager.merge(demo);
    else entityManager.persist(demo);
    return demo;
  }

  @Override
  public void delete(Integer... ids) {
    entityManager.createQuery("delete from Demo where id in (:ids)").executeUpdate();
  }
}
