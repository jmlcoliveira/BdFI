package BdFI;

import java.io.Serializable;

/**
 * Participation interface
 *
 * @author Guilherme Pocas (60236) g.pocas@campus.fct.unl.pt
 * @author Joao Oliveira (61052) jml.oliveira@campus.fct.unl.pt
 */
public interface Participation extends Serializable {

    /**
     * Returns the Person associated with the participation
     *
     * @return the Person
     */
    Person getPerson();

    /**
     * Returns the Participation where the participation was made
     *
     * @return the Participation
     */
    Show getShow();

    /**
     * Returns the description of the participation
     *
     * @return the description
     */
    String getDescription();

}
