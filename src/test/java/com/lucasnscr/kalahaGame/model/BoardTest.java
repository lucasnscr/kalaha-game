package com.lucasnscr.kalahaGame.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BoardTest {

    static Board init(){
        Player play1 = new Player(Player.PLAYER1_INDEX, "play1");
        Player play2 = new Player(Player.PLAYER2_INDEX, "play2");
        return new Board(Board.INITIAL_STONE_ON_PIT, play1, play2);
    }

    @Test
    public void shouldCreateBoard(){
        Board board = init();
        assertNotNull(board.getPits());
        assertEquals(14, board.getPits().size());
    }

    @Test
    public void shouldGetStoneCountByPitIndex(){
        Board board = init();

        Integer pit1Stone = board.getStoneCountWithPitIndex(1);
        Integer house1Stone = board.getStoneCountWithPitIndex(7);
        Integer pit8Stone = board.getStoneCountWithPitIndex(8);
        Integer house2Stone = board.getStoneCountWithPitIndex(14);

        assertEquals(Integer.valueOf(6), pit1Stone);
        assertEquals(Integer.valueOf(0), house1Stone);
        assertEquals(Integer.valueOf(6), pit8Stone);
        assertEquals(Integer.valueOf(0), house2Stone);
    }

    @Test
    public void shouldGetPlayerHouse(){
        Board board = init();

        Pit house1 = board.getPlayerHouse(Player.PLAYER1_INDEX);
        Pit house2 = board.getPlayerHouse(Player.PLAYER2_INDEX);

        assertEquals(Integer.valueOf(7), house1.getPitIndex());
        assertEquals(Integer.valueOf(14), house2.getPitIndex());
    }

    @Test
    public void shouldGetPitByPitIndex(){
        Board board = init();
        Pit pit = board.getPitByPitIndex(2);
        assertEquals(Integer.valueOf(2), pit.getPitIndex());
        assertEquals(Integer.valueOf(1), pit.getPlayerIndex());
    }

    @Test
    public void shouldGetNextPit() {
        Board board = init();

        Pit pit1 = board.getPitByPitIndex(1);
        Pit pit2 = board.getNextPit(pit1);
        Pit pit14 = board.getPitByPitIndex(14);
        Pit pit1Again = board.getNextPit(pit14);

        assertEquals(Integer.valueOf(2), pit2.getPitIndex());
        assertEquals(pit1, pit1Again);
    }

    @Test
    public void shouldGetOppositePit() {
        Board board = init();

        Pit pit1 = board.getPitByPitIndex(1);
        Pit oppositePit = board.getOppositePit(pit1);
        Pit pit1Again = board.getOppositePit(oppositePit);

        assertEquals(Integer.valueOf(13), oppositePit.getPitIndex());
        assertEquals(pit1, pit1Again);
    }

    @Test
    public void shouldGetPlayer1PitStoneCount(){
        Board board = init();
        Integer player1PitStoneCount = board.getPlayer1PitStoneCount();
        assertEquals(Integer.valueOf(36), player1PitStoneCount);
    }

    @Test
    public void shouldGetPlayer2PitStoneCount(){
        Board board = init();
        board.getPits().get(8).setStoneCount(0);
        Integer player2PitStoneCount = board.getPlayer2PitStoneCount();
        assertEquals(Integer.valueOf(30), player2PitStoneCount);
    }
}
