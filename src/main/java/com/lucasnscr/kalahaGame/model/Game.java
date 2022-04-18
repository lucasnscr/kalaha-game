package com.lucasnscr.kalahaGame.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Game {

    private String id;
    private Board board;
    private Player player1;
    private Player player2;
    private Player winner;
    private GameStatus gameStatus;

    /**
     * Constructor to the game class, is required for initialize the game: Two players, the board and define status game with INIT
     * @param initialStoneOnPit
     */
    public Game(Integer initialStoneOnPit){
        this.player1 = new Player(Player.PLAYER1_INDEX, "player1");
        this.player2 = new Player(Player.PLAYER2_INDEX, "player2");
        this.board = new Board(initialStoneOnPit, player1, player2);
        this.gameStatus = GameStatus.INIT;
    }
}
