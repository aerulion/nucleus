package net.aerulion.nucleus.exeptions;

public class NotEnoughItemsException extends Exception {

  public NotEnoughItemsException() {
  }

  public NotEnoughItemsException(final String message) {
    super(message);
  }

  public NotEnoughItemsException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public NotEnoughItemsException(final Throwable cause) {
    super(cause);
  }
}