package tech.simter.demo.dao;

import tech.simter.data.Page;
import tech.simter.demo.po.Demo;
import tech.simter.persistence.CommonState;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author RJ 2017-04-29
 */
@Named
@Singleton
public class DemoDaoJpaImpl implements DemoDao {
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Demo get(Integer id) {
    return entityManager.find(Demo.class, id);
  }

  @Override
  public List<Demo> find(CommonState status, String search) {
    String jpql = "select d from Demo d where 0=0";
    if (status != null) jpql += " and status = :status";
    if (search != null) jpql += " and name like :search";
    jpql += " order by status, name";

    TypedQuery<Demo> query = entityManager.createQuery(jpql, Demo.class);
    if (status != null) query.setParameter("status", status);
    if (search != null) query.setParameter("search", "%" + search + "%");

    return query.getResultList();
  }

  @Override
  public Page<Demo> find(int pageNo, int pageCapacity, CommonState status) {
    String baseQl = " from Demo d";
    if (status != null) baseQl += " where status = :status";
    String rowsQl = "select d" + baseQl + " order by name";
    String countQl = "select count(*)" + baseQl;

    TypedQuery<Demo> rowsQuery = entityManager.createQuery(rowsQl, Demo.class);
    TypedQuery<Long> countQuery = entityManager.createQuery(countQl, Long.class);
    if (status != null) {
      rowsQuery.setParameter("status", status);
      countQuery.setParameter("status", status);
    }

    rowsQuery.setFirstResult(Page.calculateOffset(pageNo, pageCapacity));
    rowsQuery.setMaxResults(Page.toValidCapacity(pageCapacity));

    return Page.build(pageNo, pageCapacity, rowsQuery.getResultList(), countQuery.getSingleResult());
  }

  @Override
  public Demo save(Demo demo) {
    Objects.requireNonNull(demo);
    if (demo.id != null) demo = entityManager.merge(demo);
    else entityManager.persist(demo);
    return demo;
  }

  @Override
  public int delete(Integer... ids) {
    return entityManager.createQuery("delete from Demo where id in (:ids)")
      .setParameter("ids", Arrays.asList(ids))
      .executeUpdate();
  }
}
