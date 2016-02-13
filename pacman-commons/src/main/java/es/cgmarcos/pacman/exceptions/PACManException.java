package es.cgmarcos.pacman.exceptions;


public class PACManException extends Exception {

    public PACManException() {
    }

    public PACManException(String message) {
        super(message);
    }

    public PACManException(String message, Throwable cause) {
        super(message, cause);
    }

    public PACManException(Throwable cause) {
        super(cause);
    }

    public PACManException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
