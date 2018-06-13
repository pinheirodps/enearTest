package com.hooyu.exercise.controllers;

public class EmailAlreadyExistsInSessionException extends RuntimeException {
    public EmailAlreadyExistsInSessionException(String message) {
        super(message);
    }
}
