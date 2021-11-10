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
    private final List<String> tags;
    private int currentRating;
    private int reviewCount;
    private boolean premiered;

    public ShowClass(String showID, int year, String title) {
        this.showID = showID;
        this.year = year;
        this.title = title;
        currentRating = 0;
        reviewCount = 0;
        participation = new DoubleList<>();
        tags = new DoubleList<>();
        premiered = LocalDate.now().getYear() != year;
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
        return !premiered;
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
        if (!premiered)
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
        premiered = true;
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

   /* @Override
    public boolean hasParticipation() {
        return participation.size() != 0;
    }

    @Override
    public boolean hasTag(String tag) {
        return tags.find(tag) >= 0;
    }

    @Override
    public boolean hasAnyTag() {
        return tags.size() > 0;
    }*/

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Show)) return false;
        return ((Show) o).getShowID().equals(showID);
    }
}
