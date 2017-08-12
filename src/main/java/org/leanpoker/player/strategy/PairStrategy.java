package org.leanpoker.player.strategy;

import org.leanpoker.player.Card;
import org.leanpoker.player.Session;
import org.leanpoker.player.analyzer.CardAnalyzeResult;
import org.leanpoker.player.analyzer.CardAnalyzer;
import org.leanpoker.player.analyzer.DefaultCardAnalyzer;
import org.leanpoker.player.constants.Combination;

import java.util.ArrayList;
import java.util.List;

public class PairStrategy implements Strategy {
    private CardAnalyzer analyzer = new DefaultCardAnalyzer();
    private RaiseSelector raiseSelector;

    public int process(Session session) {
        raiseSelector = new RaiseSelector(session);

        int analyzes = analyzeCards(session.getAllCards());
        if (analyzes < 200) { // Not a pair
            return 0;
        } else if (analyzes < 300 &&
            (session.getCommunity_cards() != null && session.getCommunity_cards().size() > 3)) {
            return 0;
        }

        return raiseSelector.getMinimalRaise();
    }

    private int analyzeCards(List<Card> handCards) {
        CardAnalyzeResult cardAnalyzeResult = analyzer.analyzeCards(handCards);
        Combination combination = cardAnalyzeResult.getCombination();
        return combination.getValue() * 100 + cardAnalyzeResult.getBiggestCardInCombination();
    }

    @Override
    public int process(List<Card> handCards, List<Card> tableCards) {
        List cards = new ArrayList(handCards);
        cards.add(tableCards);
        CardAnalyzeResult cardAnalyzeResult = analyzer.analyzeCards(cards);
        Combination combination = cardAnalyzeResult.getCombination();
        return combination == Combination.PAIR ? 80 : 0;
    }
}
