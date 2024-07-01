package com.epam.reactiveprogramming.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SportAlreadyExistException extends RuntimeException {
    private static final String SPORT_ALREADY_EXIST = "Sport already exists";
    public SportAlreadyExistException() {
        super(SPORT_ALREADY_EXIST);
    }
}
