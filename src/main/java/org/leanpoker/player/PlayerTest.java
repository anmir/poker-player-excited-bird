package org.leanpoker.player;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

//import static org.junit.Assert.*;

public class PlayerTest {

//    @org.junit.Test
    public void testGetSession() throws Exception {

//        Gson gson = new Gson();
        String testState = "";
        JsonElement jsonElement = new JsonParser().parse(testState);
        Session session = Player.getSession(jsonElement);
//        asNN
    }
}