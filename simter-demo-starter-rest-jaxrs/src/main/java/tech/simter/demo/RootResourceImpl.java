package tech.simter.demo;

import tech.simter.vo.User;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author RJ
 */
@Named
@Singleton
public class RootResourceImpl implements RootResource {
  @PersistenceContext
  private EntityManager entityManager;
  private String ts = OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

  @Override
  public String root() {
    return "Simter Demo.<br><br>Start at " + ts;
  }

  @Override
  public String vueComponentsTestData4Grid(int pageNo, int pageSize, String query) {
    return "{" +
      "\"count\":333," +

      "\"columns\":[" +
      "{\"id\":\"date\",\"label\":\"日期\",\"width\":\"7em\"},{\"id\":\"str\",\"label\":\"文本\",\"width\":\"15em\",\"title\":true}," +
      "{\"id\":\"money\",\"label\":\"金额（元）\",\"children\":[" +
      "{\"id\":\"moneyIn\",\"label\":\"支出\",\"width\":\"7em\"}," +
      "{\"id\":\"moneyOut\",\"label\":\"收入\",\"width\":\"7em\",\"title\":true}]}," +
      "{\"id\":\"num\",\"label\":\"数字\",\"width\":\"7em\"}" +
      "]," +

      "\"rows\":[" +
      "{\"date\":\"2017-5-11\",\"str\":\"文本1\",\"num\":1,\"moneyIn\":100,\"moneyOut\":101.05}," +
      "{\"date\":\"2017-5-10\",\"str\":\"文本2\",\"num\":2,\"moneyIn\":200,\"moneyOut\":102.05}," +
      "{\"date\":\"2017-5-9\",\"str\":\"文本3\",\"num\":3,\"moneyIn\":300,\"moneyOut\":103.05}" +
      "]}";
  }

  @Override
  public List<User> demoUser() {
    List<User> users = entityManager.createQuery("select u from User u", User.class)
      .getResultList();
    return users;
  }
}