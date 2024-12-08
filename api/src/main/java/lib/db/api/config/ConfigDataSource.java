package lib.db.api.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigDataSource {

    private String url = Variables.getUrl();
    private String username = Variables.getUsername();
    private String password = Variables.getPassword();
    private String driverClassName ;

    @Bean
    public DataSource dataSource() throws PropertyVetoException{

        DataSourceBuilder<?> dsb = DataSourceBuilder.create();

        dsb.driverClassName(driverClassName);
        dsb.url(url);
        dsb.username(username);
        dsb.password(password);

        return dsb.build();
    }

}
