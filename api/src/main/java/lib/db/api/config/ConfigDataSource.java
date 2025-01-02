package lib.db.api.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigDataSource {

    

    // @Value("${spring.datasource.url}")
    // private static String url;

    // @Value("${spring.datasource.username}")
    // private static String username;

    // @Value("${spring.datasource.password}")
    // private static String password;

    // @Value("${spring.datasource.driver-class-name}")
    // private static String driverClassName;
    
    @Bean
    public static DataSource dataSource() throws PropertyVetoException{

        DataSourceBuilder<?> dsb = DataSourceBuilder.create();
        Variables v = new Variables();
        dsb.driverClassName(v.getDriver());
        dsb.url(v.getUrl());
        dsb.username(v.getUsername());
        dsb.password(v.getPassword());

        return dsb.build();
    }
}
