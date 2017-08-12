package org.leanpoker.player.strategy;

import org.leanpoker.player.Card;
import org.leanpoker.player.analyzer.CardAnalyzer;
import org.leanpoker.player.analyzer.DefaultCardAnalyzer;
import org.leanpoker.player.constants.Combination;

import java.util.List;

public class PairStrategy implements Strategy {
    private CardAnalyzer analyzer = new DefaultCardAnalyzer();

    @Override
    public int process(List<Card> handCards) {
        Combination combination = analyzer.analyzeCards(handCards, null);
        return combination == Combination.PAIR ? 100 : 0;
    }

    @Override
    public int process(List<Card> handCards, List<Card> tableCards) {
        Combination combination = analyzer.analyzeCards(handCards, tableCards);
        return combination == Combination.PAIR ? 100 : 0;
    }
}
