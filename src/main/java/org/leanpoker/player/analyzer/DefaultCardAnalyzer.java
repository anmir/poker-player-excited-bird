package org.leanpoker.player.analyzer;

import org.leanpoker.player.Card;
import org.leanpoker.player.constants.Combination;

import java.util.Collections;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

/**
 * Created by andrey on 12.08.17.
 */
public class DefaultCardAnalyzer implements CardAnalyzer {

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

    @Override
    public CardAnalyzeResult analyzeCards(List<Card> cards) {
        if (isNotEmpty(cards)) {
            Integer biggestCardRankInPair = isPair(cards);
            if (biggestCardRankInPair != null) {
                return new CardAnalyzeResult(Combination.PAIR, biggestCardRankInPair);
            }
        }
        Card biggestCard = Collections.max(cards);
        return new CardAnalyzeResult(Combination.BIGGEST_CARD, biggestCard.getRank().getOrdr());
    }

}
