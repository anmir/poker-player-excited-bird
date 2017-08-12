package org.leanpoker.player.strategy;

import org.leanpoker.player.Session;

import java.math.BigDecimal;

public class BetSelector {
    private Session session;

    public BetSelector(Session session) {
        this.session = session;
    }

    public Integer fold() {
        return 0;
    }

    public Integer check() {
        return session.isWeFirst() ? 0 : session.getCurrent_buy_in().intValue();
    }

    public Integer getMinimalRaise() {
        return session.getMinimum_raise().intValue();
    }

    public Integer getMaximumRaise() {
        return session.getPlayer().getStack().intValue();
    }
}
