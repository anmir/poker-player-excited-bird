package org.leanpoker.player.strategy;

import org.leanpoker.player.Session;
import org.leanpoker.player.strategy.particular.FlopStrategy;
import org.leanpoker.player.strategy.particular.PreFlopStrategy;
import org.leanpoker.player.strategy.particular.RiverStrategy;
import org.leanpoker.player.strategy.particular.TurnStrategy;

public class NewGenerationStrategy implements Strategy {
    private PreFlopStrategy preflopStrategy = new PreFlopStrategy();
    private FlopStrategy flopStrategy = new FlopStrategy();
    private TurnStrategy turnStrategy = new TurnStrategy();
    private RiverStrategy riverStrategy = new RiverStrategy();

    @Override
    public int process(Session session) {
        if (isPreFlop(session)) {
            System.out.println("----------preFlop::::" + session.getGame_id());
            return preflopStrategy.process(session);
        }
        if (isFlop(session)) {
            System.out.println("----------flop::::" + session.getGame_id());
            return flopStrategy.process(session);
        }
        if (isTurn(session)) {
            System.out.println("----------turn::::" + session.getGame_id());
            return turnStrategy.process(session);
        }
        System.out.println("----------river::::" + session.getGame_id());
        return riverStrategy.process(session);
    }

    private boolean isPreFlop(Session session) {
        return session.getCommunity_cards() == null && session.getCommunity_cards().size() == 0;
    }

    private boolean isFlop(Session session) {
        return session.getCommunity_cards() != null && session.getCommunity_cards().size() == 3;
    }

    private boolean isTurn(Session session) {
        return session.getCommunity_cards() != null && session.getCommunity_cards().size() == 4;
    }
}
