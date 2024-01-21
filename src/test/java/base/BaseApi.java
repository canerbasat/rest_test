package base;

import io.restassured.RestAssured;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import util.Boards;

public class BaseApi {

    public static Logger log = Logger.getLogger(BaseApi.class);
    private String logMessage = "";

    public static String API_KEY = ConfigReader.getApiKey();
    public static String TOKEN = ConfigReader.getToken();


    @BeforeAll
    public static void beforeTest(){
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
        RestAssured.baseURI = "https://api.trello.com/1";
        log.info("Web Service test started.");
    }

    @After
    public void afterTest(){
        RestAssured.reset();
        logMessage = String.format("Web Service test finished.");
        log.info(logMessage);
    }

}
