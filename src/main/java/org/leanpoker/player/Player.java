package org.leanpoker.player;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.leanpoker.player.strategy.*;

import java.util.List;

public class Player {
    static final String VERSION = "Excited Fish";

    static private Strategy strategy = new PairStrategy();
    static private Strategy combinationOrientedStrategy = new SimpleCombinationOrientedStrategy();
    static private Strategy generationStrategy = new NewGenerationStrategy();

    public static int betRequest(JsonElement request) {
        int bet = 10;

        try {
            System.out.println("betRequest: " + request);
            Session session = getSession(request);
            System.out.println("session = " + session);

            List<Card> handCards = session.getAllCards();

            // Should be good realisation
//            bet = combinationOrientedStrategy.process(session);
            bet = strategy.process(session);
        } catch (Exception e) {
            System.err.println("main err= " + e);
            bet = new RandomStrategy().process((Session) null);
        }

        System.out.println("Placing bet = " + bet);
        return bet;
    }

    protected static Session getSession(JsonElement request) {
        Gson gson = new Gson();
        return gson.fromJson(request, Session.class);
    }

    public static void showdown(JsonElement game) {
    }
}
