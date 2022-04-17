package com.lucasnscr.kalahaGame.service;

import com.lucasnscr.kalahaGame.model.Game;
import com.lucasnscr.kalahaGame.model.Pit;
import com.lucasnscr.kalahaGame.rules.*;
import org.springframework.stereotype.Component;

@Component
public class EngineGameService {

    private final KalahaRules rules;
    public EngineGameService(){
        this.rules = new Start();
        rules.setNext(new DistributedPitStone()).setNext(new EndPit()).setNext(new GameOver());
    }

    public void play(Game game, Pit pit){
        this.rules.apply(game, pit);

    }
}
