package test;

import base.BaseApi;
import base.BaseMethods;
import base.ConfigReader;
import io.restassured.path.json.JsonPath;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import util.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.fail;


public class Tests extends BaseApi {
    private Logger log = Logger.getLogger(Tests.class);
    private String logMessage = "";


    @Test
    public void Tests() {
        String createdBoardId = Boards.createBoard();
        String createdListId =  Lists.createList(createdBoardId);
        String createdFirstCardId = Cards.createCardFirst(createdListId);
        String createdSecondCardId = Cards.createCardSecond(createdListId);
        String randomCardId = Cards.getCardIdRandomFromList(createdListId);
        Cards.updateCard(randomCardId);
        Cards.deleteCard(createdFirstCardId);
        Cards.deleteCard(createdSecondCardId);
        Lists.archiveList(createdListId);
        Boards.deleteBoard(createdBoardId);
    }



}
