package org.leanpoker.player;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import org.leanpoker.player.strategy.PairStrategy;
import org.leanpoker.player.strategy.RandomStrategy;
import org.leanpoker.player.strategy.Strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    static final String VERSION = "Excited Fish";

    static private Strategy strategy = new PairStrategy();

    public static int betRequest(JsonElement request) {
        int bet = 10;

        try {
            System.out.println("betRequest: " + request);
            Session session = getSession(request);
            System.out.println("session = " + session);

            List<Card> handCards = new ArrayList<>();

            // Should be good realisation
            bet = strategy.process(handCards);
        } catch (Exception e) {
            System.err.println("main err= " + e);
            bet = new RandomStrategy().process(null);;
        }

//        return bet;
        return 0;
    }

    protected static Session getSession(JsonElement request) {
        Gson gson = new Gson();
        return gson.fromJson(request, Session.class);
    }

    public static void showdown(JsonElement game) {
    }
}
