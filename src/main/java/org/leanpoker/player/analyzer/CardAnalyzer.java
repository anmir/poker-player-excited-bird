package org.leanpoker.player.analyzer;

import org.leanpoker.player.Card;

import java.util.List;

/**
 * Created by andrey on 12.08.17.
 */
public interface CardAnalyzer {
    CardAnalyzeResult analyzeCards(List<Card> cards);

    Boolean isOrdered(List<Card> cards);

    Boolean isAllCardsHasSameSuits(List<Card> cards);

    Boolean allCardsAreHigh(List<Card> cards);

    Boolean containsHighCard(List<Card> cards);
}
