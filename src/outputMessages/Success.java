package outputMessages;

/**
 * Abstract class containing all output Success messages
 *
 * @author Guilherme Pocas (60236) g.pocas@campus.fct.unl.pt
 * @author Joao Oliveira (61052) jml.oliveira@campus.fct.unl.pt
 */
public abstract class Success {
    //Person
    public static final String PERSON_ADDED = "Person added.";
    public static final String PERSON_INFO = "%s %s %d %s %s %s\n";
    public static final String PERSON_SHOW = "%s %s %d %d\n";

    //Show
    public static final String SHOW_ADDED = "Show added.";
    public static final String SHOW_PREMIERED = "Successful production.";
    public static final String SHOW_REMOVED = "Show removed.";
    public static final String INFO_SHOW_HEADERS = "%s %s %d %d\n";
    public static final String INFO_SHOW_TAG = "%s\n";
    public static final String SHOW_PARTICIPATION = "%s %s %d %s %s %s %s\n";
    public static final String SHOW_LIST = "%s %s %d %d\n";
    public static final String SHOW_RATED = "Rating applied.";

    //Participation
    public static final String PARTICIPATION_ADDED = "Participation added.";

    //Tag
    public static final String TAG_ADDED = "Tag added.";
    public static final String TAG_SHOW = "%s %s %d %d\n";

    //Exit
    public static final String CLOSE_PROGRAM = "Serializing and quitting...";
}
