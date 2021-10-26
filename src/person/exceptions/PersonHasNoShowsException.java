package person.exceptions;

public class PersonHasNoShowsException extends Exception {

    private static final String MESSAGE = "%s has no shows.";

    public PersonHasNoShowsException(String personID) {
        super(String.format(MESSAGE, personID));
    }
}
