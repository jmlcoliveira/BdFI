package person.exceptions;

public class PersonIdNotFoundException extends Exception {

    private static final String MESSAGE = "%s does not exist.";

    public PersonIdNotFoundException(String personID) {
        super(String.format(MESSAGE, personID));
    }
}
