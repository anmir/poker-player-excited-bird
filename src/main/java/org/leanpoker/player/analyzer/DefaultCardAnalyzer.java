package org.leanpoker.player.analyzer;

import org.leanpoker.player.Card;
import org.leanpoker.player.constants.Combination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

/**
 * Created by andrey on 12.08.17.
 */
public class DefaultCardAnalyzer implements CardAnalyzer {

    @Override
    public CardAnalyzeResult analyzeCards(List<Card> cards) {
        if (isNotEmpty(cards)) {
            Integer biggestPairRank = isPair(cards);
            if (biggestPairRank != null) {
                return new CardAnalyzeResult(Combination.PAIR, biggestPairRank);
            }
            Integer biggestTripleRank = isTriple(cards);
            if (biggestTripleRank != null) {
                return new CardAnalyzeResult(Combination.TRIPLE, biggestTripleRank);
            }

            Integer biggestDoublePairRank = isDoublePair(cards);
            if (biggestDoublePairRank != null) {
                return new CardAnalyzeResult(Combination.DOUBLE_PAIR, biggestDoublePairRank);
            }
            Integer biggestStraightRank = isStraight(cards);
            if (biggestStraightRank != null) {
                return new CardAnalyzeResult(Combination.STRAIGHT, biggestStraightRank);
            }

        }
        Card biggestCard = Collections.max(cards);
        return new CardAnalyzeResult(Combination.BIGGEST_CARD, biggestCard.getRank().getOrdr());
    }

    public static Integer isDoublePair(List<Card> cards) {

        Integer biggestCardInFirstPair = isPair(cards);
        if (biggestCardInFirstPair != null) {
            // удаляем текущие, ищем еще пару
            List<Card> cardsWithoutFirstPair = new ArrayList<>(cards);
            for (int i = 0; i < cardsWithoutFirstPair.size(); i++) {
                Card card = cardsWithoutFirstPair.get(i);
                if (card.getRank().getOrdr().equals(biggestCardInFirstPair)) {
                    cardsWithoutFirstPair.remove(card);
                }
            }
            Integer biggestCardInSecondPair = isPair(cardsWithoutFirstPair);
            if (biggestCardInSecondPair != null) {
                return biggestCardInSecondPair;
            }
        }
        return null;
    }


    public static Integer isPair(List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            Card comparableCard = cards.get(i);
            for (int j = i + 1; j < cards.size(); j++) {
                Card card = cards.get(j);
                if (card.getRank().equals(comparableCard.getRank())) {
                    return card.getRank().getOrdr();
                }
            }
        }
        return null;
    }

    public static Integer isTriple(List<Card> cards) {
        int counter = 0;
        for (int i = 0; i < cards.size(); i++) {
            Card comparableCard = cards.get(i);
            for (int j = i + 1; j < cards.size(); j++) {
                Card card = cards.get(j);
                if (card.getRank().equals(comparableCard.getRank())) {
                    counter += 1;
                }
                if (counter == 2) {
                    return card.getRank().getOrdr();
                }
            }
        }
        return null;
    }

    public static Integer isStraight(List<Card> cards) {
        Collections.sort(cards);
        boolean diffBiggerThanOne = false;
        for (int i = 0; i < cards.size() - 1; i++) {
            Card card = cards.get(i);
            Card card2 = cards.get(i + 1);
            int diff = card2.getRank().getOrdr() - card.getRank().getOrdr();
            if (diff != 1) {
                diffBiggerThanOne = true;
            }
        }
        if (diffBiggerThanOne) {
            return null;
        }
        Card biggestCardd = cards.get(cards.size() - 1);
        return biggestCardd.getRank().getOrdr();
    }

}
