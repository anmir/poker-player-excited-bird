package org.leanpoker.player;

import com.google.gson.JsonElement;

import java.util.Random;

public class Player {

    static final String VERSION = "Default Java folding player";
    static private Random random = new Random();

    public static int betRequest(JsonElement request) {
        return random.nextInt(500);
    }

    public static void showdown(JsonElement game) {
    }
}
