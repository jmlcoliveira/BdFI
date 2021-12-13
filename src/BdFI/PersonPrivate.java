package BdFI;

import java.io.Serializable;

/**
 * Person's interface
 *
 * @author Guilherme Pocas (60236) g.pocas@campus.fct.unl.pt
 * @author Joao Oliveira (61052) jml.oliveira@campus.fct.unl.pt
 */
interface PersonPrivate extends Person, Serializable {

    /**
     * Adds a participation
     *
     * @param show participation
     */
    void addShow(Show show);

    /**
     * Removes a show
     *
     * @param show show
     */
    void removeShow(Show show);
}
