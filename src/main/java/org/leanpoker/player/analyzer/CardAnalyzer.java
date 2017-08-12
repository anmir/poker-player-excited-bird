package org.leanpoker.player.analyzer;

import org.leanpoker.player.Card;
import org.leanpoker.player.constants.Combination;

import java.util.List;

/**
 * Created by andrey on 12.08.17.
 */
public interface CardAnalyzer {
    Combination analyzeCards(List<Card> cards);
}
