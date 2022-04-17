package com.lucasnscr.kalahaGame.controller;

import com.lucasnscr.kalahaGame.exception.IllegalMoveException;
import com.lucasnscr.kalahaGame.exception.KalahaException;
import com.lucasnscr.kalahaGame.model.Board;
import com.lucasnscr.kalahaGame.service.KalahaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kalaha")
public class KalahaController {
    private final KalahaService kalahaService;

    public KalahaController(KalahaService kalahaService){
        this.kalahaService = kalahaService;
    }

    @PostMapping(value = "/games")
    public ResponseEntity initBoard(@RequestParam(name = "stone", defaultValue = "6", required = false) Integer numberOfStone){
        return ResponseEntity.status(HttpStatus.CREATED).body(kalahaService.initGame(numberOfStone));
    }


    @PutMapping("/games/{gameId}/pits/{pitIndex}")
    public ResponseEntity play(@PathVariable String gameId, @PathVariable Integer pitIndex){
        if(pitIndex > Board.PIT_END_INDEX || pitIndex < Board.PIT_START_INDEX){
            throw new KalahaException("Incorrect pit index");
        }

        if(pitIndex.equals(Board.PLAYER1_HOUSE) || pitIndex.equals(Board.PLAYER2_HOUSE)){
            throw new IllegalMoveException("House stone is not allow to distribute");
        }

        return ResponseEntity.ok().body(kalahaService.play(gameId, pitIndex));
    }


}
