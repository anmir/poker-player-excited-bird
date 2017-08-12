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
    private RaiseSelector raiseSelector;

    public int process(Session session) {
        raiseSelector = new RaiseSelector(session);

        CardAnalyzeResult cardAnalyzeResult = analyzer.analyzeCards(session.getAllCards());
        Combination combination = cardAnalyzeResult.getCombination();

        Integer biggestCard = cardAnalyzeResult.getBiggestCardInCombination();
        int analyzes = getKoef(combination) + biggestCard;

        if (analyzes < getKoef(Combination.PAIR)) {
            System.out.println("No combinations");
            if (session.getCommunity_cards() != null
                    && session.getCommunity_cards().size() ==0) {
                if (biggestCard >= CardRanks._8.getOrdr()){
                    if(biggestCard>=CardRanks.KING.getOrdr()){
                        return raiseSelector.getMaximumRaise();
                    }
                    return  raiseSelector.getMinimalRaise();
                }
            }
            return raiseSelector.check();
        } else if (analyzes < getKoef(Combination.DOUBLE_PAIR)) {
            System.out.println("Got double pair");
            if (session.getCommunity_cards() != null && session.getCommunity_cards().size() > 3) {
                return raiseSelector.check();
            }
        } else if (analyzes > getKoef(Combination.TRIPLE)) {
            System.out.println("Got triple");
            return raiseSelector.getMaximumRaise();
        }

        System.out.println("Got pair?");
        return raiseSelector.getMinimalRaise();
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
