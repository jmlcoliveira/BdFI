package show.exceptions;

public class ShowIDExistsException extends Exception {

    private static final String MESSAGE = "%s exists.";

    public ShowIDExistsException(String idShow) {
        super(String.format(MESSAGE, idShow));
    }
}
