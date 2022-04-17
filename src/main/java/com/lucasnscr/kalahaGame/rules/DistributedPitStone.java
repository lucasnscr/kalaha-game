package com.lucasnscr.kalahaGame.rules;

import com.lucasnscr.kalahaGame.model.Game;
import com.lucasnscr.kalahaGame.model.Pit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DistributedPitStone extends KalahaRules{

    @Override
    public void apply(Game game, Pit currentPit) {
        log.debug("check the rules for distributing stone to the next pit(s)");
        Integer stoneToDistribute = currentPit.getStoneCount();
        currentPit.setStoneCount(0);
        for (int i = 0; i < stoneToDistribute; i++) {
            currentPit = game.getBoard().getNextPit(currentPit);
            log.debug("next pit {}", currentPit);
            if (currentPit.isDistributable(game.getGameStatus())) {
                currentPit.setStoneCount(currentPit.getStoneCount() + 1);
            }else{
                i--;
            }
        }
        this.next.apply(game, currentPit);
    }
}
