package org.leanpoker.player.strategy;

import org.leanpoker.player.Card;

import java.util.List;

public class PairStrategy implements Strategy {
    @Override
    public int process(List<Card> cards) {
        return 1;
    }
}
