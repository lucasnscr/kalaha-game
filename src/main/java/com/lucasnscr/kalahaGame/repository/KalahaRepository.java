package com.lucasnscr.kalahaGame.repository;

import com.lucasnscr.kalahaGame.exception.KalahaException;
import com.lucasnscr.kalahaGame.model.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Repository in memory for management data game
 */

@Slf4j
@Component
public class KalahaRepository {

    private static final Map<String, Game> gameMap = new ConcurrentHashMap<>();

    /**
     * Save the game
     * @param initialPitStoneCount
     * @return
     */
    public Game save(Integer initialPitStoneCount){
        String id = UUID.randomUUID().toString();
        Game game = new Game(initialPitStoneCount);
        game.setId(id);
        gameMap.put(id, game);
        return gameMap.get(id);
    }

    /**
     * find game by id
     * @param id
     * @return
     */

    public Game findById(String id){
        Game game = gameMap.get(id);
        if(game == null){
            throw new KalahaException("Game is not found for the id: "+id);
        }
        return game;
    }
}
