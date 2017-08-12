package org.leanpoker.player.constants;

/**
 * Created by andrey on 12.08.17.
 */
public enum CardRanks {
    _2("2", 2),
    _3("3", 3),
    _4("4", 4),
    _5("5", 5),
    _6("6", 6),
    _7("7", 7),
    _8("8", 8),
    _9("9", 9),
    _10("10", 10),
    JAN("J", 11),
    QUEEN("Q", 12),
    KING("K", 13),
    ACE("A", 14);

    int ordr;
    String val;

    CardRanks(String val, int ordr) {
        this.ordr = ordr;
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public Integer getOrdr() {
        return ordr;
    }

    public int compare(CardRanks other) {
        return Integer.compare(this.ordr, other.ordr);
    }
}
