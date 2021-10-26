package show;

import dataStructures.Iterator;
import participation.Participation;
import show.exceptions.InvalidShowRatingException;
import show.exceptions.ShowHasNoParticipationsException;
import show.exceptions.ShowInProductionException;
import show.exceptions.ShowNotInProductionException;

import java.io.Serializable;

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
     * @return
     */
    String getTitle();

    /**
     * Checks if the show is still in production
     *
     * @return
     */
    Boolean isInProduction();

    void addParticipation(Participation part);

    /**
     * Gives the show a rating
     *
     * @param rating between 0 and 10 stars
     * @throws InvalidShowRatingException if the rating is not between 0 and 10
     */
    void rate(int rating) throws InvalidShowRatingException, ShowInProductionException;

    int getRating();

    boolean isRated();

    /**
     * @throws ShowNotInProductionException
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

    Iterator<Participation> iteratorParticipation() throws ShowHasNoParticipationsException;

    boolean hasParticipation();

    boolean hasTag(String tag);

    boolean hasAnyTag();
}
