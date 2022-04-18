package com.lucasnscr.kalahaGame.service;

import com.lucasnscr.kalahaGame.model.Game;
import com.lucasnscr.kalahaGame.model.Pit;
import com.lucasnscr.kalahaGame.rules.*;
import org.springframework.stereotype.Component;

/**
 * class for start the state machinne to the game
 */
@Component
public class EngineGameService {

    private final KalahaRules rules;

    /**
     * Constructor to the class, enginegame. Start the machinne and apply the the next states: Start, DistributedPitStone, EndPit and Game over.
     */
    public EngineGameService(){
        this.rules = new Start();
        rules.setNext(new DistributedPitStone()).setNext(new EndPit()).setNext(new GameOver());
    }

    /**
     * method for apply game rules
     * @param game
     * @param pit
     */
    public void play(Game game, Pit pit){
        this.rules.apply(game, pit);

    }
}
