package org.leanpoker.player.strategy.particular;

import org.leanpoker.player.Session;
import org.leanpoker.player.analyzer.CardAnalyzer;
import org.leanpoker.player.analyzer.DefaultCardAnalyzer;
import org.leanpoker.player.strategy.BetSelector;
import org.leanpoker.player.strategy.Strategy;

public class FlopStrategy implements Strategy {
    @Override
    public int process(Session session) {
        CardAnalyzer analyzer = new DefaultCardAnalyzer();
        BetSelector betSelector = new BetSelector(session);

        return 0;
    }
}
