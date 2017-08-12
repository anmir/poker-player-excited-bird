package org.leanpoker.player.analyzer;

import org.leanpoker.player.Card;
import org.leanpoker.player.constants.Combination;

import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.leanpoker.player.constants.Combination.BIGGEST_CARD;

/**
 * Created by andrey on 12.08.17.
 */
public class DefaultCardAnalyzer implements CardAnalyzer {

    @Override
    public Combination analyzeCards(List<Card> holeCards, List<Card> tableCards) {
        if (isNotEmpty(holeCards) && isEmpty(tableCards)) {

        }

        return BIGGEST_CARD;
    }

    public static Boolean isPair(List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            Card comparableCard = cards.get(i);
            for (int j = i + 1; j < cards.size(); j++) {
                Card card = cards.get(j);
                if (card.getRank().equals(comparableCard.getRank())) {
                    return true;
                }
            }
        }
        return false;
    }

}
