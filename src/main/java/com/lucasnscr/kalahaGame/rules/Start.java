package com.lucasnscr.kalahaGame.rules;

import com.lucasnscr.kalahaGame.exception.IllegalMoveException;
import com.lucasnscr.kalahaGame.model.*;

public class Start extends KalahaRules{

    @Override
    public void apply(Game game, Pit startPit) {
        checkTurn(game, startPit);
        checkEmpty(startPit);
        this.next.apply(game, startPit);
    }

    private void checkEmpty(Pit startPit) {
        if (startPit.getStoneCount() == 0){
            throw new IllegalMoveException("Pit to play is invalid");
        }
    }

    private void checkTurn(Game game, Pit startPit) {
        if(game.getGameStatus().equals(GameStatus.INIT)) {
            GameStatus gameStatus =  startPit.getPlayerIndex().equals(Player.PLAYER1_INDEX) ? GameStatus.PLAYER1_MOVIMENT : GameStatus.PLAYER2_MOVIMENT;
            game.setGameStatus(gameStatus);
        }

        if((game.getGameStatus().equals(GameStatus.PLAYER1_MOVIMENT) && startPit.getPitIndex() >= Board.PLAYER1_HOUSE) ||
                (game.getGameStatus().equals(GameStatus.PLAYER2_MOVIMENT) && startPit.getPitIndex() <= Board.PLAYER1_HOUSE)){
            throw new IllegalMoveException("pit not valid to play");
        }
    }
}
