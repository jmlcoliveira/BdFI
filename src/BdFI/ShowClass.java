package BdFI;

import BdFI.show.exceptions.InvalidShowRatingException;
import BdFI.show.exceptions.ShowHasNoParticipationsException;
import BdFI.show.exceptions.ShowInProductionException;
import BdFI.show.exceptions.ShowNotInProductionException;
import dataStructures.Dictionary;
import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;
import dataStructures.hashTable.SepChainHashTable;

import java.time.LocalDate;

/**
 * Participation's class containing all information regarding a show
 *
 * @author Guilherme Pocas (60236) g.pocas@campus.fct.unl.pt
 * @author Joao Oliveira (61052) jml.oliveira@campus.fct.unl.pt
 */
class ShowClass implements ShowPrivate {

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
     * Dictionary containing every Person that participated on the Show
     * the Key is the PersonID
     * the Value is the Person
     */
    private final Dictionary<String, Person> personsInShow;

    /**
     * List containing all tags of this show
     */
    private final List<String> tags;

    /**
     * Variable containing the current rating of the show
     */
    private int currentRating;

    /**
     * If the Show is rated
     */
    private boolean isRated;

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
        isRated = false;
        participation = new DoubleList<>();
        tags = new DoubleList<>();
        inProduction = LocalDate.now().getYear() == year;

        personsInShow = new SepChainHashTable<>();
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
    public boolean isInProduction() {
        return inProduction;
    }

    //requires stars, count, currentReview >= 0
    //O(1)
    private static int updateReview(int stars, int count, int currentReview) {
        return Math.round((stars + count * currentReview) / ((float) (count + 1)));
    }

    @Override
    //O(1)
    public void addParticipation(Participation part) {
        participation.addLast(part);
        Person p = part.getPerson();
        personsInShow.insert(p.getPersonID(), p);
    }

    @Override
    //O(1)
    public void rate(int stars) throws InvalidShowRatingException, ShowInProductionException {
        if (stars < 0 || stars > 10)
            throw new InvalidShowRatingException();
        if (inProduction)
            throw new ShowInProductionException();
        currentRating = updateReview(stars, reviewCount, currentRating);
        isRated = true;
        reviewCount++;
    }

    @Override
    public int getRating() {
        return currentRating;
    }

    @Override
    public boolean hasRatings() {
        return isRated;
    }

    @Override
    //O(1)
    public void premiere() throws ShowNotInProductionException {
        if (!isInProduction())
            throw new ShowNotInProductionException();
        inProduction = false;
    }

    @Override
    //O(1)
    public void addTag(String tag) {
        tags.addLast(tag);
    }

    @Override
    //O(1)
    public Iterator<String> iteratorTags() {
        return tags.iterator();
    }

    @Override
    //O(1)
    public Iterator<Participation> iteratorParticipation() throws ShowHasNoParticipationsException {
        if (participation.isEmpty()) throw new ShowHasNoParticipationsException();
        return participation.iterator();
    }

    @Override
    //O(1)
    public Iterator<Person> iteratorPersonsInShow() {
        return personsInShow.iteratorValues();
    }

    @Override
    //O(1)
    public int compareTo(Show o) {
        return showID.compareTo(o.getShowID());
    }
}
