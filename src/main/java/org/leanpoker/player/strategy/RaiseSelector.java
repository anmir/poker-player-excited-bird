package org.leanpoker.player.strategy;

import org.leanpoker.player.Session;

import java.math.BigDecimal;

public class RaiseSelector {
    private Session session;

    public RaiseSelector(Session session) {
        this.session = session;
    }

    public Integer check() {
        return session.getCurrent_buy_in().intValue();
    }

    public Integer getMinimalRaise() {
        return session.getMinimum_raise().intValue();
    }

    public Integer getMaximumRaise() {
        return session.getPlayer().getStack().intValue();
    }
}
