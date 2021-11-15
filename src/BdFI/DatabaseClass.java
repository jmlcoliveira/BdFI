package BdFI;

import BdFI.database.exceptions.NoFinishedShowsException;
import BdFI.database.exceptions.NoProductionsWithRatingException;
import BdFI.database.exceptions.NoRatedShowsException;
import BdFI.database.exceptions.NoShowsException;
import BdFI.person.exceptions.*;
import BdFI.show.exceptions.*;
import dataStructures.Iterator;

/**
 * Database class which communicates with the Main class and stores information of all shows and
 * persons.
 *
 * @author Guilherme Pocas (60236) g.pocas@campus.fct.unl.pt
 * @author Joao Oliveira (61052) jml.oliveira@campus.fct.unl.pt
 */
public class DatabaseClass implements Database {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * the Person in the Database
     */
    private PersonPrivate person;

    /**
     * Array containing possible genders
     */
    public String[] genders = {"FEMALE", "MALE", "OTHER", "NOT-PROVIDED"};

    /**
     * the Show in the Show
     */
    private ShowPrivate show;

    /**
     * DatabaseClass constructor
     */
    public DatabaseClass() {
        person = null;
        show = null;
    }


    @Override
    public void addPerson(String personID, int year, String email, String telephone, String gender, String name) throws InvalidYearException, InvalidGenderException, PersonIdAlreadyExistsException {
        if (year <= 0) throw new InvalidYearException();
        validateGender(gender);
        if (getPersonP(personID) != null) throw new PersonIdAlreadyExistsException();
        person = new PersonClass(personID, year, email, telephone, gender, name);
    }

    /**
     * Checks if gender is valid
     *
     * @param gender gender to be checked
     * @throws InvalidGenderException if gender is not valid
     */
    private void validateGender(String gender) throws InvalidGenderException {
        for (String s : genders)
            if (s.compareToIgnoreCase(gender) == 0)
                return;
        throw new InvalidGenderException();
    }

    @Override
    public void addShow(String showID, int year, String title) throws InvalidShowYearException, ShowIDExistsException {
        if (year <= 0) throw new InvalidShowYearException();
        if (getShowP(showID) != null) throw new ShowIDExistsException();
        show = new ShowClass(showID, year, title);
    }

    @Override
    public void addParticipation(String personID, String showID, String description) throws PersonIdNotFoundException, ShowIdNotFoundException {
        PersonPrivate p = (PersonPrivate) getPerson(personID);
        ShowPrivate s = (ShowPrivate) getShow(showID);
        Participation part = new ParticipationClass(p, s, description);
        p.addShow(s);
        s.addParticipation(part);
    }

    @Override
    public void premiereShow(String showID) throws ShowNotInProductionException, ShowIdNotFoundException {
        ShowPrivate s = (ShowPrivate) getShow(showID);
        s.premiere();
    }

    @Override
    public void removeShow(String showID) throws ShowNotInProductionException, ShowIdNotFoundException {
        Show s = getShow(showID);
        if (!s.isInProduction())
            throw new ShowNotInProductionException();
        show = null;
    }

    @Override
    public void tagShow(String showID, String tag) throws ShowIdNotFoundException {
        ShowPrivate s = (ShowPrivate) getShow(showID);
        s.addTag(tag);
    }

    @Override
    public void reviewShow(String showID, int review) throws InvalidShowRatingException, ShowInProductionException, ShowIdNotFoundException {
        ShowPrivate s = (ShowPrivate) getShow(showID);
        s.rate(review);
    }

    @Override
    public Iterator<Participation> iteratorParticipationByShow(String showID) throws ShowIdNotFoundException, ShowHasNoParticipationsException {
        Show s = getShow(showID);
        if (s == null) throw new ShowIdNotFoundException();
        return s.iteratorParticipation();
    }

    @Override
    public Show listBestShows() throws NoShowsException, NoFinishedShowsException, NoRatedShowsException {
        if (show == null) throw new NoShowsException();
        if (show.isInProduction()) throw new NoFinishedShowsException();
        if (show.hasNoRatings()) throw new NoRatedShowsException();
        return show;
    }

    @Override
    public Show listShows(int rating) throws InvalidShowRatingException, NoShowsException,
            NoFinishedShowsException, NoRatedShowsException, NoProductionsWithRatingException {
        if (rating < 0 || rating > 10) throw new InvalidShowRatingException();
        if (show == null) throw new NoShowsException();
        if (show.isInProduction()) throw new NoFinishedShowsException();
        if (show.hasNoRatings()) throw new NoRatedShowsException();
        if (show.getRating() != rating) throw new NoProductionsWithRatingException();
        return show;
    }

    @Override
    public Show showByPersonID(String personID) throws PersonHasNoShowsException, PersonIdNotFoundException {
        Person p = getPerson(personID);
        if (!p.hasParticipation()) throw new PersonHasNoShowsException();
        return show;
    }

    @Override
    public Show getShow(String showID) throws ShowIdNotFoundException {
        Show s = getShowP(showID);
        if (s == null) throw new ShowIdNotFoundException();
        return s;
    }

    @Override
    public Person getPerson(String personID) throws PersonIdNotFoundException {
        Person p = getPersonP(personID);
        if (p == null) throw new PersonIdNotFoundException();
        return p;
    }

    /**
     * Returns a Person object with the given personID or <code>null</code> if no person exists with that id
     *
     * @param personID person's ID
     * @return a Person object with the given personID or <code>null</code> if no person exists with that id
     */
    private PersonPrivate getPersonP(String personID) {
        if (person == null || !personID.equalsIgnoreCase(person.getPersonID()))
            return null;
        return person;
    }

    /**
     * Returns a Show object with the given showID or <code>null</code> if no show exists with that id
     *
     * @param showID show's ID
     * @return a show object with the given showID or <code>null</code> if no show exists with that id
     */
    private ShowPrivate getShowP(String showID) {
        if (show == null || !showID.equalsIgnoreCase(show.getShowID()))
            return null;
        return show;
    }
}
