package com.lucasnscr.kalahaGame.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PitTest {

    @Test
    public void playerPit(){
        Pit pit1 = new Pit(1, 6, 1);
        assertEquals(true, pit1.isPlayerPit(GameStatus.PLAYER1_MOVIMENT));
        assertEquals(false, pit1.isPlayerPit(GameStatus.PLAYER2_MOVIMENT));
    }

    @Test
    public void distributable(){
        Pit pit1 = new Pit(1, 6, 1);
        Pit pit6 = new Pit(7, 6, 1);

        assertEquals(true, pit1.isDistributable(GameStatus.PLAYER1_MOVIMENT));
        assertEquals(false, pit6.isDistributable(GameStatus.PLAYER2_MOVIMENT));
    }

    @Test
    public void house(){
        Pit pit1 = new Pit(1, 6, 1);
        Pit pit6 = new Pit(7, 6, 1);
        Pit pit11 = new Pit(11, 6, 1);
        Pit pit12 = new Pit(14, 6, 1);

        assertEquals(false, pit1.isHouse());
        assertEquals(false, pit11.isHouse());
        assertEquals(true, pit6.isHouse());
        assertEquals(true, pit12.isHouse());
    }

    @Test
    public void nextPitIndex(){
        Pit pit1 = new Pit(1, 6, 1);
        Pit pit6 = new Pit(6, 6, 1);
        Pit pit12 = new Pit(14, 6, 1);

        assertEquals(Integer.valueOf(2), pit1.nextPitIndex());
        assertEquals(Integer.valueOf(7), pit6.nextPitIndex());
        assertEquals(Integer.valueOf(1), pit12.nextPitIndex());
    }

    @Test
    public void player1House(){
        Pit pit1 = new Pit(1, 6, 1);
        Pit pit7 = new Pit(7, 6, 1);
        Pit pit10 = new Pit(10, 6, 2);
        Pit pit11 = new Pit(11, 6, 2);

        assertEquals(true, pit7.isPlayer1House());
        assertEquals(false, pit1.isPlayer1House());
        assertEquals(false, pit10.isPlayer1House());
        assertEquals(false, pit11.isPlayer1House());
    }

    @Test
    public void player2House(){
        Pit pit1 = new Pit(1, 6, 1);
        Pit pit6 = new Pit(6, 6, 1);
        Pit pit10 = new Pit(10, 6, 2);
        Pit pit14 = new Pit(14, 6, 2);

        assertEquals(false, pit1.isPlayer2House());
        assertEquals(false, pit6.isPlayer2House());
        assertEquals(false, pit10.isPlayer2House());
        assertEquals(true, pit14.isPlayer2House());
    }

    @Test
    public void oppositePitIndex(){
        Pit pit1 = new Pit(1, 6, 1);
        Pit pit4 = new Pit(4, 6, 1);

        assertEquals(Integer.valueOf(13), pit1.getOppositePitIndex());
        assertEquals(Integer.valueOf(10), pit4.getOppositePitIndex());
    }
}
