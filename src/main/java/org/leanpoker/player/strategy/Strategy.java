package org.leanpoker.player.strategy;

import org.leanpoker.player.Card;

import java.util.List;

/**
 * Created by Nadezhda on 12.08.2017.
 */
public interface Strategy {
    int process(List<Card> cards);
}
