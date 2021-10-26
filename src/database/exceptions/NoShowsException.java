package database.exceptions;

public class NoShowsException extends Exception{

    private static final String MESSAGE = "No shows.";

    public NoShowsException() {
        super(MESSAGE);
    }
}
