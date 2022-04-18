package com.lucasnscr.kalahaGame.rules;

import com.lucasnscr.kalahaGame.model.Game;
import com.lucasnscr.kalahaGame.model.GameStatus;
import com.lucasnscr.kalahaGame.model.Pit;

/**
 * State for check last stone and rules
 */
public class EndPit extends KalahaRules{
    @Override
    public void apply(Game game, Pit endPit) {
        empty(game, endPit);
        nextPlayer(game, endPit);
        this.next.apply(game, endPit);

    }

    private void empty(Game game, Pit endPit){

        if (!endPit.isHouse() && endPit.isPlayerPit(game.getGameStatus()) && endPit.getStoneCount().equals(1) ){
            Pit oppositePit = game.getBoard().getOppositePit(endPit);
            if (oppositePit.getStoneCount() > 0) {
                Pit house = game.getBoard().getPlayerHouse(endPit.getPlayerIndex());
                house.setStoneCount((house.getStoneCount() + oppositePit.getStoneCount()) + endPit.getStoneCount());
                oppositePit.setStoneCount(0);
                endPit.setStoneCount(0);
            }
        }
    }

    private void nextPlayer(Game game, Pit endPit){

        if(endPit.isPlayer1House() && game.getGameStatus().equals(GameStatus.PLAYER1_MOVIMENT)){
            game.setGameStatus(GameStatus.PLAYER1_MOVIMENT);
        }
        else if(endPit.isPlayer2House() && game.getGameStatus().equals(GameStatus.PLAYER2_MOVIMENT)){
            game.setGameStatus(GameStatus.PLAYER2_MOVIMENT);
        }
        else{
            GameStatus changeStage = game.getGameStatus() == GameStatus.PLAYER1_MOVIMENT? GameStatus.PLAYER2_MOVIMENT : GameStatus.PLAYER1_MOVIMENT;
            game.setGameStatus(changeStage);
        }
    }
}
