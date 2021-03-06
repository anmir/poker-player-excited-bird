package org.leanpoker.player.analyzer;

import org.leanpoker.player.Card;
import org.leanpoker.player.constants.CardRanks;
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
            Integer biggestStraightFlush = isStraightFlush(cards);
            if (biggestStraightFlush != null) {
                return new CardAnalyzeResult(Combination.STRAIGHT_FLASH, biggestStraightFlush);
            }
            Integer biggestFourKindRank = isFourKind(cards);
            if (biggestFourKindRank != null) {
                return new CardAnalyzeResult(Combination.FOUR_KIND, biggestFourKindRank);
            }
            Integer biggestFlushRank = isFlush(cards);
            if (biggestFlushRank != null) {
                return new CardAnalyzeResult(Combination.FLUSH, biggestFlushRank);
            }
            Integer biggestStraightRank = isStraight(cards);
            if (biggestStraightRank != null) {
                return new CardAnalyzeResult(Combination.STRAIGHT, biggestStraightRank);
            }
            Integer biggestDoublePairRank = isDoublePair(cards);
            if (biggestDoublePairRank != null) {
                return new CardAnalyzeResult(Combination.DOUBLE_PAIR, biggestDoublePairRank);
            }
            Integer biggestTripleRank = isTriple(cards);
            if (biggestTripleRank != null) {
                return new CardAnalyzeResult(Combination.TRIPLE, biggestTripleRank);
            }
            Integer biggestPairRank = isPair(cards);
            if (biggestPairRank != null) {
                return new CardAnalyzeResult(Combination.PAIR, biggestPairRank);
            }

        }
        Card biggestCard = Collections.max(cards);
        return new CardAnalyzeResult(Combination.BIGGEST_CARD, biggestCard.getRank().getOrdr());
    }

    @Override
    public Boolean isOrdered(List<Card> cards) {
        return isOrderedInternal(cards);
    }


    public static Boolean isOrderedInternal(List<Card> cards) {
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
        return !diffBiggerThanOne;
    }

    @Override
    public Boolean isAllCardsHasSameSuits(List<Card> cards) {
        return isAllCardsHasSameSuitsInternal(cards);
    }

    public static Boolean isAllCardsHasSameSuitsInternal(List<Card> cards) {
        int sameSuitCount = 0;
        for (int i = 0; i < cards.size() - 1; i++) {
            Card card = cards.get(i);
            Card nextCard = cards.get(i + 1);
            if (nextCard.getSuit().equals(card.getSuit())) {
                sameSuitCount += 1;
            }
        }
        if (sameSuitCount == cards.size() - 1) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean allCardsAreHigh(List<Card> cards) {
        return allCardsAreHighInternal(cards);
    }

    @Override
    public Boolean containsHighCard(List<Card> cards) {
        return containsHighCardInternal(cards);
    }

    public static Boolean containsHighCardInternal(List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card.getRank().getOrdr() > CardRanks.JAN.getOrdr()) {
                return true;
            }
        }
        return false;
    }

    public Boolean allCardsAreHighInternal(List<Card> cards) {
        Collections.sort(cards);
        Card card = cards.get(0);
        if (card.getRank().getOrdr() > CardRanks.JAN.getOrdr()) {
            return true;
        }
        return false;
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
        int oneDiffCounter = 0;
        Integer lastBiggerCardRank = null;
        for (int i = 0; i < cards.size() - 1; i++) {
            Card card = cards.get(i);
            Card card2 = cards.get(i + 1);
            int diff = card2.getRank().getOrdr() - card.getRank().getOrdr();
            if (diff == 1) {
                oneDiffCounter += 1;
                lastBiggerCardRank = new Integer(card2.getRank().getOrdr());
            }
        }
        if (oneDiffCounter >= 4) {
            return lastBiggerCardRank;
        }
        return null;
    }

    public static Integer isFlush(List<Card> cards) {
        if (cards.size() < 5) {
            return null;
        }
        int sameSuitCount = 0;
        for (int i = 0; i < cards.size() - 1; i++) {
            Card card = cards.get(i);
            Card nextCard = cards.get(i + 1);
            if (nextCard.getSuit().equals(card.getSuit())) {
                sameSuitCount += 1;
            }
        }
        if (sameSuitCount == 4) {
            Collections.sort(cards);
            Card card = cards.get(cards.size() - 1);
            return card.getRank().getOrdr();
        }
        return null;
    }

    public static Integer isFourKind(List<Card> cards) {
        if (cards.size() < 4) {
            return null;
        }
        int matchesCounter = 0;
        for (int i = 0; i < cards.size() - 1; i++) {
            Card card = cards.get(i);
            Card nextCard = cards.get(i + 1);
            if (nextCard.getRank().getOrdr().equals(card.getRank().getOrdr())) {
                matchesCounter += 1;
            }
        }
        if (matchesCounter == 3) {
            Collections.sort(cards);
            Card card = cards.get(cards.size() - 1);
            return card.getRank().getOrdr();
        }
        return null;
    }

    public static Integer isStraightFlush(List<Card> cards) {
        Integer biggestFlushCard = isFlush(cards);
        if (biggestFlushCard == null) {
            return null;
        }
        Integer biggestStraightCard = isStraight(cards);
        if (biggestStraightCard == null) {
            return null;
        }
        Collections.sort(cards);
        Card card = cards.get(cards.size() - 1);
        return card.getRank().getOrdr();
    }
}
