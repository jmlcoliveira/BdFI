package database.exceptions;

public class NoFinishedShowsException extends Exception{

    private static final String MESSAGE = "No finished productions.";

    public NoFinishedShowsException() {
        super(MESSAGE);
    }
}
