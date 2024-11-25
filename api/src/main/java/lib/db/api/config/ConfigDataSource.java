package lib.db.api.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigDataSource {
    static Variables v = new Variables();

    // @Value("${spring.datasource.url}")
    // private String url;

    // @Value("${spring.datasource.username}")
    // private String username;

    // @Value("${spring.datasource.password}")
    // private String password;

    // @Value("${spring.datasource.driver-class-name}")
    // private String driverClassName;

    @Bean
    public static DataSource source() throws PropertyVetoException{

        DataSourceBuilder<?> dsb = DataSourceBuilder.create();

        dsb.driverClassName(v.getDriver());
        dsb.url(v.getUrl());
        dsb.username(v.getUsername());
        dsb.password(v.getPassword());

        return dsb.build();
    }

}
