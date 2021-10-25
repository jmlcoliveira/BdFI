package person.exceptions;

public class PersonIdAlreadyExistsException extends Exception {
    private static final String MESSAGE = "%s exists.";

    public PersonIdAlreadyExistsException(String id) {
        super(String.format(MESSAGE, id));
    }
}
