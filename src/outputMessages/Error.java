package outputMessages;

public abstract class Error {
    //Person
    public static final String PERSON_NOT_ADDED = "Person added.";
    public static final String INVALID_GENDER = "Invalid gender information.";
    public static final String INVALID_PERSON_YEAR = "Invalid year.";
    public static final String PERSON_HAS_NO_SHOWS = "idPerson has no shows.";
    public static final String PERSON_EXISTS = "idPerson exists.";
    public static final String PERSON_DOESNT_EXIST = "idPerson does not exist.";

    //Participation
    public static final String INVALID_RATING = "Invalid Rating.";
    public static final String INVALID_SHOW_YEAR = "Invalid year.";
    public static final String SHOW_NO_PARTICIPATIONS = "idShow has no participations.";
    public static final String SHOW_EXISTS = "idShow exists.";
    public static final String SHOW_DOESNT_EXIST = "idShow does not exist.";
    public static final String SHOW_IN_PRODUCTION = "idShow is in production.";
    public static final String SHOW_FINISHED_PRODUCTION = "idShow has already completed production.";

    //Database
    public static final String NO_FINISHED_PRODUCTIONS = "No finished productions.";
    public static final String NO_RATED_PRODUCTIONS = "No rated productions.";
    public static final String NO_SHOWS = "No shows.";
    public static final String NO_SHOWS_WITH_TAG  = "No shows with tag.";
    public static final String NO_TAGGED_PRODUCTIONS = "No tagged productions.";
    public static final String NO_PRODUCTION_WITH_RATING = "No productions with rating.";

}
