package show.exceptions;

public class InvalidShowYearException extends Exception {

    private static final String MESSAGE = "Invalid year.";

    public InvalidShowYearException() {
        super(MESSAGE);
    }
}
