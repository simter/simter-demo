package tech.simter.demo.rest.jaxrs.javatime;

import tech.simter.annotation.Format;

import java.time.YearMonth;

/**
 * @author RJ
 */
public class TestDto {
  // default formatter ("yyyy-MM")
  public YearMonth ym1;

  @Format("yyyy/MM")
  public YearMonth ym2;

  @Format("yyyyMM")
  public YearMonth ym3;
}
