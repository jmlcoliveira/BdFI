package person;

import dataStructures.Iterator;
import show.Show;

import java.io.Serializable;

/**
 * Person's interface
 *
 * @author Guilherme Pocas (60236) g.pocas@campus.fct.unl.pt
 * @author Joao Oliveira (61052) jml.oliveira@campus.fct.unl.pt
 */
public interface Person extends Serializable {

    /**
     * Returns the person's id
     *
     * @return the person's id
     */
    String getPersonID();

    /**
     * Returns the person's year
     *
     * @return the person's year
     */
    int getYear();

    /**
     * Returns the person's email
     *
     * @return the person's email
     */
    String getEmail();

    /**
     * Returns the person's telephone
     *
     * @return the person's telephone
     */
    String getTelephone();

    /**
     * Returns the person's gender
     *
     * @return the person's gender
     */
    String getGender();

    /**
     * Returns the person's name
     *
     * @return the person's name
     */
    String getName();

    /**
     * Adds a participation
     *
     * @param show participation
     */
    void addParticipation(Show show);

    /**
     * Returns an iterator containing all show where the person participated
     *
     * @return an iterator containing all show where the person participated
     */
    Iterator<Show> iteratorShows();

    /**
     * Returns <code>true</code> if person has any participation, <code>false</code> otherwise
     *
     * @return <code>true</code> if person has any participation, <code>false</code> otherwise
     */
    boolean hasParticipation();
}
