package org.leanpoker.player.strategy;

import org.leanpoker.player.Session;

public interface Strategy {
    int process(Session session);
}
