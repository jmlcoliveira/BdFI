package show.exceptions;

public class ShowIDExistsException extends Exception {

    private static final String MESSAGE = "%s exists.";

    public ShowIDExistsException(String showID) {
        super(String.format(MESSAGE, showID));
    }
}
