package base;

import com.google.gson.*;
import io.restassured.path.json.JsonPath;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.lang.Math;
import java.util.List;
import java.util.Random;


public  class BaseMethods {
    public static String jsonBeauty(String uglyString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(uglyString);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }

    public static String getRandomElement(List<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

}
