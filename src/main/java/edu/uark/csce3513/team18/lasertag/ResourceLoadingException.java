package edu.uark.csce3513.team18.lasertag;

public class ResourceLoadingException extends Exception {
    public ResourceLoadingException() {
        super();
    }

    public ResourceLoadingException(String message) {
        super(message);
    }

    public ResourceLoadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceLoadingException(Throwable cause) {
        super(cause);
    }
}
