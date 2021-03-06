package com.lucasnscr.kalahaGame.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pit {

    @NotNull
    private Integer pitIndex;

    @NotNull
    private Integer stoneCount;

    @NotNull
    private Integer playerIndex;

    /**
     * Method for check status game and pit index to the board
     * @param gameStatus
     * @return boolean
     */
    public Boolean isDistributable(GameStatus gameStatus){
        return (!gameStatus.equals(GameStatus.PLAYER1_MOVIMENT) || !this.pitIndex.equals(Board.PLAYER2_HOUSE))
                && (!gameStatus.equals(GameStatus.PLAYER2_MOVIMENT) || !this.pitIndex.equals(Board.PLAYER1_HOUSE));
    }

    /**
     * define permission to the turn pits in the game
     * @param gameStatus
     * @return
     */
    public Boolean isPlayerPit(GameStatus gameStatus){
        if(gameStatus.equals(GameStatus.PLAYER1_MOVIMENT) && this.playerIndex.equals(Player.PLAYER1_INDEX)) {
            return true;
        }else if(gameStatus.equals(GameStatus.PLAYER2_MOVIMENT) && this.playerIndex.equals(Player.PLAYER2_INDEX)) {
            return true;
        }
        return false;
    }

    public Boolean isHouse(){
        return this.pitIndex.equals(Board.PLAYER1_HOUSE) || this.pitIndex.equals(Board.PLAYER2_HOUSE);
    }

    public Integer nextPitIndex(){
        Integer index = this.pitIndex + 1;
        if(index > Board.PLAYER2_HOUSE) {
            index = 1;
        }
        return index;
    }

    public Boolean isPlayer1House(){
        return this.playerIndex.equals(Player.PLAYER1_INDEX) && this.pitIndex.equals(Board.PLAYER1_HOUSE);
    }

    public Boolean isPlayer2House(){
        return this.playerIndex.equals(Player.PLAYER2_INDEX) && this.pitIndex.equals(Board.PLAYER2_HOUSE);
    }

    public Integer getOppositePitIndex(){
        return  (Board.PIT_START_INDEX + Board.PIT_END_INDEX - 1) - this.getPitIndex();
    }
}
