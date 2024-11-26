package lib.db.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Variables {

    
    @Autowired
    private Environment env;

    private String url = env.getProperty("spring.datasource.url");
    private String username = env.getProperty("spring.datasource.username");
    private String password = env.getProperty("spring.datasource.password");
    private String driver = env.getProperty("spring.datasource.driver-class-name");
    // private final static String url = "jdbc:postgresql://aws-0-us-west-1.pooler.supabase.com:6543/postgres";
    // private final static String username = "postgres.nmkipvazyvwaegxfqotg";
    // private final static String password = "LjWIbmC62AG3Nfdz";
    // private final static String driver = "org.postgresql.Driver";
    private final static String KEY = "Ir3allyWantthisPr0ject2BDoneS00n";
    private final static String VITE_KEY = "WhyAm1CreatingIt";

    public static String getViteKey() {
        return VITE_KEY;
    }
    public static String getKey() {
        return KEY;
    }
    public String getUrl() {
        return url;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getDriver() {
        return driver;
    }

}
