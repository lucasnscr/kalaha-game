package com.lucasnscr.kalahaGame.model;

import com.lucasnscr.kalahaGame.exception.KalahaException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Board {

    public static final Integer PIT_START_INDEX = 1;
    public static final Integer PIT_END_INDEX = 14;
    public static final Integer PLAYER1_HOUSE = 7;
    public static final Integer PLAYER2_HOUSE = 14;
    public static final Integer INITIAL_STONE_ON_PIT = 6;
    public static final Integer INITIAL_STONE_ON_HOUSE = 0;

    private Map<Integer, Pit> pits;

    /**
     * Constructor to the Board class, initialize for the game
     * @param initialStoneOnPit
     * @param player1
     * @param player2
     */
    public Board(Integer initialStoneOnPit, Player player1, Player player2){
        this.pits = initPit(initialStoneOnPit, player1, player2);
    }

    public Integer getStoneCountWithPitIndex(Integer pitIndex){
        return getPitByPitIndex(pitIndex).getStoneCount();
    }

    /**
     * Method for getting the players house, if the house is incorrect do generate the Kalaha exception
     * @param playerIndex
     * @return pits
     */
    public Pit getPlayerHouse(Integer playerIndex){
        if(playerIndex.equals(Player.PLAYER1_INDEX)) {
            return pits.get(Board.PLAYER1_HOUSE);
        }
        else if(playerIndex.equals(Player.PLAYER2_INDEX)){
            return pits.get(Board.PLAYER2_HOUSE);
        }

        throw new KalahaException("playerIndex is not correct");
    }

    public Pit getPitByPitIndex(Integer pitIndex){
        return pits.get(pitIndex);
    }

    public Pit getNextPit(Pit pit){
        return pits.get(pit.nextPitIndex());
    }

    public Pit getOppositePit(Pit pit){
        return pits.get(pit.getOppositePitIndex());
    }

    /**
     * Method for count pits to the stone for player 1
     * @return player1PitStoneCount
     */

    public Integer getPlayer1PitStoneCount(){
        Integer player1PitStoneCount = 0;
        for(int i = Board.PIT_START_INDEX; i < Board.PLAYER1_HOUSE; i++){
            player1PitStoneCount += this.getPits().get(i).getStoneCount();
        }
        return player1PitStoneCount;
    }

    /**
     * Method for count pits to the stone for player 1
     * @return player1PitStoneCount
     */
    public Integer getPlayer2PitStoneCount(){
        Integer player2PitStoneCount = 0;
        for(int i=Board.PLAYER1_HOUSE + 1; i < Board.PLAYER2_HOUSE; i++){
            player2PitStoneCount += this.getPits().get(i).getStoneCount();
        }
        return player2PitStoneCount;
    }


    /**
     * Method for define Pits house for player 1 and 2. Initialize board for beginning the game
     * @param initialStoneOnPit
     * @param player1
     * @param player2
     * @return pits
     */

    private Map<Integer, Pit> initPit(Integer initialStoneOnPit, Player player1, Player player2){
        Map<Integer, Pit> pits = new HashMap<>();
        for(int i= Board.PIT_START_INDEX; i < Board.PLAYER1_HOUSE; i++){
            Pit pit = new Pit(i, initialStoneOnPit, player1.getPlayerIndex());
            pits.put(i, pit);
        }
        Pit house1 = new Pit(Board.PLAYER1_HOUSE, Board.INITIAL_STONE_ON_HOUSE, player1.getPlayerIndex());
        pits.put(Board.PLAYER1_HOUSE, house1);

        for(int i= Board.PLAYER1_HOUSE + 1; i < Board.PLAYER2_HOUSE; i++){
            Pit pit = new Pit(i, initialStoneOnPit, player2.getPlayerIndex());
            pits.put(i, pit);
        }
        Pit house2 = new Pit(Board.PLAYER2_HOUSE, Board.INITIAL_STONE_ON_HOUSE, player2.getPlayerIndex());
        pits.put(Board.PLAYER2_HOUSE, house2);

        return pits;
    }
}
