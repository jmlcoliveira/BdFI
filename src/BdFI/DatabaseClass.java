package BdFI;

import BdFI.database.exceptions.NoFinishedShowsException;
import BdFI.database.exceptions.NoProductionsWithRatingException;
import BdFI.database.exceptions.NoRatedShowsException;
import BdFI.database.exceptions.NoShowsException;
import BdFI.person.exceptions.InvalidYearException;
import BdFI.person.exceptions.PersonHasNoShowsException;
import BdFI.person.exceptions.PersonIdAlreadyExistsException;
import BdFI.person.exceptions.PersonIdNotFoundException;
import BdFI.show.exceptions.*;
import dataStructures.*;

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
    private final Dictionary<String, PersonPrivate> personByID;

    /**
     * the Show in the Show
     */
    private final Dictionary<String, ShowPrivate> showsByID;
    private final OrderedDictionary<Integer, OrderedList<Show>> listOfShowsByRating;
    private final Dictionary<String, OrderedList<Show>> listOfShowsByTag;

    private int showsInProductionCounter;


    /**
     * DatabaseClass constructor
     */
    public DatabaseClass() {
        personByID = new SepChainHashTable<>();
        showsByID = new SepChainHashTable<>();
        listOfShowsByRating = new BinarySearchTree<>();
        listOfShowsByTag = new SepChainHashTable<>();
        showsInProductionCounter = 0;
    }

    @Override
    public void addPerson(String personID, int year, String email, String telephone, String gender, String name) throws InvalidYearException, PersonIdAlreadyExistsException {
        if (year <= 0) throw new InvalidYearException();
        if (personByID.find(personID) != null) throw new PersonIdAlreadyExistsException();

        personByID.insert(personID, new PersonClass(personID, year, email, telephone, gender, name));
        showsByPerson.insert(personID, new BinarySearchTree<>());
    }

    @Override
    public void addShow(String showID, int year, String title) throws InvalidShowYearException, ShowIDExistsException {
        if (year <= 0) throw new InvalidShowYearException();
        if (showsByID.find(showID) != null) throw new ShowIDExistsException();

        ShowPrivate show = new ShowClass(showID, year, title);
        showsByID.insert(showID, show);
    }

    @Override
    public void addParticipation(String personID, String showID, String description) throws PersonIdNotFoundException, ShowIdNotFoundException {
        PersonPrivate p = (PersonPrivate) getPerson(personID);
        ShowPrivate s = (ShowPrivate) getShow(showID);
        Participation part = new ParticipationClass(p, s, description);
        p.addShow(s);
        s.addParticipation(part);

        if (showsByPerson.find(personID) == null) {
            showsByPerson.insert(personID, new BinarySearchTree<>());
        } else if () //todo
    }

    @Override
    public void premiereShow(String showID) throws ShowNotInProductionException, ShowIdNotFoundException {
        ShowPrivate s = (ShowPrivate) getShow(showID);
        s.premiere();
        showsInProductionCounter--;
    }

    @Override
    public void removeShow(String showID) throws ShowNotInProductionException, ShowIdNotFoundException {
        Show s = getShow(showID);
        if (!s.isInProduction())
            throw new ShowNotInProductionException();
        showsInProductionCounter--;
        Iterator<Person> it = s.iteratorPersonsInShow();
        while (it.hasNext()) {
            PersonPrivate p = (PersonPrivate) it.next();
            p.removeShow((ShowPrivate) s);
        }
        showsByID.remove(s.getShowID());

    }

    @Override
    public void tagShow(String showID, String tag) throws ShowIdNotFoundException {
        ShowPrivate s = (ShowPrivate) getShow(showID);
        s.addTag(tag);
    }

    @Override
    public void reviewShow(String showID, int review) throws InvalidShowRatingException, ShowInProductionException, ShowIdNotFoundException {
        ShowPrivate s = (ShowPrivate) getShow(showID);
        int oldRating = s.getRating();
        s.rate(review);
        int newRating = s.getRating();
        if (oldRating != newRating) {
            if (listOfShowsByRating.find(oldRating) != null)
                listOfShowsByRating.find(oldRating).remove(s);
            if (listOfShowsByRating.find(newRating) == null)
                listOfShowsByRating.insert(newRating, new OrderedDoubleList<>());
            listOfShowsByRating.find(newRating).insert(s);
        }
    }

    @Override
    public Iterator<Participation> iteratorParticipationByShow(String showID) throws ShowIdNotFoundException, ShowHasNoParticipationsException {
        Show s = getShow(showID);
        if (s == null) throw new ShowIdNotFoundException();
        return s.iteratorParticipation();
    }

    @Override
    public Iterator<Show> listBestShows() throws NoShowsException, NoFinishedShowsException, NoRatedShowsException {
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
        Show s = showsByID.find(showID);
        if (s == null) throw new ShowIdNotFoundException();
        return s;
    }

    @Override
    public Person getPerson(String personID) throws PersonIdNotFoundException {
        Person p = personByID.find(personID);
        if (p == null) throw new PersonIdNotFoundException();
        return p;
    }
}
