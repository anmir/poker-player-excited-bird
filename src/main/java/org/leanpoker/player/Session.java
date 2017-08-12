package org.leanpoker.player;

import java.math.BigDecimal;

/**
 * Created by liy on 12.08.2017.
 */
public class Session {

//    "game_id": "598eb6f78314440004000015",
//            "round": 0,
//            "small_blind": 2,
//            "big_blind": 4,
//            "orbits": 0,
//            "dealer": 2,
//            "community_cards": [],
//            "current_buy_in": 4,
//            "pot": 6,
//            "in_action": 2,
//            "minimum_raise": 2,
//            "bet_index": 3

    private Integer round;
    private BigDecimal small_blind;
    private BigDecimal big_blind;
    private Number orbits;
    private Integer dealer;
    private BigDecimal current_buy_in;
    private BigDecimal pot;
    private Integer in_action;
    private BigDecimal minimum_raise;
    private Integer bet_index;

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public BigDecimal getSmall_blind() {
        return small_blind;
    }

    public void setSmall_blind(BigDecimal small_blind) {
        this.small_blind = small_blind;
    }

    public BigDecimal getBig_blind() {
        return big_blind;
    }

    public void setBig_blind(BigDecimal big_blind) {
        this.big_blind = big_blind;
    }

    public Number getOrbits() {
        return orbits;
    }

    public void setOrbits(Number orbits) {
        this.orbits = orbits;
    }

    public Integer getDealer() {
        return dealer;
    }

    public void setDealer(Integer dealer) {
        this.dealer = dealer;
    }

    public BigDecimal getCurrent_buy_in() {
        return current_buy_in;
    }

    public void setCurrent_buy_in(BigDecimal current_buy_in) {
        this.current_buy_in = current_buy_in;
    }

    public BigDecimal getPot() {
        return pot;
    }

    public void setPot(BigDecimal pot) {
        this.pot = pot;
    }

    public Integer getIn_action() {
        return in_action;
    }

    public void setIn_action(Integer in_action) {
        this.in_action = in_action;
    }

    public BigDecimal getMinimum_raise() {
        return minimum_raise;
    }

    public void setMinimum_raise(BigDecimal minimum_raise) {
        this.minimum_raise = minimum_raise;
    }

    public Integer getBet_index() {
        return bet_index;
    }

    public void setBet_index(Integer bet_index) {
        this.bet_index = bet_index;
    }

    @Override
    public String toString() {
        return "Session{" +
                "round=" + round +
                ", small_blind=" + small_blind +
                ", big_blind=" + big_blind +
                ", orbits=" + orbits +
                ", dealer=" + dealer +
                ", current_buy_in=" + current_buy_in +
                ", pot=" + pot +
                ", in_action=" + in_action +
                ", minimum_raise=" + minimum_raise +
                ", bet_index=" + bet_index +
                '}';
    }
}

/*
*
*
* */