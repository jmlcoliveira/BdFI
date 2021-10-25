package show.exceptions;

public class InvalidShowIDException extends Exception {

    private static final String MESSAGE = "%s does not exist.";

    public InvalidShowIDException(String idShow) {
        super(String.format(MESSAGE, idShow));
    }
}
