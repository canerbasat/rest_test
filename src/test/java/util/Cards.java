package util;

import base.BaseMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;


import java.util.List;

import static base.BaseApi.API_KEY;
import static base.BaseApi.TOKEN;
import static io.restassured.RestAssured.given;

public class Cards {
    private static Logger log = Logger.getLogger(Cards.class);


    public static String createCardFirst(String listId) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "caner");
        Response board = RestAssured
                .given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")  // Con
                .queryParam("key", API_KEY)
                .queryParam("token", TOKEN)
                .when()
                .body(requestParams)
                .post("cards?idList="+listId)
                .then()
                .statusCode(200)
                .extract()
                .response();

        log.info("Card created with " + board.jsonPath().getString("id") + " listId at " + listId + " list.");
        return board.path("id");

    }

    public static String createCardSecond(String listId) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "basat");

        Response board = RestAssured
                .given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")  // Con
                .queryParam("key", API_KEY)
                .queryParam("token", TOKEN)
                .when()
                .body(requestParams)
                .post("cards?idList="+listId)
                .then()
                .statusCode(200)
                .extract()
                .response();
        log.info("Card created with " + board.jsonPath().getString("id") + " listId at " + listId + " list.");
        return board.path("id");
    }



    public static String getCardIdRandomFromList(String listId) {
        Response response = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .queryParam("key", API_KEY)
                .queryParam("token", TOKEN)
                .when()
                .get("/lists/"+listId+"/cards")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        List<String> cardIds = jsonPath.getList("id");

        String randomCardId = BaseMethods.getRandomElement(cardIds);
        log.info("Random Card Id : "+randomCardId);
        return randomCardId;
    }

    public static void updateCard(String cardId) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "TestCase");

        Response board = RestAssured
                .given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .queryParam("key", API_KEY)
                .queryParam("token", TOKEN)
                .when()
                .body(requestParams)
                .put("cards/"+cardId)
                .then()
                .statusCode(200)
                .extract()
                .response();
        log.info("Choosen random card is "+ cardId+" updated.");

    }


    public static void deleteCard(String cardId) {
        Response board = RestAssured
                .given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .queryParam("key", API_KEY)
                .queryParam("token", TOKEN)
                .when()
                .delete("cards/"+cardId)
                .then()
                .statusCode(200)
                .extract()
                .response();
        log.info(cardId+" card has been deleted.");

    }




}
