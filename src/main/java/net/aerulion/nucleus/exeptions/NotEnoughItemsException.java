package net.aerulion.nucleus.exeptions;

public class NotEnoughItemsException extends Exception {

    public NotEnoughItemsException() {
    }

    public NotEnoughItemsException(String errorMessage) {
        super(errorMessage);
    }
}