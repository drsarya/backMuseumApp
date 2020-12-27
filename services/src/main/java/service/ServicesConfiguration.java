package service;

import dataBase.DatabaseConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DatabaseConfig.class)
@ComponentScan(basePackageClasses = ServicesConfiguration.class)
public class ServicesConfiguration {
}
