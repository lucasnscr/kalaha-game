package com.lucasnscr.kalahaGame.service;

import com.lucasnscr.kalahaGame.model.Board;
import com.lucasnscr.kalahaGame.model.Game;
import com.lucasnscr.kalahaGame.model.GameStatus;
import com.lucasnscr.kalahaGame.model.Pit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EngineGameTest {

    @Autowired
    private EngineGameService engineGameService;

    @Test
    public void shouldStartWithOwnPit(){
        Game game = new Game(6);
        engineGameService.play(game, game.getBoard().getPitByPitIndex(1));
        assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountWithPitIndex(1));
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(2) );
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(3));
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(4));
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(5));
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(6));
        assertEquals(Integer.valueOf(1), game.getBoard().getStoneCountWithPitIndex(7));
        assertEquals(GameStatus.PLAYER1_MOVIMENT, game.getGameStatus());
        assertEquals(Integer.valueOf(1), game.getBoard().getPits().get(Board.PLAYER1_HOUSE).getStoneCount());
        assertEquals(Integer.valueOf(0), game.getBoard().getPits().get(Board.PLAYER2_HOUSE).getStoneCount());
    }

    @Test
    public void shouldDistributeStoneFromPlayer2PitToPlayer1Pit() {
        Game game = new Game(6);
        engineGameService.play(game, game.getBoard().getPitByPitIndex(12));
        assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountWithPitIndex(12));
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(13));
        assertEquals(Integer.valueOf(1), game.getBoard().getStoneCountWithPitIndex(14));
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(1));
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(2));
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(3));
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(4));
        assertEquals(GameStatus.PLAYER1_MOVIMENT, game.getGameStatus());
        assertEquals(Integer.valueOf(0), game.getBoard().getPits().get(Board.PLAYER1_HOUSE).getStoneCount());
        assertEquals(Integer.valueOf(1), game.getBoard().getPits().get(Board.PLAYER2_HOUSE).getStoneCount());
    }

    @Test
    public void shouldDistributeStoneFromPlayer1PitToPlayer2Pit(){
        Game game = new Game(6);
        engineGameService.play(game, game.getBoard().getPitByPitIndex(4));
        assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountWithPitIndex(4));
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(5));
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(6));
        assertEquals(Integer.valueOf(1), game.getBoard().getStoneCountWithPitIndex(7));
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(8));
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(9));
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(10));
        assertEquals(GameStatus.PLAYER2_MOVIMENT, game.getGameStatus());
        assertEquals(Integer.valueOf(1), game.getBoard().getPits().get(Board.PLAYER1_HOUSE).getStoneCount());
        assertEquals(Integer.valueOf(0), game.getBoard().getPits().get(Board.PLAYER2_HOUSE).getStoneCount());
    }

    @Test
    public void shouldDistribute13Stone(){
        Game game = new Game(6);
        game.getBoard().getPits().get(4).setStoneCount(13);
        game.getBoard().getPits().get(10).setStoneCount(10);
        engineGameService.play(game, game.getBoard().getPitByPitIndex(4));
        assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountWithPitIndex(4));
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(5));
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(6));
        assertEquals(Integer.valueOf(13), game.getBoard().getStoneCountWithPitIndex(7));
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(8));
        assertEquals(Integer.valueOf(7), game.getBoard().getStoneCountWithPitIndex(9));
        assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountWithPitIndex(10));
        assertEquals(GameStatus.PLAYER2_MOVIMENT, game.getGameStatus());
        assertEquals(Integer.valueOf(13), game.getBoard().getPits().get(Board.PLAYER1_HOUSE).getStoneCount());
        assertEquals(Integer.valueOf(0), game.getBoard().getPits().get(Board.PLAYER2_HOUSE).getStoneCount());
    }

    @Test
    public void shouldIncreaseHouseStoneOnOwnEmptyPit() {
        Game game = new Game(6);
        Pit pit1 = game.getBoard().getPitByPitIndex(1);
        pit1.setStoneCount(2);
        Pit pit2 = game.getBoard().getPitByPitIndex(3);
        pit2.setStoneCount(0);
        engineGameService.play(game, game.getBoard().getPitByPitIndex(1));
        assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountWithPitIndex(1));
        assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountWithPitIndex(3) );
        assertEquals(Integer.valueOf(0), game.getBoard().getStoneCountWithPitIndex(11));
        assertEquals(GameStatus.PLAYER2_MOVIMENT, game.getGameStatus());
        assertEquals(Integer.valueOf(7), game.getBoard().getPits().get(Board.PLAYER1_HOUSE).getStoneCount());
        assertEquals(Integer.valueOf(0), game.getBoard().getPits().get(Board.PLAYER2_HOUSE).getStoneCount());
    }


    @Test
    public void shouldChangeGameToPlayerTurn1() {
        Game game = new Game(6);
        engineGameService.play(game, game.getBoard().getPitByPitIndex(1));
        assertEquals(GameStatus.PLAYER1_MOVIMENT, game.getGameStatus());
    }


    @Test
    public void shouldChangeGameToPlayerTurn2() {
        Game game = new Game(6);
        engineGameService.play(game, game.getBoard().getPitByPitIndex(2));
        assertEquals(GameStatus.PLAYER2_MOVIMENT, game.getGameStatus());
    }


    @Test
    public void shouldChangeGameToPlayerTurn2Again() {
        Game game = new Game(6);
        Pit pit = game.getBoard().getPits().get(8);
        pit.setStoneCount(6);
        engineGameService.play(game, game.getBoard().getPitByPitIndex(8));
        assertEquals(GameStatus.PLAYER2_MOVIMENT, game.getGameStatus());
    }


    @Test
    public void shouldGameOver() {
        Game game = new Game(6);
        for(Integer key : game.getBoard().getPits().keySet()){
            Pit pit = game.getBoard().getPits().get(key);
            if(!pit.isHouse()) {
                pit.setStoneCount(0);
            }
        }
        game.getBoard().getPits().get(6).setStoneCount(1);
        engineGameService.play(game, game.getBoard().getPitByPitIndex(6));
        assertEquals(GameStatus.ENDED, game.getGameStatus());
        assertEquals(game.getWinner(), game.getPlayer1());
    }

    @Test
    public void shouldPlayer1Win() {
        Game game = new Game(6);
        for(Integer key : game.getBoard().getPits().keySet()){
            Pit pit = game.getBoard().getPits().get(key);
            if(!pit.isHouse()) {
                pit.setStoneCount(0);
            }
        }
        Pit lastPit = game.getBoard().getPits().get(6);
        lastPit.setStoneCount(1);
        engineGameService.play(game, game.getBoard().getPitByPitIndex(6));
        assertEquals(GameStatus.ENDED, game.getGameStatus());
        assertEquals(game.getWinner(), game.getPlayer1());
    }

    @Test
    public void shouldPlayer2Win(){
        Game game = new Game(6);
        for(Integer key : game.getBoard().getPits().keySet()){
            Pit pit = game.getBoard().getPits().get(key);
            if(!pit.isHouse()) {
                pit.setStoneCount(0);
            }
        }
        game.getBoard().getPits().get(13).setStoneCount(1);
        engineGameService.play(game, game.getBoard().getPitByPitIndex(13));
        assertEquals(GameStatus.ENDED, game.getGameStatus());
        assertEquals(game.getWinner(), game.getPlayer2());
    }

    @Test
    public void shouldDraw(){
        Game game = new Game(6);
        for(Integer key : game.getBoard().getPits().keySet()){
            Pit pit = game.getBoard().getPits().get(key);
            if(!pit.isHouse()) {
                pit.setStoneCount(0);
            }
        }
        game.getBoard().getPits().get(6).setStoneCount(1);
        game.getBoard().getPits().get(14).setStoneCount(1);
        engineGameService.play(game, game.getBoard().getPitByPitIndex(6));
        assertEquals(GameStatus.ENDED, game.getGameStatus());
        assertEquals(game.getWinner(), null);
    }
}
