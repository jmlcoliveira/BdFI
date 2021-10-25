package show.exceptions;

public class ShowInProductionException extends Exception {

    private static final String MESSAGE = "%s is in production.";

    public ShowInProductionException(String idShow) {
        super(String.format(MESSAGE, idShow));
    }
}
