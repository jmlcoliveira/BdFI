package database.exceptions;

public class NoProductionsWithRatingException extends Exception {
    private static final String MESSAGE = "No productions with rating.";

    public NoProductionsWithRatingException() {
        super(MESSAGE);
    }
}
