package org.leanpoker.player.strategy.particular;

import org.leanpoker.player.Card;
import org.leanpoker.player.Session;
import org.leanpoker.player.analyzer.CardAnalyzeResult;
import org.leanpoker.player.analyzer.CardAnalyzer;
import org.leanpoker.player.analyzer.DefaultCardAnalyzer;
import org.leanpoker.player.constants.CardSuits;
import org.leanpoker.player.constants.Combination;
import org.leanpoker.player.strategy.BetSelector;
import org.leanpoker.player.strategy.Strategy;

import java.util.HashMap;
import java.util.List;

public class FlopStrategy implements Strategy {
    @Override
    public int process(Session session) {
        CardAnalyzer analyzer = new DefaultCardAnalyzer();
        BetSelector betSelector = new BetSelector(session);
        CardAnalyzeResult cardAnalyzeResult = analyzer.analyzeCards(session.getAllCards());
        System.out.println("cardAnalyzeResult: " + cardAnalyzeResult);
        Combination combination = cardAnalyzeResult.getCombination();

        if (combination.getValue() >= Combination.TRIPLE.getValue()) {
            System.out.println("Got triple or more ");
            return betSelector.getMaximumRaise();
        }

        if (combination.getValue() >= Combination.DOUBLE_PAIR.getValue()) {
            System.out.println("Got DOUBLE_PAIR");
            return betSelector.check();
        }

        if (combination.getValue() <= Combination.PAIR.getValue()) {
            int countFlashMax = countFlash(session);
            if (countFlashMax < 4) {
                System.out.println("No flash oportunity all  " + session.getAllCards());
                return betSelector.check();
            }
        }

        return betSelector.fold();
    }

    private int countFlash(Session session) {
        List<Card> allCards = session.getAllCards();
        HashMap<CardSuits, Integer> suitMap = new HashMap<>();
        for (CardSuits cardSuits : CardSuits.values()) {
            suitMap.put(cardSuits, Integer.valueOf(0));
        }

        for (Card card : allCards) {
            Integer prev = suitMap.get(card.getSuit());
            suitMap.put(card.getSuit(), prev + 1);
        }

        int max = 0;
        for (Integer integer : suitMap.values()) {
            if (integer > max) {
                max = integer;
            }
        }
        return max;
    }

    private boolean isAtLeastFlop(Session session) {
        return session.getCommunity_cards() != null && session.getCommunity_cards().size() > 3;
    }
}
