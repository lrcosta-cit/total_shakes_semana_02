package br.com.TDD.exceptions;

public class DuplicatedIngredienteException extends RuntimeException{

    public DuplicatedIngredienteException(String message, Throwable cause) {
        super(message, cause);
    }
}
