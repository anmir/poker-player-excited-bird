package org.leanpoker.player.strategy;

import org.leanpoker.player.Card;
import org.leanpoker.player.Session;

import java.util.List;
import java.util.Random;

/**
 * Created by Nadezhda on 12.08.2017.
 */
public class RandomStrategy implements Strategy {
    static private Random random = new Random();

    @Override
    public int process(Session session) {
        return random.nextInt(80);
    }

    @Override
    public int process(List<Card> handCards, List<Card> tableCards) {
        return random.nextInt(80);
    }
}
