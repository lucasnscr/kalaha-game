package com.lucasnscr.kalahaGame.rules;

import com.lucasnscr.kalahaGame.model.Game;
import com.lucasnscr.kalahaGame.model.Pit;


/**
 * Abstract class for define contract to the state machinne and next states
 */
public abstract class KalahaRules {

    protected KalahaRules next;
    public abstract void apply(Game game, Pit currentPit);

    public KalahaRules setNext(KalahaRules next){
        this.next = next;
        return next;
    }




}
