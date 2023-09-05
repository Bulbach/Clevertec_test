package org.example.exceptions;

public class AppBankException extends RuntimeException{
    public AppBankException(String str) {
        super(str);
    }
    public AppBankException(String message, Throwable cause){
        super(message,cause);
    }
}
