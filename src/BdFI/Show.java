package BdFI;

import BdFI.show.exceptions.ShowHasNoParticipationsException;
import dataStructures.Iterator;

public interface Show extends Comparable<Show> {
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
     * Returns the rating of the Participation
     *
     * @return the rating
     */
    int getRating();

    /**
     * Returns an iterator containing all show tags
     *
     * @return iterator containing all show tags
     */
    Iterator<String> iteratorTags();

    /**
     * Returns an iterator with the participation of the show
     *
     * @return iterator of participation
     * @throws ShowHasNoParticipationsException if the show has no participation
     */
    Iterator<Participation> iteratorParticipation() throws ShowHasNoParticipationsException;

    /**
     * Returns an iterator containing all persons in the show
     *
     * @return an iterator containing all persons in the show
     */
    Iterator<Person> iteratorPersonsInShow();

    /**
     * Returns <code>true</code> if it has any rating, <code>false</code> otherwise
     *
     * @return <code>true</code> if it has any rating, <code>false</code> otherwise
     */
    boolean hasNoRatings();

    /**
     * Checks if the show is still in production
     *
     * @return <code>true</code> if show is in production or <code>false</code> if not
     */
    boolean isInProduction();
}
