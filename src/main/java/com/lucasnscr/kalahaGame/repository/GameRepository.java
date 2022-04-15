package com.lucasnscr.kalahaGame.repository;

import com.lucasnscr.kalahaGame.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GameRepository extends CrudRepository<Game, String> {
    Optional<Game> findById(String id);
}
