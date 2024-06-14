package me.dio.controller.exception;

public class CNPJInvalidoException extends RuntimeException{

    public CNPJInvalidoException(){
        super("CNPJ inválido!");
    }

    public CNPJInvalidoException(String message){super(message);}
}
