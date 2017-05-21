package tech.simter.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author RJ
 */
@Entity
@Table(schema = "public")
public class User implements Serializable {
  @Id
  public Integer id;

  @Column(nullable = false)
  public String name;
}