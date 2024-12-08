package lib.db.api.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Variables {
    private static String url = "";
    private static String username = "";
    private static String password = "";
    private static String driver = "";
    private static String KEY = "";
    private static String VITE_KEY = "";

    public Variables(){
        try(FileInputStream fis = new FileInputStream("api\\src\\main\\resources\\application.properties")){
        Properties prop = new Properties();
        
        url = prop.getProperty("db.url");
        username = prop.getProperty( "db.url");
        password = prop.getProperty("db.password");
        driver = prop.getProperty("db.driver");
        KEY = prop.getProperty("db.KEY");
        VITE_KEY = prop.getProperty("db.VITE_KEY");

        }
        catch(FileNotFoundException fne){
            fne.printStackTrace();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }

    }

    // private String url = env.getProperty("spring.datasource.url");
    // private String username = env.getProperty("spring.datasource.username");
    // private String password = env.getProperty("spring.datasource.password");
    // private String driver = env.getProperty("spring.datasource.driver-class-name");
    // private final static String url = "jdbc:postgresql://aws-0-us-west-1.pooler.supabase.com:6543/postgres";
    // private final static String username = "postgres.nmkipvazyvwaegxfqotg";
    // private final static String password = "LjWIbmC62AG3Nfdz";
    // private final static String driver = "org.postgresql.Driver";
    // private final static String KEY = "Ir3allyWantthisPr0ject2BDoneS00n";
    // private final static String VITE_KEY = "WhyAm1CreatingIt";

    public static String getViteKey() {
        return VITE_KEY;
    }
    public static String getKey() {
        return KEY;
    }
    public static String getUrl() {
        return url;
    }
    public static String getUsername() {
        return username;
    }
    public static String getPassword() {
        return password;
    }
    public static String getDriver() {
        return driver;
    }

}
