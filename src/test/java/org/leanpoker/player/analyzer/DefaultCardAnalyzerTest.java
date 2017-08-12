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

    @Test
    public void isDoublePair() throws Exception {
        List<Card> cards = new ArrayList<>();
        Card card1 = new Card("2", CardSuits.CLUBS.getVal());
        Card card2 = new Card("2", CardSuits.DIAMONDS.getVal());
        Card card3 = new Card("4", CardSuits.CLUBS.getVal());
        Card card4 = new Card("8", CardSuits.SPADES.getVal());
        Card card5 = new Card("4", CardSuits.DIAMONDS.getVal());

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);

        Integer biggestTripleCard = DefaultCardAnalyzer.isDoublePair(cards);
        Assert.assertEquals(new Integer(4), biggestTripleCard);
    }


    @Test
    public void isStraight() throws Exception {
        List<Card> cards = new ArrayList<>();
        Card card1 = new Card("6", CardSuits.CLUBS.getVal());
        Card card2 = new Card("2", CardSuits.DIAMONDS.getVal());
        Card card3 = new Card("4", CardSuits.CLUBS.getVal());
        Card card4 = new Card("3", CardSuits.SPADES.getVal());
        Card card5 = new Card("5", CardSuits.DIAMONDS.getVal());

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);

        Integer biggestTripleCard = DefaultCardAnalyzer.isStraight(cards);
        Assert.assertEquals(new Integer(6), biggestTripleCard);
    }

    @Test
    public void isFlush() throws Exception {
        List<Card> cards = new ArrayList<>();
        Card card1 = new Card("6", CardSuits.DIAMONDS.getVal());
        Card card2 = new Card("2", CardSuits.DIAMONDS.getVal());
        Card card3 = new Card("4", CardSuits.DIAMONDS.getVal());
        Card card4 = new Card("3", CardSuits.DIAMONDS.getVal());
        Card card5 = new Card("5", CardSuits.DIAMONDS.getVal());

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);

        Integer biggestTripleCard = DefaultCardAnalyzer.isFlush(cards);
        Assert.assertEquals(new Integer(6), biggestTripleCard);
    }

    @Test
    public void isnotFlush() throws Exception {
        List<Card> cards = new ArrayList<>();
        Card card1 = new Card("6", CardSuits.DIAMONDS.getVal());
        Card card2 = new Card("2", CardSuits.SPADES.getVal());
        Card card3 = new Card("4", CardSuits.DIAMONDS.getVal());
        Card card4 = new Card("3", CardSuits.CLUBS.getVal());
        Card card5 = new Card("5", CardSuits.DIAMONDS.getVal());

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);

        Integer biggestCard = DefaultCardAnalyzer.isFlush(cards);
        Assert.assertEquals(null, biggestCard);
    }

}