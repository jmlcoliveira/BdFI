package show.exceptions;

public class ShowNotInProductionException extends Exception{

    private static final String MESSAGE = "%s has already completed production.";

    public ShowNotInProductionException(String idShow) {
        super(String.format(MESSAGE, idShow));
    }
}
