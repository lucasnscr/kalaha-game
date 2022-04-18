package com.lucasnscr.kalahaGame.service;

import com.lucasnscr.kalahaGame.model.*;
import com.lucasnscr.kalahaGame.repository.KalahaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class KalahaServiceTest {

    @MockBean
    private KalahaRepository kalahaRepository;

    @MockBean
    private EngineGameService engineService;

    @Autowired
    private KalahaService kalahaService;

    @Test
    public void initGame(){
        Player play1 = new Player(Player.PLAYER1_INDEX, "play1");
        Player play2 = new Player(Player.PLAYER2_INDEX, "play2");

        Board board = new Board();
        board.setPits(initPit());

        Game game = new Game(Board.INITIAL_STONE_ON_PIT);
        game.setId(UUID.randomUUID().toString());
        game.setPlayer1(play1);
        game.setPlayer2(play2);
        game.setBoard(board);

        BDDMockito.given(kalahaRepository.save(BDDMockito.any())).willReturn(game);
        Game gameMock = kalahaService.createGame(6);
        assertEquals(game, gameMock);
    }

    @Test
    public void findByIdGame(){
        Player player1 = new Player(Player.PLAYER1_INDEX, "play1");
        Player player2 = new Player(Player.PLAYER2_INDEX, "play2");

        Board board = new Board();
        board.setPits(initPit());

        String id = UUID.randomUUID().toString();

        Game game = new Game(Board.INITIAL_STONE_ON_PIT);
        game.setId(id);
        game.setPlayer1(player1);
        game.setPlayer2(player2);
        BDDMockito.given(kalahaRepository.findById(BDDMockito.any())).willReturn(game);
        Game gameMock = kalahaService.findById(id);
        assertEquals(game, gameMock);
    }

    @Test
    public void playGame(){

        Player play1 = new Player(Player.PLAYER1_INDEX, "play1");
        Player play2 = new Player(Player.PLAYER2_INDEX, "play2");

        Board board = new Board();
        board.setPits(initPit());

        String id = UUID.randomUUID().toString();

        Game game = new Game(Board.INITIAL_STONE_ON_PIT);
        game.setGameStatus(GameStatus.INIT);
        game.setId(id);
        game.setPlayer1(play1);
        game.setPlayer2(play1);
        game.setBoard(board);

        Optional<Game> gameOp = Optional.of(game);
        game.setGameStatus(GameStatus.PLAYER1_MOVIMENT);
        BDDMockito.given(kalahaRepository.findById(id)).willReturn(game);

        Game fakeGame = kalahaService.play(game.getId(), game.getBoard().getPits().get(1).getPitIndex());
        assertEquals(GameStatus.PLAYER1_MOVIMENT, fakeGame.getGameStatus());
    }

    private Map<Integer, Pit> initPit(){
        Map<Integer, Pit> pits = new HashMap<>();
        for(int i = Board.PIT_START_INDEX; i < Board.PLAYER1_HOUSE; i++){
            Pit pit = new Pit(i, Board.INITIAL_STONE_ON_PIT, Player.PLAYER1_INDEX);
            pits.put(i, pit);
        }
        Pit house1 = new Pit(Board.PLAYER1_HOUSE, Board.INITIAL_STONE_ON_HOUSE, Player.PLAYER1_INDEX);
        pits.put(Board.PLAYER1_HOUSE, house1);

        for(int i= Board.PLAYER1_HOUSE + 1; i < Board.PLAYER2_HOUSE; i++){
            Pit pit = new Pit(i, Board.INITIAL_STONE_ON_PIT, Player.PLAYER2_INDEX);
            pits.put(i, pit);
        }
        Pit house2 = new Pit(Board.PLAYER2_HOUSE, Board.INITIAL_STONE_ON_HOUSE, Player.PLAYER2_INDEX);
        pits.put(Board.PLAYER2_HOUSE, house2);

        return pits;
    }


}
