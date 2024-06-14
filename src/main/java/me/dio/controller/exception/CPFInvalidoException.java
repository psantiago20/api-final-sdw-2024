package me.dio.controller.exception;

public class CPFInvalidoException extends RuntimeException{

    public CPFInvalidoException(){
        super("CPF inválido!");
    }

    public CPFInvalidoException(String message){super(message);}
}
