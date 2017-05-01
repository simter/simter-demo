package tech.simter.demo.po;

import javax.persistence.*;

/**
 * @author RJ
 */
@Entity
public class Demo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;
  @Column(nullable = false)
  public String name;
  public String remark;
}
