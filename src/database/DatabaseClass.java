package database;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import database.exceptions.*;
import participation.Participation;
import participation.ParticipationClass;
import person.Gender;
import person.Person;
import person.PersonClass;
import person.exceptions.*;
import show.Show;
import show.ShowClass;
import show.exceptions.*;

import java.io.Serializable;

public class DatabaseClass implements Database, Serializable {

    static final long serialVersionUID = 0L;

    private Person person;
    private Show show;

    public DatabaseClass() {
        person = null;
        show = null;
    }


    @Override
    public void addPerson(String personID, int year, String email, String telephone, String gender, String name) throws InvalidYearException, InvalidGenderException, PersonIdAlreadyExistsException {
        if (year <= 0) throw new InvalidYearException();
        Gender gender1;
        try {
            gender1 = Gender.valueOf(gender.replace('-', '_').toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidGenderException();
        }
        /*Person p = getPerson(personID);
        if (p != null) throw new PersonIdAlreadyExistsException(personID);*/
        person = new PersonClass(personID, year, email, telephone, gender1, name);
    }

    @Override
    public void addShow(String showID, int year, String title) throws InvalidShowYearException, ShowIDExistsException {
        if (year <= 0) throw new InvalidShowYearException();
        /*Show s = getShowP(showID);
        if (s != null) throw new ShowIDExistsException(showID);*/
        show = new ShowClass(showID, year, title);
    }

    @Override
    public void addParticipation(String personID, String showID, String description) throws PersonIdNotFoundException, ShowIdNotFoundException {
        Person p = getPerson(personID);
        Show s = getShow(showID);
        Participation part = new ParticipationClass(p, s, description);
        p.addParticipation(part);
        s.addParticipation(part);
    }

    @Override
    public void premiereShow(String showID) throws ShowNotInProductionException, ShowIdNotFoundException {
        Show s = getShow(showID);
        if (s == null) throw new ShowIdNotFoundException(showID);
        s.premiere();
    }

    @Override
    public void removeShow(String showID) throws ShowNotInProductionException, ShowIdNotFoundException {
        Show s = getShow(showID);
        if (s == null)
            throw new ShowIdNotFoundException(showID);
        if (!s.isInProduction())
            throw new ShowNotInProductionException(showID);
        show = null;
    }

    @Override
    public void tagShow(String showID, String tag) throws ShowIdNotFoundException {
        Show s = getShow(showID);
        if (s == null)
            throw new ShowIdNotFoundException(showID);
        s.addTag(tag);
    }

    @Override
    public void reviewShow(String showID, int review) throws InvalidShowRatingException, ShowInProductionException, ShowIdNotFoundException {
        Show s = getShow(showID);
        if (s == null) throw new ShowIdNotFoundException(showID);
        s.rate(review);
    }

    public Person getPerson(String personID) throws PersonIdNotFoundException {
        Person p = getPersonP(personID);
        if (p == null) throw new PersonIdNotFoundException(personID);
        return p;
    }

    @Override
    public Iterator<Participation> iteratorParticipationByShow(String showID) throws ShowIdNotFoundException, ShowHasNoParticipationsException {
        Show s = getShow(showID);
        if (s == null) throw new ShowIdNotFoundException(showID);
        return s.iteratorParticipations();
    }

    @Override
    public Iterator<Show> listBestShows() throws NoShowsException, NoFinishedShowsException, NoRatedShowsException {
        if (show == null) throw new NoShowsException();
        if (show.isInProduction()) throw new NoFinishedShowsException();
        if (!show.isRated()) throw new NoRatedShowsException();
        List<Show> showList = new DoubleList<>();
        return showList.iterator();

    }

    @Override
    public Iterator<Show> iteratorShowsByTag(String tag) throws NoShowsException, NoTaggedShowsException, NoShowsWithTagException {
        return null;
    }

    @Override
    public Iterator<Participation> iteratorShowsByPerson(String personID) throws PersonHasNoShowsException, PersonIdNotFoundException {
        Person p = getPerson(personID);
        if (!p.hasParticipation()) throw new PersonHasNoShowsException(personID);
        return p.iteratorParticipation();
    }

    public Show getShow(String showID) throws ShowIdNotFoundException {
        Show s = getShowP(showID);
        if (s == null) throw new ShowIdNotFoundException(showID);
        return s;
    }

    /**
     * Returns a Person object with the given personID or <code>null</code> if no person exists with that id
     *
     * @param personID person's ID
     * @return a Person object with the given personID or <code>null</code> if no person exists with that id
     */
    private Person getPersonP(String personID) {
        //Person p = new PersonClass(personID, 0, null, null, null, null);
        //To be completed in phase 2
        return person;
    }

    /**
     * Returns a Show object with the given showID or <code>null</code> if no show exists with that id
     *
     * @param showID show's ID
     * @return a Show object with the given showID or <code>null</code> if no show exists with that id
     */
    private Show getShowP(String showID) {
        //Show s = new ShowClass(showID, 0, null);
        //To be completed in phase 2
        return show;
    }
}
