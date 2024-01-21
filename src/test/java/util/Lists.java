package util;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import static base.BaseApi.API_KEY;
import static base.BaseApi.TOKEN;

public class Lists {
    private static Logger log = Logger.getLogger(Lists.class);

    public static String createList(String boardId) {
        Response board = RestAssured
                .given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")  // Con
                .queryParam("key", API_KEY)
                .queryParam("token", TOKEN)
                .when()
                .post("boards/"+boardId+"/lists?name=sena")
                .then()
                .statusCode(200)
                .extract()
                .response();

        log.info("List created with "+ board.path("id")+" listId at "+boardId+" board.");
        return board.path("id");
    }

    public static void archiveList(String listId) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("closed", "true");
        Response board = RestAssured
                .given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .queryParam("key", API_KEY)
                .queryParam("token", TOKEN)
                .when()
                .body(requestParams)
                .put("lists/"+listId)
                .then()
                .statusCode(200)
                .extract()
                .response();
        log.info("List id "+listId+" has been archieved.");
    }

}
