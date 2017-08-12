package org.leanpoker.player.strategy;

import org.leanpoker.player.Card;

import java.util.List;

public class PairStrategy implements Strategy {
    @Override
    public int process(List<Card> cards) {
        Card firstCard = cards.get(0);
        Card secondCard = cards.get(1);

        return firstCard.getRank().equals(secondCard.getRank()) ? 100 : 0;
    }

    @Override
    public int process(List<Card> handCards, List<Card> tableCards) {
        return process(handCards);
    }
}
