package org.leanpoker.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.leanpoker.player.constants.CardRanks;
import org.leanpoker.player.constants.CardSuits;

@Data
@NoArgsConstructor
public class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public CardRanks getRank() {
//        CardRanks.values();
        for (CardRanks cardRank : CardRanks.values()) {
            if (cardRank.getVal().equals(rank)) {
                return cardRank;
            }
        }
        throw new RuntimeException("horrible error! can't parse rank '" + rank + "'");
    }

    public CardSuits getSuit() {
//        CardRanks.values();
        for (CardSuits cardSuit : CardSuits.values()) {
            if (cardSuit.getVal().equals(suit)) {
                return cardSuit;
            }
        }
        throw new RuntimeException("horrible error! can't parse suit '" + suit + "'");
    }

    public int whatBigger(Card other){
        if(other == null){
            return 1;
        }
       return getRank().compare(other.getRank());
    }
}
