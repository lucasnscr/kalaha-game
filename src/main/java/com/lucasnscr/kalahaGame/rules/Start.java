package com.lucasnscr.kalahaGame.rules;

import com.lucasnscr.kalahaGame.exception.IllegalMoveException;
import com.lucasnscr.kalahaGame.model.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Start State for analysis distributing stones.
 */
@Slf4j
public class Start extends KalahaRules{

    /**
     * Rules applied
     * @param game
     * @param startPit
     */
    @Override
    public void apply(Game game, Pit startPit) {
        log.debug("analysis rules to start pit {}", startPit);
        checkPlayerTurnRule(game, startPit);
        checkEmptyStartRULE(startPit);
        this.next.apply(game, startPit);
    }

    private void checkPlayerTurnRule(Game game, Pit startPit){

        if(game.getGameStatus().equals(GameStatus.INIT)) {
            GameStatus gameStatus =  startPit.getPlayerIndex().equals(Player.PLAYER1_INDEX) ? GameStatus.PLAYER1_MOVIMENT : GameStatus.PLAYER2_MOVIMENT;
            game.setGameStatus(gameStatus);
        }

        if((game.getGameStatus().equals(GameStatus.PLAYER1_MOVIMENT) && startPit.getPitIndex() >= Board.PLAYER1_HOUSE) ||
                (game.getGameStatus().equals(GameStatus.PLAYER2_MOVIMENT) && startPit.getPitIndex() <= Board.PLAYER1_HOUSE)){
            throw new IllegalMoveException("Incorrect pit to play");
        }
    }

    private void checkEmptyStartRULE(Pit startPit){

        if(startPit.getStoneCount() == 0){
            throw new IllegalMoveException("not start game with empty pit");
        }
    }
}
