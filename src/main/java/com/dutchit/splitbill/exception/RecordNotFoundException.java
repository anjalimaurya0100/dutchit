package com.dutchit.splitbill.exception;


public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String message){
        super(message);
    }

}
