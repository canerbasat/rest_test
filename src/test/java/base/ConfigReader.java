package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("BASE_URI");
    }
    public static String getBoardName() {
        return properties.getProperty("BOARD_NAME");
    }

    public static String getToken() {
        return properties.getProperty("TOKEN");
    }

    public static String getApiKey() {
        return properties.getProperty("API_KEY");
    }
}
