package lib.db.api.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigDataSource {
    
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
