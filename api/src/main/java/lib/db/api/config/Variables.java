package lib.db.api.config;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class Variables {

    private String url;
    private String username;
    private String password;
    private String driver;

    private final static String KEY = "Ir3allyWantthisPr0ject2BDoneS00n";
    private final static String VITE_KEY = "WhyAm1CreatingIt";

    public Variables() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("application.properties not found in classpath.");
            }
            prop.load(input);
            url = prop.getProperty("spring.datasource.url");
            username = prop.getProperty("spring.datasource.username");
            password = prop.getProperty("spring.datasource.password");
            driver = prop.getProperty("spring.datasource.driver-class-name");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

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
