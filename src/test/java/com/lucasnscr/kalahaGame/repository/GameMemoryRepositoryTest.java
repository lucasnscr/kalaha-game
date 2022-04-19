package com.lucasnscr.kalahaGame.repository;


import com.lucasnscr.kalahaGame.model.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GameMemoryRepositoryTest {

    @Autowired
    private KalahaRepository  gameMemoryRepository;

    @Test
    public void shouldCreateGame(){
        Game game1 = gameMemoryRepository.save(6);
        Game game = gameMemoryRepository.findById(game1.getId());
        assertNotNull(game);
        assertEquals(game1, game1);
    }
}
