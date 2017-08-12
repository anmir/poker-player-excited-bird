package org.leanpoker.player;

import lombok.Data;
import org.leanpoker.player.constants.CardRanks;
import org.leanpoker.player.constants.CardSuits;

//@Data
public class Card {
    private String rank;
    private String suit;

    public CardRanks getRank() {
//        CardRanks.values();
        for (CardRanks cardRank : CardRanks.values()) {
            if (cardRank.getVal().equals(rank)){
                return cardRank;
            }
        }
        throw new RuntimeException("horrible error! can't parse rank '" + rank +"'");
    }

    public CardSuits getSuit() {
//        CardRanks.values();
        for (CardSuits cardSuit : CardSuits.values()) {
            if (cardSuit.getVal().equals(suit)){
                return cardSuit;
            }
        }
        throw new RuntimeException("horrible error! can't parse suit '" + suit +"'");
    }
}
