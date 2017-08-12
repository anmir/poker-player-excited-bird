package org.leanpoker.player.analyzer;

import org.junit.Assert;
import org.junit.Test;
import org.leanpoker.player.Card;
import org.leanpoker.player.constants.CardSuits;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 12.08.17.
 */
public class DefaultCardAnalyzerTest {
    private List<Card> cards = new ArrayList<>();
    private Card card1 = new Card("2", CardSuits.CLUBS.getVal());
    private Card card2 = new Card("2", CardSuits.DIAMONDS.getVal());

    @Test
    public void isPair() throws Exception {
        cards.add(card1);
        cards.add(card2);
        Integer pair = DefaultCardAnalyzer.isPair(cards);
        Assert.assertEquals(new Integer(2), pair);
    }

    @Test
    public void isTriple() throws Exception {
        cards.add(card1);
        cards.add(card2);
        cards.add(card2);
        Integer biggestTripleCard = DefaultCardAnalyzer.isTriple(cards);
        Assert.assertEquals(new Integer(2), biggestTripleCard);
    }
}