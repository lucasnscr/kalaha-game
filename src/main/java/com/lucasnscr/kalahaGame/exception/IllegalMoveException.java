package com.lucasnscr.kalahaGame.exception;

public class IllegalMoveException extends RuntimeException{
    public IllegalMoveException(String message){super("Illegal move:" + message);}
}
