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
 * Show's class containing all information regarding a show
 *
 * @author Guilherme Pocas (60236) g.pocas@campus.fct.unl.pt
 * @author Joao Oliveira (61052) jml.oliveira@campus.fct.unl.pt
 */
public class ShowClass implements Show {

    static final long serialVersionUID = 0L;

    private final String showID;
    private final int year;
    private final String title;
    private final List<Participation> participation;
    private final List<String> tags;
    private int currentRating;
    private boolean isRated;
    private int reviewCount;
    private boolean premiered;

    public ShowClass(String showID, int year, String title) {
        this.showID = showID;
        this.year = year;
        this.title = title;
        currentRating = 0;
        isRated = false;
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

    public void addParticipation(Participation part) {
        participation.addLast(part);
    }

    public void rate(int stars) throws InvalidShowRatingException, ShowInProductionException {
        if (stars < 0 || stars > 10)
            throw new InvalidShowRatingException();
        if (!premiered)
            throw new ShowInProductionException(showID);
        currentRating = bdfiAlg.updateReview(stars, reviewCount, currentRating);
        reviewCount++;
        isRated = true;
    }

    public int getRating() {
        return currentRating;
    }

    @Override
    public boolean isRated() {
        return isRated;
    }

    @Override
    public void premiere() throws ShowNotInProductionException {
        if (!isInProduction())
            throw new ShowNotInProductionException(showID);
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
        if (participation.isEmpty()) throw new ShowHasNoParticipationsException(showID);
        return participation.iterator();
    }

    @Override
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
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Show)) return false;
        return ((Show) o).getShowID().equals(showID);
    }
}
