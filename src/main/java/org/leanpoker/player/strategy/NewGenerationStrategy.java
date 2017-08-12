package org.leanpoker.player.strategy;

import org.leanpoker.player.Session;
import org.leanpoker.player.strategy.particular.FlopStrategy;
import org.leanpoker.player.strategy.particular.PreFlopStrategy;
import org.leanpoker.player.strategy.particular.RiverStrategy;

/**
 * Created by andrey on 12.08.17.
 */
public class NewGenerationStrategy implements Strategy {
    private PreFlopStrategy preflopStrategy = new PreFlopStrategy();
    private FlopStrategy flopStrategy = new FlopStrategy();
    private RiverStrategy riverStrategy = new RiverStrategy();

    @Override
    public int process(Session session) {
        if (isPreFlop(session)) {
            System.out.println("----------preFlop::::");
            return preflopStrategy.process(session);
        }
        if (isFlop(session)) {
            System.out.println("----------flop::::");
            return flopStrategy.process(session);
        }
        System.out.println("----------river::::");
        return riverStrategy.process(session);
    }

    private boolean isPreFlop(Session session) {
        return session.getCommunity_cards() == null || session.getCommunity_cards().size() == 0;
    }

    private boolean isFlop(Session session) {
        return session.getCommunity_cards() == null || session.getCommunity_cards().size() == 3;
    }
}
