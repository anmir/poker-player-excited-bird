package org.leanpoker.player.strategy;

import lombok.Data;
import org.leanpoker.player.Session;
import org.leanpoker.player.analyzer.CardAnalyzeResult;
import org.leanpoker.player.analyzer.CardAnalyzer;
import org.leanpoker.player.analyzer.DefaultCardAnalyzer;
import org.leanpoker.player.constants.Combination;

/**
 * Created by andrey on 12.08.17.
 */
@Data
public class SimpleCombinationOrientedStrategy implements Strategy {
    private BetSelector betSelector;
    private CardAnalyzer analyzer = new DefaultCardAnalyzer();

    @Override
    public int process(Session session) {
        betSelector = new BetSelector(session);
        CardAnalyzeResult cardAnalyzeResult = analyzer.analyzeCards(session.getAllCards());
        System.out.println("cardAnalyzeResult: " + cardAnalyzeResult);
        Combination combination = cardAnalyzeResult.getCombination();

        if (combination.getValue().equals(Combination.BIGGEST_CARD)) {
            return betSelector.fold();
        }
        if (combination.getValue() <= Combination.PAIR.getValue()) {
            return betSelector.check();
        }
        if (combination.getValue() <= Combination.FOUR_KIND.getValue()) {
            return betSelector.getMinimalRaise();
        }

        return betSelector.getMinimalRaise();
    }

}
