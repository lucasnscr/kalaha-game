package com.lucasnscr.kalahaGame.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameTest {

    @Test
    public void createGame(){
        Game game = new Game(Board.INITIAL_STONE_ON_PIT);
        assertEquals(Player.PLAYER1_INDEX, game.getPlayer1().getPlayerIndex());
        assertEquals(Player.PLAYER2_INDEX, game.getPlayer2().getPlayerIndex());
        assertEquals(14, game.getBoard().getPits().size());
        assertEquals(GameStatus.INIT, game.getGameStatus());
        assertNotNull(game.getBoard());
    }

}
