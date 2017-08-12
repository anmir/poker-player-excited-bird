package org.leanpoker.player.strategy;

import org.leanpoker.player.Card;
import org.leanpoker.player.Session;
import org.leanpoker.player.analyzer.CardAnalyzeResult;
import org.leanpoker.player.analyzer.CardAnalyzer;
import org.leanpoker.player.analyzer.DefaultCardAnalyzer;
import org.leanpoker.player.constants.CardRanks;
import org.leanpoker.player.constants.Combination;

import java.util.List;

public class PairStrategy implements Strategy {
    private CardAnalyzer analyzer = new DefaultCardAnalyzer();
    private BetSelector betSelector;

    public int process(Session session) {
        betSelector = new BetSelector(session);

        CardAnalyzeResult cardAnalyzeResult = analyzer.analyzeCards(session.getAllCards());
        System.out.println("cardAnalyzeResult: " + cardAnalyzeResult);
        Combination combination = cardAnalyzeResult.getCombination();

        Integer biggestCard = cardAnalyzeResult.getBiggestCardInCombination();
        int analyzes = getKoef(combination) + biggestCard;

        List<Card> ownCards = session.getOwnCards();

        if (analyzes < getKoef(Combination.PAIR)) {
            System.out.println("No combinations  " + ownCards);
            if (isPreFlop(session)) {
                if (isFlashOpotunity(session)) {
                    return getPreflopMax();
                } else if (biggestCard >= CardRanks._8.getOrdr()) {
                    if (biggestCard >= CardRanks.KING.getOrdr()) {
                        return getPreflopMax();
                    }
                    return betSelector.getMinimalRaise();
                }
            }
            return betSelector.check();
        } else if (analyzes < getKoef(Combination.DOUBLE_PAIR)) {
            if (session.getCommunity_cards() != null && session.getCommunity_cards().size() > 3) {
                System.out.println("Got less then double pair " + ownCards);
                return betSelector.check();
            }
        } else if (analyzes > getKoef(Combination.TRIPLE)) {
            System.out.println("Got triple or more " + ownCards);
            return betSelector.getMaximumRaise();
        }

        System.out.println("Got pair? " + ownCards);
        return betSelector.getMinimalRaise();
    }

    private int getPreflopMax() {
        return betSelector.getMinimalRaise() * 4;
    }

    private boolean isPreFlop(Session session) {
        return session.getCommunity_cards() == null || session.getCommunity_cards().size() == 0;
    }

    private boolean isFlashOpotunity(Session session) {
        List<Card> ownCards = session.getOwnCards();
        if (ownCards == null) {
            return false;
        }
        return ownCards.get(0).getSuit() == ownCards.get(1).getSuit();
    }

    private int getKoef(Combination combination) {
        return combination.getValue() * 100;
    }

}
