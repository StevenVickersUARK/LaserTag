package edu.uark.csce3513.team18.lasertag;

public class ImageException extends Exception {
  public ImageException() {
    super();
  }

  public ImageException(String message) {
    super(message);
  }

  public ImageException(String message, Throwable cause) {
    super(message, cause);
  }

  public ImageException(Throwable cause) {
    super(cause);
  }
}
