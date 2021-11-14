package show;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;
import participation.Participation;
import show.exceptions.InvalidShowRatingException;
import show.exceptions.ShowHasNoParticipationsException;
import show.exceptions.ShowInProductionException;
import show.exceptions.ShowNotInProductionException;

import java.time.LocalDate;

/**
 * Participation's class containing all information regarding a show
 *
 * @author Guilherme Pocas (60236) g.pocas@campus.fct.unl.pt
 * @author Joao Oliveira (61052) jml.oliveira@campus.fct.unl.pt
 */
public class ShowClass implements Show {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * ID of the show
     */
    private final String showID;

    /**
     * Release year of the show
     */
    private final int year;

    /**
     * Title of the show
     */
    private final String title;

    /**
     * List containing all participation of the show
     */
    private final List<Participation> participation;

    /**
     * List containing all tags of this show
     */
    private final List<String> tags;

    /**
     * Variable containing the current rating of the show
     */
    private int currentRating;

    /**
     * Number of reviews
     */
    private int reviewCount;

    /**
     * Boolean variable containing production status
     */
    private boolean inProduction;

    /**
     * Constructor method
     *
     * @param showID id of the show
     * @param year   production year of the show
     * @param title  title of the show
     */
    public ShowClass(String showID, int year, String title) {
        this.showID = showID;
        this.year = year;
        this.title = title;
        currentRating = 0;
        reviewCount = 0;
        participation = new DoubleList<>();
        tags = new DoubleList<>();
        inProduction = LocalDate.now().getYear() == year;
    }

    @Override
    public String getShowID() {
        return showID;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Boolean isInProduction() {
        return inProduction;
    }

    //requires stars, count, currentReview >= 0
    private static int updateReview(int stars, int count, int currentReview) {
        return Math.round((stars + count * currentReview) / ((float) (count + 1)));
    }

    @Override
    public void addParticipation(Participation part) {
        participation.addLast(part);
    }

    @Override
    public void rate(int stars) throws InvalidShowRatingException, ShowInProductionException {
        if (stars < 0 || stars > 10)
            throw new InvalidShowRatingException();
        if (inProduction)
            throw new ShowInProductionException();
        currentRating = updateReview(stars, reviewCount, currentRating);
        reviewCount++;
    }

    @Override
    public int getRating() {
        return currentRating;
    }

    @Override
    public boolean hasNoRatings() {
        return currentRating == 0;
    }

    @Override
    public void premiere() throws ShowNotInProductionException {
        if (!isInProduction())
            throw new ShowNotInProductionException();
        inProduction = false;
    }

    @Override
    public void addTag(String tag) {
        tags.addLast(tag);
    }

    @Override
    public Iterator<String> iteratorTags() {
        return tags.iterator();
    }

    @Override
    public Iterator<Participation> iteratorParticipation() throws ShowHasNoParticipationsException {
        if (participation.isEmpty()) throw new ShowHasNoParticipationsException();
        return participation.iterator();
    }
}
