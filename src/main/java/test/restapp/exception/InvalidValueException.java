package test.restapp.exception;

public class InvalidValueException extends RuntimeException {

    public InvalidValueException(String msg, String value) {
        super(String.format("%S: %s", msg, value));
    }
}
