package com.lucasnscr.kalahaGame.exception;

public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException(String id){super("Game not found: "+ id);}
}
