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

public class RiverStrategy implements Strategy {
    @Override
    public int process(Session session) {
        CardAnalyzer analyzer = new DefaultCardAnalyzer();
        BetSelector betSelector = new BetSelector(session);

        List<Card> allCards = session.getAllCards();
        CardAnalyzeResult cardAnalyzeResult = analyzer.analyzeCards(allCards);
        Combination combination = cardAnalyzeResult.getCombination();

        // 5 card combo or more powerful
        if (combination.getValue() >= Combination.STRAIGHT.getValue()) {
            return betSelector.getMaximumRaise();
        }

        // 3 card combo
        if (combination == Combination.TRIPLE) {
            return betSelector.getMinimalRaise() * 4;
        }

        if (combination != Combination.BIGGEST_CARD) {
            if (session.getPlayer().getStack().intValue() < 300 || session.getCurrent_buy_in().intValue() > 300) {
                return betSelector.fold();
            }
            return betSelector.check();
        }

        return betSelector.fold();
    }
}
