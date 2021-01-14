package museum.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@MapperScan(basePackages = {"museum.app/mapper"})
@EnableTransactionManagement
public class DatabaseConfig {
}
