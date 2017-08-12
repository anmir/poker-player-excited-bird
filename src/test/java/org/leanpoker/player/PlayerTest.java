package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

//import static org.junit.Assert.*;

public class PlayerTest {

        public static final String JSON1 = "{\n" +
                "  \"tournament_id\": \"598e25d6b0cdd800040018da\",\n" +
                "  \"game_id\": \"598eb6f78314440004000015\",\n" +
                "  \"round\": 0,\n" +
                "  \"players\": [\n" +
                "    {\n" +
                "      \"name\": \"Maverik\",\n" +
                "      \"stack\": 996,\n" +
                "      \"status\": \"active\",\n" +
                "      \"bet\": 4,\n" +
                "      \"time_used\": 0,\n" +
                "      \"version\": \"Maverik\",\n" +
                "      \"id\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Z team\",\n" +
                "      \"stack\": 0,\n" +
                "      \"status\": \"out\",\n" +
                "      \"bet\": 0,\n" +
                "      \"time_used\": 8154,\n" +
                "      \"version\": \"0.1\",\n" +
                "      \"id\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Excited Bird\",\n" +
                "      \"stack\": 1000,\n" +
                "      \"status\": \"active\",\n" +
                "      \"bet\": 0,\n" +
                "      \"hole_cards\": [\n" +
                "        {\n" +
                "          \"rank\": \"10\",\n" +
                "          \"suit\": \"spades\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"rank\": \"Q\",\n" +
                "          \"suit\": \"diamonds\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"time_used\": 0,\n" +
                "      \"version\": \"Default Java folding player\",\n" +
                "      \"id\": 2\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Wise Kaa 2\",\n" +
                "      \"stack\": 998,\n" +
                "      \"status\": \"active\",\n" +
                "      \"bet\": 2,\n" +
                "      \"time_used\": 0,\n" +
                "      \"version\": \"Default Python folding player\",\n" +
                "      \"id\": 3\n" +
                "    }\n" +
                "  ],\n" +
                "  \"small_blind\": 2,\n" +
                "  \"big_blind\": 4,\n" +
                "  \"orbits\": 0,\n" +
                "  \"dealer\": 2,\n" +
                "  \"community_cards\": [],\n" +
                "  \"current_buy_in\": 4,\n" +
                "  \"pot\": 6,\n" +
                "  \"in_action\": 2,\n" +
                "  \"minimum_raise\": 2,\n" +
                "  \"bet_index\": 3\n" +
                "}";
        public static final String JSON2 = "{\n" +
                "  \"tournament_id\": \"598e25d6b0cdd800040018da\",\n" +
                "  \"game_id\": \"598eb6f78314440004000015\",\n" +
                "  \"round\": 0,\n" +
                "  \"players\": [\n" +
                "    {\n" +
                "      \"name\": \"Maverik\",\n" +
                "      \"stack\": 996,\n" +
                "      \"status\": \"active\",\n" +
                "      \"bet\": 4,\n" +
                "      \"time_used\": 0,\n" +
                "      \"version\": \"Maverik\",\n" +
                "      \"id\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Z team\",\n" +
                "      \"stack\": 0,\n" +
                "      \"status\": \"out\",\n" +
                "      \"bet\": 0,\n" +
                "      \"time_used\": 8154,\n" +
                "      \"version\": \"0.1\",\n" +
                "      \"id\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Excited Bird\",\n" +
                "      \"stack\": 1000,\n" +
                "      \"status\": \"active\",\n" +
                "      \"bet\": 0,\n" +
                "      \"hole_cards\": [\n" +
                "        {\n" +
                "          \"rank\": \"10\",\n" +
                "          \"suit\": \"spades\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"rank\": \"Q\",\n" +
                "          \"suit\": \"diamonds\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"time_used\": 0,\n" +
                "      \"version\": \"Default Java folding player\",\n" +
                "      \"id\": 2\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Wise Kaa 2\",\n" +
                "      \"stack\": 998,\n" +
                "      \"status\": \"active\",\n" +
                "      \"bet\": 2,\n" +
                "      \"time_used\": 0,\n" +
                "      \"version\": \"Default Python folding player\",\n" +
                "      \"id\": 3\n" +
                "    }\n" +
                "  ],\n" +
                "  \"small_blind\": 2,\n" +
                "  \"big_blind\": 4,\n" +
                "  \"orbits\": 0,\n" +
                "  \"dealer\": 2,\n" +
                "  \"community_cards\": [\n" +
                "    {\n" +
                "      \"rank\": \"10\",\n" +
                "      \"suit\": \"diamonds\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"rank\": \"K\",\n" +
                "      \"suit\": \"diamonds\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"current_buy_in\": 4,\n" +
                "  \"pot\": 6,\n" +
                "  \"in_action\": 2,\n" +
                "  \"minimum_raise\": 2,\n" +
                "  \"bet_index\": 3\n" +
                "}";

