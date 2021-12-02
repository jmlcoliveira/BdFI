package BdFI;

import BdFI.show.exceptions.InvalidShowRatingException;
import BdFI.show.exceptions.ShowInProductionException;
import BdFI.show.exceptions.ShowNotInProductionException;

import java.io.Serializable;

/**
 * Participation's interface
 *
 * @author Guilherme Pocas (60236) g.pocas@campus.fct.unl.pt
 * @author Joao Oliveira (61052) jml.oliveira@campus.fct.unl.pt
 */
interface ShowPrivate extends Show, Comparable<ShowPrivate>, Serializable {

    /**
     * @param part participation of the show
     */
    void addParticipation(Participation part);

    /**
     * Gives the show a rating
     *
     * @param rating between 0 and 10 stars
     * @throws InvalidShowRatingException if the rating is not between 0 and 10
     * @throws ShowInProductionException  if the show is in production
     */
    void rate(int rating) throws InvalidShowRatingException, ShowInProductionException;

    /**
     * Premieres a show
     *
     * @throws ShowNotInProductionException if the Participation already premiered
     */
    void premiere() throws ShowNotInProductionException;

    /**
     * Adds a tag to a show
     *
     * @param tag tag to be added
     */
    void addTag(String tag);
}
