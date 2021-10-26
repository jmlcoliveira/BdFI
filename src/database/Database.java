package database;

import person.Person;
import person.exceptions.InvalidGenderException;
import person.exceptions.InvalidYearException;
import person.exceptions.PersonIdAlreadyExistsException;
import person.exceptions.PersonIdNotFoundException;
import show.Show;
import show.exceptions.*;

import java.io.Serializable;

public interface Database extends Serializable {

    /**
     * @param personID  person's ID
     * @param year      person's birth year
     * @param email     person's email
     * @param telephone person's phone number
     * @param gender    person's gender
     * @param name      person's name
     * @throws InvalidYearException           if <code>year <= 0</code>
     * @throws InvalidGenderException         if gender is invalid
     * @throws PersonIdAlreadyExistsException if personID already exists
     */
    void addPerson(String personID, int year, String email, String telephone, String gender, String name)
            throws InvalidYearException, InvalidGenderException, PersonIdAlreadyExistsException;

    /**
     * @param showID show's id
     * @param year   date the show was made
     * @param title  title of the show
     */
    void addShow(String showID, int year, String title)
            throws InvalidShowYearException, ShowIDExistsException;

    /**
     * @param personID    person's ID
     * @param showID      show's id
     * @param description description of certain person's participation in a show
     * @throws PersonIdNotFoundException if personID does not exist
     * @throws ShowIdNotFoundException   if showID does not exist
     */
    void addParticipation(String personID, String showID, String description) throws PersonIdNotFoundException, ShowIdNotFoundException;

    /**
     * Premieres a show
     *
     * @param showID show's id
     * @throws ShowNotInProductionException if show already premiered
     * @throws ShowIdNotFoundException      if there is no show with the same id
     */
    void premiereShow(String showID) throws ShowNotInProductionException, ShowIdNotFoundException;

    /**
     * Removes a show
     *
     * @param showID show's id
     * @throws ShowNotInProductionException if show already premiered
     * @throws ShowIdNotFoundException      if there is no show with the same id
     */
    void removeShow(String showID) throws ShowNotInProductionException, ShowIdNotFoundException;

    /**
     * Adds a tag to a show
     *
     * @param showID show's id
     * @param tag    Tag to be added to the show
     */
    void tagShow(String showID, String tag) throws ShowIdNotFoundException;

    /**
     * Gives a show a review
     *
     * @param showID show's id
     * @param review rating between 0 and 10 stars
     * @throws InvalidShowRatingException if the review is not between 0 and 10 stars
     * @throws ShowInProductionException  if the show is still in production
     * @throws ShowIdNotFoundException    if there is no show with the same id
     */
    void reviewShow(String showID, int review)
            throws InvalidShowRatingException, ShowInProductionException, ShowIdNotFoundException;

    /**
     * Returns a Show object with the given ID
     *
     * @param showID show's ID
     * @return Show object with the given ID
     */
    Show getShow(String showID) throws ShowIdNotFoundException;

    /**
     * Returns a Show object with the given ID
     *
     * @param showID show's ID
     * @return Show object with the given ID
     */
    Person getPerson(String showID) throws PersonIdNotFoundException;
}
