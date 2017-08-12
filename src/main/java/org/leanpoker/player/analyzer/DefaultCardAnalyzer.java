package org.leanpoker.player.analyzer;

import org.leanpoker.player.Card;
import org.leanpoker.player.constants.Combination;

import java.util.List;

import static org.leanpoker.player.constants.Combination.BIGGEST_CARD;

/**
 * Created by andrey on 12.08.17.
 */
public class DefaultCardAnalyzer implements CardAnalyzer {

    @Override
    public Combination analyzeCards(List<Card> holeCards, List<Card> tableCards) {
        return BIGGEST_CARD;
    }
}
