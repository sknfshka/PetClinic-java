package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by User on 27.08.2017.
 */
public class Settings {
    private static final Settings instance = new Settings();

    private final Properties properties = new Properties();

    private Settings() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(new FileInputStream(this.getClass().getClassLoader().getResource("clinicweb.properties").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Settings getInstance() {
        return instance;
    }

    public String value(String key) {
        return this.properties.getProperty(key);
    }
}