        @org.junit.Test
    public void testGetSession() throws Exception {

//        Gson gson = new Gson();
            Session session = getSession("{\n" +
                    "  \"tournament_id\": \"598e25d6b0cdd800040018da\",\n" +
                    "  \"game_id\": \"598eb6f78314440004000015\",\n" +
                    "  \"round\": 0,\n" +
                    "  \"players\": [\n" +
                    "    {\n" +
                    "      \"name\": \"Maverik\",\n" +
                    "      \"stack\": 996,\n" +
                    "      \"status\": \"active\",\n" +
                    "      \"bet\": 4,\n" +
                    "      \"time_used\": 0,\n" +
                    "      \"version\": \"Maverik\",\n" +
                    "      \"id\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"name\": \"Z team\",\n" +
                    "      \"stack\": 0,\n" +
                    "      \"status\": \"out\",\n" +
                    "      \"bet\": 0,\n" +
                    "      \"time_used\": 8154,\n" +
                    "      \"version\": \"0.1\",\n" +
                    "      \"id\": 1\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"name\": \"Excited Bird\",\n" +
                    "      \"stack\": 1000,\n" +
                    "      \"status\": \"active\",\n" +
                    "      \"bet\": 0,\n" +
                    "      \"hole_cards\": [\n" +
                    "        {\n" +
                    "          \"rank\": \"10\",\n" +
                    "          \"suit\": \"spades\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"rank\": \"Q\",\n" +
                    "          \"suit\": \"diamonds\"\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"time_used\": 0,\n" +
                    "      \"version\": \"Default Java folding player\",\n" +
                    "      \"id\": 2\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"name\": \"Wise Kaa 2\",\n" +
                    "      \"stack\": 998,\n" +
                    "      \"status\": \"active\",\n" +
                    "      \"bet\": 2,\n" +
                    "      \"time_used\": 0,\n" +
                    "      \"version\": \"Default Python folding player\",\n" +
                    "      \"id\": 3\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"small_blind\": 2,\n" +
                    "  \"big_blind\": 4,\n" +
                    "  \"orbits\": 0,\n" +
                    "  \"dealer\": 2,\n" +
                    "  \"community_cards\": [],\n" +
                    "  \"current_buy_in\": 4,\n" +
                    "  \"pot\": 6,\n" +
                    "  \"in_action\": 2,\n" +
                    "  \"minimum_raise\": 2,\n" +
                    "  \"bet_index\": 3\n" +
                    "}");
//        assertNull(session);
        assertNotNull(session);
    }

        public static void main(String[] args) {
                Session session = getSession(JSON2);
//                session
                session.getAllCards();

                System.out.println("session = " + session);
        }


        private static Session getSession(String json) {
                String testState = json;
                JsonElement jsonElement = new JsonParser().parse(testState);
                Session session = Player.getSession(jsonElement);
                return session;
        }
}