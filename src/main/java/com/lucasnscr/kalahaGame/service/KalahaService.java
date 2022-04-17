package com.lucasnscr.kalahaGame.service;

import com.lucasnscr.kalahaGame.exception.KalahaException;
import com.lucasnscr.kalahaGame.model.Board;
import com.lucasnscr.kalahaGame.model.Game;
import com.lucasnscr.kalahaGame.model.Pit;
import com.lucasnscr.kalahaGame.model.Player;
import com.lucasnscr.kalahaGame.repository.KalahaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class KalahaService {

    private final KalahaRepository gameRepository;
    private final EngineGameService engineService;

    @Autowired
    public KalahaService(KalahaRepository gameRepository, EngineGameService engineService){
        this.engineService = engineService;
        this.gameRepository = gameRepository;
    }

    public Game initGame(Integer initalPitStoneCount){
        Game game = new Game(initalPitStoneCount);
        return gameRepository.save(game);
    }

    public Game play(String gameId, Integer pitIndex){
        Game game= null;
        game = this.findById(gameId);
        if (game == null){
                throw new KalahaException("Game is not found for the id: "+ gameId);
        }
        Pit pit = game.getBoard().getPitByPitIndex(pitIndex);
        Player play1 = new Player(Player.PLAYER1_INDEX, "play1");
        Player play2 = new Player(Player.PLAYER2_INDEX, "play2");
        Board boardOrdered = new Board(Board.INITIAL_STONE_ON_PIT, play1, play2);
        game.setBoard(boardOrdered);

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
