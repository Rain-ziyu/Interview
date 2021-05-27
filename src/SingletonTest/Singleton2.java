package SingletonTest;

import java.io.IOException;
import java.util.Properties;
/*
 *饿汉式
 */
public class Singleton2 {
    public static final Singleton2 singleton;
    private String name;

    public static Singleton2 getSingleton() {
        return singleton;
    }

    @Override
    public String toString() {
        return "Singleton2{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    static {
        Properties properties = new Properties();
        try {
            properties.load(Singleton2.class.getResourceAsStream("Singleton.properties"));
            singleton = new Singleton2(properties.getProperty("name"));
        } catch (IOException e) {
           throw new RuntimeException();
        }

    }
    private Singleton2 (String name){
        this.name = name;
    }
}
