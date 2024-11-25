package lib.db.api.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtFilterConfig {
    
    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter(){
        FilterRegistrationBean<JwtFilter> regBean = new FilterRegistrationBean<>();

        regBean.setFilter(new JwtFilter());

        regBean.setOrder(1);

        regBean.addUrlPatterns("/api/members/auth/*");

        return regBean;
    }
}
