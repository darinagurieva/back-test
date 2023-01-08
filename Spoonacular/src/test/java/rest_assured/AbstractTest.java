package rest_assured;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

public class AbstractTest {
    public static Properties propGlobal = new Properties();
    public static Properties prop = new Properties();
    static Random random = new Random();
    private static InputStream configFileInGlobal;
    private static OutputStream configFileOut;
    private static HashMap<Object, Object> properties;
    private static String apiKey;
    private static String baseUrl;

    @BeforeAll
    static void initTest() throws IOException {
        configFileInGlobal = new FileInputStream("src/main/resources/my.properties");
        InputStream configFileTestIn = new FileInputStream("src/main/resources/myTest.properties");
        propGlobal.load(configFileInGlobal);
        prop.load(configFileTestIn);
        apiKey =  propGlobal.getProperty("apiKey");
        baseUrl= propGlobal.getProperty("base_url");

    }

    @AfterAll
    static void afterTest() throws IOException {
        configFileOut = new FileOutputStream("src/main/resources/myTest.properties");
        prop.save(configFileOut, "save");
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }


}