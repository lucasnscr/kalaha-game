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
    public ResponseEntity createGame(@RequestParam(name = "stone", defaultValue = "6", required = false) Integer numberOfStone){
        return ResponseEntity.status(HttpStatus.CREATED).body(kalahaService.createGame(numberOfStone));
    }

    @PutMapping("/games/{gameId}/pits/{pitIndex}")
    public ResponseEntity play(@PathVariable String gameId, @PathVariable Integer pitIndex){
        validate(pitIndex);
        return ResponseEntity.ok().body(kalahaService.play(gameId, pitIndex));
    }

    @GetMapping("/games/{gameId}")
    public ResponseEntity findById(@PathVariable String gameId){
        return ResponseEntity.ok().body(kalahaService.findById(gameId));
    }

    private void validate(Integer pitIndex){
        if(pitIndex > Board.PIT_END_INDEX || pitIndex < Board.PIT_START_INDEX){
            throw new KalahaException("incorrect index");
        }
        if(pitIndex.equals(Board.PLAYER1_HOUSE) || pitIndex.equals(Board.PLAYER2_HOUSE)){
            throw new IllegalMoveException("You dont distribute in the house players");
        }
    }


}
