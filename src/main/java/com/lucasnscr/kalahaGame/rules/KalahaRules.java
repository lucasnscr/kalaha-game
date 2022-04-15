package com.lucasnscr.kalahaGame.rules;

import com.lucasnscr.kalahaGame.model.Game;
import com.lucasnscr.kalahaGame.model.Pit;

public abstract class KalahaRules {

    protected KalahaRules next;
    public abstract void apply(Game game, Pit currentPit);

    public KalahaRules setNext(KalahaRules next){
        this.next = next;
        return next;
    }




}
