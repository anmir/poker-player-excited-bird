package org.leanpoker.player.constants;

/**
 * Created by andrey on 12.08.17.
 */
public enum Combinations {
    BIGGEST_CARD(1),
    PAIR(2),
    DOUBLE_PAIR(3),
    TRIPLE(4),
    STRAIGHT(5),
    FLUSH(6),
    FULL_HOUSE(7),
    FOUR_KIND(8),
    STRAIGHT_FLASH(9),
    ROYAL_FLASH(10);
    private Integer value;

    Combinations(Integer value) {
        this.value = value;
    }
}
