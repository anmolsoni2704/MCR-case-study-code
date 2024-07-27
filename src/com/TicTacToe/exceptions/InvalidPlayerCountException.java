package com.TicTacToe.exceptions;

public class InvalidPlayerCountException extends Exception{
    public InvalidPlayerCountException(String message){
        super(message);
    }
}
