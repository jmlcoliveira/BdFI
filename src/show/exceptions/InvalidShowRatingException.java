package show.exceptions;

public class InvalidShowRatingException extends Exception{

    private static final String MESSAGE = "Invalid Rating.";

    public InvalidShowRatingException() {
        super(MESSAGE);
    }
}
