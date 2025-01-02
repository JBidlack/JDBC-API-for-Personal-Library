package lib.db.api.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigDataSource {

    private final Variables v;

    @Autowired
    public ConfigDataSource(Variables v) {
        this.v = v;
    }

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder<?> dsb = DataSourceBuilder.create();

        dsb.driverClassName(v.getDriver());
        dsb.url(v.getUrl());
        dsb.username(v.getUsername());
        dsb.password(v.getPassword());

        return dsb.build();
    }
}
