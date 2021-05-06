package museum.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import service.ServicesConfiguration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Import(ServicesConfiguration.class)
@ComponentScan(basePackages = {"museum"})
@SpringBootApplication
@EnableSwagger2
public class SampleApp {
  public static void main(String[] args) {
    SpringApplication.run(SampleApp.class, args);
  }
  @Bean
  public Docket productApi() {
    return new Docket(DocumentationType.SWAGGER_2).select()
      .apis(RequestHandlerSelectors.basePackage("museum.api")).build();
  }
}
