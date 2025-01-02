package lib.db.api.config;

import java.io.FileInputStream;
import java.util.Properties;

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
            prop.load(fis);

            url = prop.getProperty("db.url");
            username = prop.getProperty("db.username");
            password = prop.getProperty("db.password");
            driver = prop.getProperty("db.driver");
            KEY = prop.getProperty("db.key");
            VITE_KEY = prop.getProperty("db.vite_key");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
        

    // public Variables() {
    //     Properties prop = new Properties();
    //     try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
    //         if (input == null) {
    //             throw new IOException("application.properties not found in classpath.");
    //         }
    //         prop.load(input);
    //         url = prop.getProperty("spring.datasource.url");
    //         username = prop.getProperty("spring.datasource.username");
    //         password = prop.getProperty("spring.datasource.password");
    //         driver = prop.getProperty("spring.datasource.driver-class-name");
    //     } catch (IOException ioe) {
    //         ioe.printStackTrace();
    //     }
    // }

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
