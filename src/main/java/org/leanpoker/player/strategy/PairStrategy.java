package org.leanpoker.player.strategy;

import org.leanpoker.player.Card;
import org.leanpoker.player.Session;
import org.leanpoker.player.analyzer.CardAnalyzeResult;
import org.leanpoker.player.analyzer.CardAnalyzer;
import org.leanpoker.player.analyzer.DefaultCardAnalyzer;
import org.leanpoker.player.constants.CardRanks;
import org.leanpoker.player.constants.CardSuits;
import org.leanpoker.player.constants.Combination;

import java.util.HashMap;
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
                if (isFlashOpotunity(session)){
                        return getPreflopMax(session);
                } else if (biggestCard >= CardRanks._8.getOrdr()){
                    if(biggestCard>=CardRanks.KING.getOrdr()){
                        return getPreflopMax(session);
                    }
                    return betSelector.getMinimalRaise();
                }
            }else {
                int countFlashMax = countFlash(session);
                if(countFlashMax <4) {
                    System.out.println("No flash oportunity  " + ownCards);
                    System.out.println("No flash oportunity all  " + session.getAllCards());
                    return betSelector.fold();
                }

            }
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

    private int getPreflopMax(Session session) {
        return  Math.max(betSelector.getMinimalRaise(), session.getMinimum_raise().intValue() * 4);
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
    private int countFlash(Session session){
        List<Card> allCards = session.getAllCards();
        HashMap<CardSuits, Integer> suitMap = new HashMap<>();
        for (CardSuits cardSuits : CardSuits.values()) {
            suitMap.put(cardSuits, Integer.valueOf(0));
        }

        for (Card card : allCards) {
            Integer prev = suitMap.get(card.getSuit());
            suitMap.put(card.getSuit(), prev+1);
        }

        int max = 0;
        for (Integer integer : suitMap.values()) {
            if(integer > max){
                max= integer;
            }
        }
        return max;
    }

    private int getKoef(Combination combination) {
        return combination.getValue() * 100;
    }

}
