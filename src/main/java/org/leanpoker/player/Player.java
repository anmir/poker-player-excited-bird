package org.leanpoker.player;

import com.google.gson.JsonElement;
import org.leanpoker.player.strategy.PairStrategy;
import org.leanpoker.player.strategy.Strategy;

import java.util.Random;

public class Player {
    static final String VERSION = "Excited Fish";
    static private Random random = new Random();

    static Strategy strategy = new PairStrategy();

    public static int betRequest(JsonElement request) {
        System.out.println("betRequest: " + request);
        return random.nextInt(500);
    }

    public static void showdown(JsonElement game) {
    }
}
