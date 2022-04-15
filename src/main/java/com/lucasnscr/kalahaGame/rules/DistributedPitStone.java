package com.lucasnscr.kalahaGame.rules;

import com.lucasnscr.kalahaGame.model.Game;
import com.lucasnscr.kalahaGame.model.Pit;

public class DistributedPitStone extends KalahaRules{

    @Override
    public void apply(Game game, Pit currentPit) {
        Integer stoneDistribute = currentPit.getStoneCount();
        currentPit.setStoneCount(0);
        for (int i =0; i<stoneDistribute; i++){
            currentPit = game.getBoard().getNextPit(currentPit);
            if (currentPit.isDistributable(game.getGameStatus())){
                currentPit.setStoneCount(currentPit.getStoneCount()+1);
            }else {
                i--;
            }
        }
        this.next.apply(game, currentPit);
    }
}
