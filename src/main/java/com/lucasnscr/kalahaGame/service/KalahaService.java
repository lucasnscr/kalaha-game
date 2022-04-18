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

/**
 * Class to connect Api service with engine an rules to the game
 */
@Slf4j
@Service
public class KalahaService {

    private final KalahaRepository gameRepository;
    private final EngineGameService engineService;

    /**
     * Constructor of KalahaService, connect repository for management of data and engineering game
     * @param gameRepository
     * @param engineService
     */
    @Autowired
    public KalahaService(KalahaRepository gameRepository, EngineGameService engineService){
        this.engineService = engineService;
        this.gameRepository = gameRepository;
    }

    /**
     * method for create game, with board, player, game status and save game in memory
     * @param initalPitStoneCount
     * @return
     */
    public Game createGame(Integer initalPitStoneCount){
        Game game = new Game(initalPitStoneCount);
        return gameRepository.save(initalPitStoneCount);
    }

    /**
     * method for start a game, you need inform gameId for consulting if game existis in memory and inform the pitIndex initial to a game
     * @param gameId
     * @param pitIndex
     * @return
     */
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

    /**
     * Search game saved in memory with your id
     * @param id
     * @return
     */
    public Game findById(String id){
        Game game = null;
        game = gameRepository.findById(id);
        if (game != null){
            return game;
        }
        return null;
    }



}
