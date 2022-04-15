package com.lucasnscr.kalahaGame.service;

import com.lucasnscr.kalahaGame.model.Game;
import com.lucasnscr.kalahaGame.model.Pit;
import com.lucasnscr.kalahaGame.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class KalahaService {

    private final GameRepository gameRepository;
    private final EngineService engineService;

    @Autowired
    public KalahaService(GameRepository gameRepository, EngineService engineService){
        this.engineService = engineService;
        this.gameRepository = gameRepository;
    }

    public Game initGame(Integer initalPitStoneCount){
        Game game = new Game(initalPitStoneCount);
        return gameRepository.save(game);
    }

    public Game play(String gameId, Integer pitIndex){
        Game game = this.findById(gameId);
        Pit pit = game.getBoard().getPitByPitIndex(pitIndex);
        engineService.play(game, pit);
        return game;
    }

    public Game findById(String id){
        Optional<Game> gameOptional = gameRepository.findById(id);
        if (gameOptional.isPresent()){
            return gameOptional.get();
        }
        return null;
    }



}
