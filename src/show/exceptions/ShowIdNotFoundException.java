package show.exceptions;

public class ShowIdNotFoundException extends Exception {

    private static final String MESSAGE = "%s does not exist.";

    public ShowIdNotFoundException(String idShow) {
        super(String.format(MESSAGE, idShow));
    }
}
