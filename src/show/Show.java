package show;

import show.exceptions.InvalidShowRatingException;
import show.exceptions.ShowInProductionException;

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

    void addParticipation(String description);

    /**
     * Gives the show a rating
     *
     * @param rating between 0 and 10 stars
     * @throws InvalidShowRatingException if the rating is not between 0 and 10
     */
    void rate(int rating) throws InvalidShowRatingException, ShowInProductionException;

    int getRating();

    void premiere();

}
