package tech.simter.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author RJ 2017-04-29
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "tech.simter")
//@SpringBootConfiguration
@EntityScan("tech.simter")
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}