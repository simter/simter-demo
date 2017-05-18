package tech.simter.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author RJ
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "tech.simter")
@EntityScan("tech.simter")
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}