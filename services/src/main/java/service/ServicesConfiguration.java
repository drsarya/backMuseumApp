package service;

import museum.DatabaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import(DatabaseConfig.class)
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = ServicesConfiguration.class)
public class ServicesConfiguration {
  @Autowired
  Environment mEnv;

  @Bean(name = "com.cloudinary.cloud_name")
  public String getCloudinaryCloudName() {
    return mEnv.getProperty("com.cloudinary.cloud_name");
  }

  @Bean(name = "com.cloudinary.api_key")
  public String getCloudinaryApiKey() {
    return mEnv.getProperty("com.cloudinary.api_key");
  }

  @Bean(name = "com.cloudinary.api_secret")
  public String getCloudinaryApiSecret() {
    return mEnv.getProperty("com.cloudinary.api_secret");
  }
}
