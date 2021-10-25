package person.exceptions;

public class InvalidGenderException extends Exception {
    private static final String MESSAGE = "Invalid gender information.";

    public InvalidGenderException() {
        super(MESSAGE);
    }
}
