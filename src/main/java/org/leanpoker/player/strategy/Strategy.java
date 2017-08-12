package org.leanpoker.player.strategy;

import org.leanpoker.player.Card;

import java.util.List;

public interface Strategy {
    int process(List<Card> handCards);
    int process(List<Card> handCards, List<Card> tableCards);
}
