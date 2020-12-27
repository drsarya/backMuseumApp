package dataBase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@MapperScan(basePackages = "dataBase.mapper")
@EnableTransactionManagement
public class DatabaseConfig {

}
