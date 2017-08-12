package org.leanpoker.player.strategy.particular;

import org.leanpoker.player.Card;
import org.leanpoker.player.Session;
import org.leanpoker.player.analyzer.CardAnalyzeResult;
import org.leanpoker.player.analyzer.CardAnalyzer;
import org.leanpoker.player.analyzer.DefaultCardAnalyzer;
import org.leanpoker.player.constants.Combination;
import org.leanpoker.player.strategy.BetSelector;
import org.leanpoker.player.strategy.Strategy;

import java.util.List;

public class TurnStrategy implements Strategy {
    @Override
    public int process(Session session) {
        CardAnalyzer analyzer = new DefaultCardAnalyzer();
        BetSelector betSelector = new BetSelector(session);

        List<Card> allCards = session.getAllCards();
        CardAnalyzeResult cardAnalyzeResult = analyzer.analyzeCards(allCards);
        Combination combination = cardAnalyzeResult.getCombination();

        // 5 card combo or more powerful
        if (combination.getValue() >= Combination.STRAIGHT.getValue()) {
            return betSelector.getMinimalRaise() * 2;
        }

        // 3 card combo
        if (combination == Combination.TRIPLE) {
            return betSelector.getMinimalRaise();
        }

        if (combination != Combination.BIGGEST_CARD) {
            return betSelector.safeRaise(session);
        }

        return betSelector.fold();
    }
}
