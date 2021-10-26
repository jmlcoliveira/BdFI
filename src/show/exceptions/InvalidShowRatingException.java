package show.exceptions;

public class InvalidShowRatingException extends Exception{

    private static final String MESSAGE = "Invalid rating.";

    public InvalidShowRatingException() {
        super(MESSAGE);
    }
}
