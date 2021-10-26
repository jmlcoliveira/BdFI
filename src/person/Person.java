package person;

import dataStructures.Iterator;
import participation.Participation;

import java.io.Serializable;

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
    Gender getGender();

    /**
     * Returns the person's name
     *
     * @return the person's name
     */
    String getName();

    void addParticipation(Participation part);

    Iterator<Participation> iteratorParticipation();

    boolean hasParticipation();
}
