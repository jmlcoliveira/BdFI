package show;

import dataStructures.Iterator;
import participation.Participation;
import show.exceptions.InvalidShowRatingException;
import show.exceptions.ShowHasNoParticipationsException;
import show.exceptions.ShowInProductionException;
import show.exceptions.ShowNotInProductionException;

import java.io.Serializable;

/**
 * Show's interface
 *
 * @author Guilherme Pocas (60236) g.pocas@campus.fct.unl.pt
 * @author Joao Oliveira (61052) jml.oliveira@campus.fct.unl.pt
 */
public interface Show extends Serializable {

    /**
     * Returns the id of the show
     *
     * @return ShowID
     */
    String getShowID();

    /**
     * Returns the year the show was made
     *
     * @return year
     */
    int getYear();

    /**
     * Returns de title of the show
     *
     * @return title of the show
     */
    String getTitle();

    /**
     * Checks if the show is still in production
     *
     * @return <code>true</code> if show is in production or <code>false</code> if not
     */
    Boolean isInProduction();

    /**
     * @param part participation of the show
     */
    void addParticipation(Participation part);

    /**
     * Gives the show a rating
     *
     * @param rating between 0 and 10 stars
     * @throws InvalidShowRatingException if the rating is not between 0 and 10
     */
    void rate(int rating) throws InvalidShowRatingException, ShowInProductionException;

    /**
     * Returns the rating of the Show
     *
     * @return the rating
     */
    int getRating();

    /**
     * Returns <code>true</code> if it has any rating, <code>false</code> otherwise
     *
     * @return <code>true</code> if it has any rating, <code>false</code> otherwise
     */
    boolean hasNoRatings();

    /**
     * Premieres a show
     *
     * @throws ShowNotInProductionException if the Show already premiered
     */
    void premiere() throws ShowNotInProductionException;

    /**
     * Adds a tag to a show
     *
     * @param tag tag to be added
     */
    void addTag(String tag);

    /**
     * Returns an iterator containing all show tags
     *
     * @return iterator containing all show tags
     */
    Iterator<String> iteratorTags();

    /**
     * Returns an iterator with the Participations of the Show
     *
     * @return iterator of Participations
     * @throws ShowHasNoParticipationsException  if the Show has no Participations
     */
    Iterator<Participation> iteratorParticipation() throws ShowHasNoParticipationsException;

    /**
     * Checks if the Show has Participations
     *
     * @return <code>true</code> if the Show has no Participations
     */
    boolean hasParticipation();

    /**
     * Checks if the Show has a given tag
     *
     * @param tag the tag
     * @return <code>true</code> if the Show has that tag
     */
    boolean hasTag(String tag);

    /**
     * Checks if the Show has at least one tag
     *
     * @return <code>true</code> if the the Show has any tag
     */
    boolean hasAnyTag();
}
