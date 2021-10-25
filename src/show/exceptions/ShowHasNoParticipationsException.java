package show.exceptions;

public class ShowHasNoParticipationsException extends Exception {

    private static final String MESSAGE = "%s has no participations.";

    public ShowHasNoParticipationsException(String idShow) {
        super(String.format(MESSAGE, idShow));
    }
}
