package com.maksimov.exceptions;

/**
 * Created on 10.04.2016.
 */
public class NoUniqueException extends Exception {

    public NoUniqueException() {
    }

    public NoUniqueException(String message) {
        super(message);
    }

    public NoUniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoUniqueException(Throwable cause) {
        super(cause);
    }

    public NoUniqueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
