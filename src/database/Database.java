package database;

import person.exceptions.InvalidGenderException;
import person.exceptions.InvalidYearException;
import person.exceptions.PersonIdAlreadyExistsException;
import show.exceptions.InvalidShowIDException;
import show.exceptions.InvalidShowYearException;

import java.io.Serializable;

public interface Database extends Serializable {

    /**
     * @param id        person's id
     * @param year      person's birth year
     * @param email     person's email
     * @param telephone person's phone number
     * @param gender    person's gender
     * @param name      person's name
     * @throws InvalidYearException           if <code>year <= 0</code>
     * @throws InvalidGenderException         if gender is invalid
     * @throws PersonIdAlreadyExistsException if id already exists
     */
    void addPerson(String id, int year, String email, String telephone, String gender, String name)
            throws InvalidYearException, InvalidGenderException, PersonIdAlreadyExistsException;

    /**
     * @param idShow show's id
     * @param year   date the show was made
     * @param title  title of the show
     */
    void addShow(String idShow, int year, String title)
            throws InvalidShowYearException, InvalidShowIDException;

    void addParticipation()
}
