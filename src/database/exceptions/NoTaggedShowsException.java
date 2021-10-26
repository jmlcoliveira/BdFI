package database.exceptions;

public class NoTaggedShowsException extends Exception {
    private static final String MESSAGE = "No tagged productions.";

    public NoTaggedShowsException() {
        super(MESSAGE);
    }
}
