package participation;

import person.Person;
import show.Show;

import java.io.Serializable;

public interface Participation extends Serializable {

    /**
     * Returns the Person associated with the participation
     *
     * @return the Person
     */
    Person getPerson();

    /**
     * Returns the Show where the participation was made
     *
     * @return the Show
     */
    Show getShow();

    /**
     * Returns the description of the participation
     *
     * @return the description
     */
    String getDescription();

}
