package database.exceptions;

public class NoRatedShowsException extends Exception{

    private static final String MESSAGE = "No rated productions.";

    public NoRatedShowsException() {
        super(MESSAGE);
    }
}
