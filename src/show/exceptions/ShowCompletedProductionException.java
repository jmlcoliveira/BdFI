package show.exceptions;

public class ShowCompletedProductionException extends Exception {

    private static final String MESSAGE = "%s has already completed production.";

    public ShowCompletedProductionException(String idShow) {
        super(String.format(MESSAGE, idShow));
    }
}
