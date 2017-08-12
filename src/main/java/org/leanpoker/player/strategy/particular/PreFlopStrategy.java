package org.leanpoker.player.strategy.particular;

import org.leanpoker.player.Session;
import org.leanpoker.player.analyzer.CardAnalyzeResult;
import org.leanpoker.player.analyzer.CardAnalyzer;
import org.leanpoker.player.analyzer.DefaultCardAnalyzer;
import org.leanpoker.player.constants.Combination;
import org.leanpoker.player.strategy.BetSelector;
import org.leanpoker.player.strategy.Strategy;

/**
 * Created by andrey on 12.08.17.
 */
public class PreFlopStrategy implements Strategy {

    /**
     * если пара
     * или одинковая масть,
     * или большие карты
     * Райзим
     * <p>
     * если один король или туз - чекаем
     */
    @Override
    public int process(Session session) {
        CardAnalyzer analyzer = new DefaultCardAnalyzer();
        BetSelector betSelector = new BetSelector(session);
        CardAnalyzeResult cardAnalyzeResult = analyzer.analyzeCards(session.getAllCards());
        System.out.println("cardAnalyzeResult: " + cardAnalyzeResult);
        Combination combination = cardAnalyzeResult.getCombination();

        if (combination.getValue().equals(Combination.PAIR.getValue())) {
            return betSelector.getMinimalRaise();
        }

        Boolean ordered = analyzer.isOrdered(session.getAllCards());
        if (ordered) {
            return betSelector.getMinimalRaise();
        }

        Boolean allCardsAreHigh = analyzer.allCardsAreHigh(session.getAllCards());
        if (allCardsAreHigh) {
            return betSelector.getMaximumRaise();
        }

        Boolean containsHighCard = analyzer.containsHighCard(session.getAllCards());
        if (containsHighCard) {
            return betSelector.getMaximumRaise();
        }

        return betSelector.check();
    }
}
