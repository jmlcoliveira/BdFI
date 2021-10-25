package person.exceptions;

public class InvalidYearException extends Exception {
    private static final String MESSAGE = "Invalid year.";

    public InvalidYearException() {
        super(MESSAGE);
    }
}
