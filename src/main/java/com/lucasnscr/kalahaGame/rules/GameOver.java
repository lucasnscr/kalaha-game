package com.lucasnscr.kalahaGame.rules;

import com.lucasnscr.kalahaGame.model.Board;
import com.lucasnscr.kalahaGame.model.Game;
import com.lucasnscr.kalahaGame.model.GameStatus;
import com.lucasnscr.kalahaGame.model.Pit;

public class GameOver extends KalahaRules{
    @Override
    public void apply(Game game, Pit currentPit) {

        Integer player1PitStoneCount = game.getBoard().getPlayer1PitStoneCount();
        Integer player2PitStoneCount = game.getBoard().getPlayer2PitStoneCount();
        if (player1PitStoneCount == 0 || player2PitStoneCount == 0){
            game.setGameStatus(GameStatus.ENDED);

            Pit playHouse1 = game.getBoard().getPits().get(Board.PLAYER1_HOUSE);
            playHouse1.setStoneCount(playHouse1.getStoneCount() + player1PitStoneCount);

            Pit playHouse2 = game.getBoard().getPits().get(Board.PLAYER2_HOUSE);
            playHouse2.setStoneCount(playHouse2.getStoneCount() + player2PitStoneCount);

            getResult(game, playHouse1.getStoneCount(), playHouse2.getStoneCount());

            resetGame(game);

        }

    }

    private void getResult(Game game, Integer stoneCountPlay1, Integer stoneCountPlay2) {
        if (stoneCountPlay1 > stoneCountPlay2){
            game.setWinner(game.getPlayer1());
        }else if(stoneCountPlay1 < stoneCountPlay2){
            game.setWinner(game.getPlayer2());
        }else {
            game.setWinner(null);
        }
    }

    private void resetGame(Game game) {
        for (Integer key : game.getBoard().getPits().keySet()) {
            if (key.equals(Board.PLAYER1_HOUSE) || key.equals(Board.PLAYER2_HOUSE)) {
                continue;
            }
            Pit pit = game.getBoard().getPits().get(key);
            pit.setStoneCount(0);
        }
    }
}
