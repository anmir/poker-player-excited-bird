package org.leanpoker.player;

import java.util.List;

/**
 * Created by liy on 12.08.2017.
 */
public class Person {
    /*
    *     {
      "name": "Excited Bird",
      "stack": 1000,
      "status": "active",
      "bet": 0,
      "hole_cards": [
        {
          "rank": "10",
          "suit": "spades"
        },
        {
          "rank": "Q",
          "suit": "diamonds"
        }
      ],
      "time_used": 0,
      "version": "Default Java folding player",
      "id": 2
    }*/
    private String name;
    private List<Card> hole_cards;
//    private String sad;
//    private String sad;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getHole_cards() {
        return hole_cards;
    }

    public void setHole_cards(List<Card> hole_cards) {
        this.hole_cards = hole_cards;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", hole_cards=" + hole_cards +
                '}';
    }
}