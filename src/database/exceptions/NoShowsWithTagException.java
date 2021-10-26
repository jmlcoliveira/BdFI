package database.exceptions;

public class NoShowsWithTagException extends Exception {

    private static final String MESSAGE = "No shows with %s.";

    public NoShowsWithTagException(String tag) {
        super(String.format(MESSAGE, tag));
    }
}
