package util;

import base.BaseApi;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;


public class Boards extends BaseApi {
    private static String created_board_id;
    private static Logger log = Logger.getLogger(Boards.class);


    public static void getBoardDetail() {
        Response board = RestAssured
                .given()
                .header("Accept", "application/json")
                .queryParam("key", API_KEY)
                .queryParam("token", TOKEN)
                .when()
                .get("/boards/65ac52514db7ec7bd6b53a11")
                .then()
                .statusCode(200)
                .extract()
                .response();
        board.then().log().all();
    }

    public static String createBoard() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Created Board");
        requestParams.put("desc", "Test case");

        Response board = RestAssured
                .given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")  // Con
                .queryParam("key", API_KEY)
                .queryParam("token", TOKEN)
                .when()
                .body(requestParams)
                .post("boards")
                .then()
                .statusCode(200)
                .extract()
                .response();
        created_board_id = board.path("id");
        log.info("Board created with = "+created_board_id+" board id.");
        return created_board_id;
    }


    public static void deleteBoard(String boardId) {
        Response board = RestAssured
                .given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .queryParam("key", API_KEY)
                .queryParam("token", TOKEN)
                .when()
                .delete("/boards/"+boardId)
                .then()
                .statusCode(200)
                .extract()
                .response();
        log.info("Board id "+boardId+"has been deleted.");
    }


}
