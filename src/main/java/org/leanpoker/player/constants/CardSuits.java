package org.leanpoker.player.constants;

/**
 * Created by andrey on 12.08.17.
 */
public enum  CardSuits {
    HEARTS ("hearts"),
    SPADES ("spades"), // пики
    DIAMONDS ("diamonds"),// буби
    CLUBS ( "clubs"); // крести
    String val ;

    CardSuits(String val) {
        this.val = val;
    }
}