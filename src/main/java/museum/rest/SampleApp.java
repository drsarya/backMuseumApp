package museum.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import service.ServicesConfiguration;


@Import(ServicesConfiguration.class)
@ComponentScan(basePackages = {"museum"})
@SpringBootApplication
public class SampleApp {
  public static void main(String[] args) {
    SpringApplication.run(SampleApp.class, args);
  }
}
