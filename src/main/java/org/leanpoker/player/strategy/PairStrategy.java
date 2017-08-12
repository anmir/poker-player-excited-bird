package org.leanpoker.player.strategy;

import org.leanpoker.player.Card;
import org.leanpoker.player.Session;
import org.leanpoker.player.analyzer.CardAnalyzeResult;
import org.leanpoker.player.analyzer.CardAnalyzer;
import org.leanpoker.player.analyzer.DefaultCardAnalyzer;
import org.leanpoker.player.constants.CardRanks;
import org.leanpoker.player.constants.Combination;

import java.util.ArrayList;
import java.util.List;

public class PairStrategy implements Strategy {
    private CardAnalyzer analyzer = new DefaultCardAnalyzer();
    private BetSelector betSelector;

    public int process(Session session) {
        betSelector = new BetSelector(session);

        CardAnalyzeResult cardAnalyzeResult = analyzer.analyzeCards(session.getAllCards());
        Combination combination = cardAnalyzeResult.getCombination();

        Integer biggestCard = cardAnalyzeResult.getBiggestCardInCombination();
        int analyzes = getKoef(combination) + biggestCard;

        if (analyzes < getKoef(Combination.PAIR)) {
            System.out.println("No combinations");
            if (session.getCommunity_cards() != null
                && session.getCommunity_cards().size() == 0) {
                if (isFlashOpotunity(session)){
                        return raiseSelector.getMaximumRaise();
                } else if (biggestCard >= CardRanks._8.getOrdr()) {
                    if (biggestCard >= CardRanks.KING.getOrdr()) {
                        return betSelector.getMinimalRaise() * 4;
                    }
                    return betSelector.getMinimalRaise();
                }
            }
            return betSelector.check();
        } else if (analyzes < getKoef(Combination.DOUBLE_PAIR)) {
            if (session.getCommunity_cards() != null && session.getCommunity_cards().size() > 3) {
                System.out.println("Got double pair");
                return betSelector.check();
            }
        } else if (analyzes > getKoef(Combination.TRIPLE)) {
            System.out.println("Got triple");
            return betSelector.getMaximumRaise();
        }

        System.out.println("Got pair?");
        return betSelector.getMinimalRaise();
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

    @Override
    public int process(List<Card> handCards, List<Card> tableCards) {
        List cards = new ArrayList(handCards);
        cards.add(tableCards);
        CardAnalyzeResult cardAnalyzeResult = analyzer.analyzeCards(cards);
        Combination combination = cardAnalyzeResult.getCombination();
        return combination == Combination.PAIR ? 80 : 0;
    }
}
