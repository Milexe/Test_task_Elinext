package ru.milexe.test_task.exception;

public class DataAlreadyExistsException extends Exception{
    public DataAlreadyExistsException(String message){
        super(message);
    }
}
