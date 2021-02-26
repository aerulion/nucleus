package net.aerulion.nucleus.exeptions;

public class NotEnoughItemsException extends Exception {

    public NotEnoughItemsException() {
    }

    public NotEnoughItemsException(String message) {
        super(message);
    }

    public NotEnoughItemsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughItemsException(Throwable cause) {
        super(cause);
    }
}