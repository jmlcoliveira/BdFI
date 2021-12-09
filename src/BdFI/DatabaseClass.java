package BdFI;

import BdFI.database.exceptions.*;
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
     * Dictionary containing every Person
     * the Key is the PersonID
     * the Value is the Person
     */
    private final Dictionary<String, PersonPrivate> personByID;

    /**
     * Dictionary containing every Show
     * the Key is the ShowID
     * the Value is the Show
     */
    private final Dictionary<String, ShowPrivate> showsByID;

    /**
     * Ordered Dictionary containing Shows with a certain Rating
     * the Key is the rating
     * the Value is an orderedList of Shows, ordered by name
     */
    private final OrderedDictionary<Integer, OrderedList<Show>> listOfShowsByRating;

    /**
     * Ordered Dictionary containing Shows with a certain Tag
     * the Key is the Tag
     * the Value is an orderedList of Shows, ordered by name
     */
    private final Dictionary<String, OrderedList<Show>> listOfShowsByTag;

    /**
     * number of Shows in production
     */
    private int showsInProductionCounter;


    /**
     * DatabaseClass constructor
     */
    //O(1)
    public DatabaseClass() {
        personByID = new SepChainHashTable<>();
        showsByID = new SepChainHashTable<>();
        listOfShowsByRating = new AVLTree<>();
        listOfShowsByTag = new SepChainHashTable<>();
        showsInProductionCounter = 0;
    }

    @Override
    //0(1)
    public void addPerson(String personID, int year, String email, String telephone, String gender, String name) throws InvalidYearException, PersonIdAlreadyExistsException {
        if (year <= 0) throw new InvalidYearException();
        if (personByID.find(personID.toUpperCase()) != null) throw new PersonIdAlreadyExistsException();

        personByID.insert(personID.toUpperCase(), new PersonClass(personID, year, email, telephone, gender, name));
    }

    @Override
    //O(1)
    public void addShow(String showID, int year, String title) throws InvalidShowYearException, ShowIDExistsException {
        if (year <= 0) throw new InvalidShowYearException();
        if (showsByID.find(showID.toUpperCase()) != null) throw new ShowIDExistsException();

        ShowPrivate show = new ShowClass(showID, year, title);
        showsByID.insert(showID.toUpperCase(), show);

        if (show.isInProduction()) showsInProductionCounter++;
    }

    @Override
    //O(log(n))
    public void addParticipation(String personID, String showID, String description) throws PersonIdNotFoundException, ShowIdNotFoundException {
        PersonPrivate p = (PersonPrivate) getPerson(personID);
        ShowPrivate s = (ShowPrivate) getShow(showID);
        Participation part = new ParticipationClass(p, s, description);
        p.addShow(s);
        s.addParticipation(part);
    }

    @Override
    //O(1)
    public void premiereShow(String showID) throws ShowNotInProductionException, ShowIdNotFoundException {
        ShowPrivate s = (ShowPrivate) getShow(showID);
        s.premiere();
        showsInProductionCounter--;
    }

    @Override
    //O(n)
    public void removeShow(String showID) throws ShowNotInProductionException, ShowIdNotFoundException {
        Show s = getShow(showID);
        if (!s.isInProduction())
            throw new ShowNotInProductionException();
        showsInProductionCounter--;

        Iterator<Person> itP = s.iteratorPersonsInShow();
        while (itP.hasNext()) {
            PersonPrivate p = (PersonPrivate) itP.next();
            p.removeShow((ShowPrivate) s);
        }
        showsByID.remove(s.getShowID().toUpperCase());
        if (listOfShowsByRating.find(s.getRating()) != null)
            listOfShowsByRating.find(s.getRating()).remove(s);


        Iterator<String> itTags = s.iteratorTags();
        while (itTags.hasNext()) {
            String tag = itTags.next().toUpperCase();
            OrderedList<Show> l = listOfShowsByTag.find(tag);
            l.remove(s);
            if(l.isEmpty())
                listOfShowsByTag.remove(tag);
        }
    }

    @Override
    //O(n)
    public void tagShow(String showID, String tag) throws ShowIdNotFoundException {
        ShowPrivate s = (ShowPrivate) getShow(showID);
        s.addTag(tag);

        if (listOfShowsByTag.find(tag.toUpperCase()) == null) {
            OrderedList<Show> shows = new OrderedDoubleList<>(new ComparatorByShowName());
            shows.insert(s);
            listOfShowsByTag.insert(tag.toUpperCase(), shows);
        } else {
            listOfShowsByTag.find(tag.toUpperCase()).insert(s);
        }
    }

    @Override
    //O(n)
    public void reviewShow(String showID, int review) throws InvalidShowRatingException, ShowInProductionException, ShowIdNotFoundException {
        if (review < 0 || review > 10) throw new InvalidShowRatingException();
        ShowPrivate s = showsByID.find(showID.toUpperCase());
        if (s != null && s.isInProduction()) throw new ShowInProductionException();
        if (s == null) throw new ShowIdNotFoundException();


        int oldRating = s.getRating();
        s.rate(review);
        int newRating = s.getRating();
        if (newRating == 0) {
            if (listOfShowsByRating.find(newRating) == null)
                listOfShowsByRating.insert(newRating, new OrderedDoubleList<>(new ComparatorByShowName()));
            listOfShowsByRating.find(newRating).insert(s);
        }

        if (oldRating != newRating) {
            if (listOfShowsByRating.find(oldRating) != null)
                listOfShowsByRating.find(oldRating).remove(s);
            if (listOfShowsByRating.find(newRating) == null)
                listOfShowsByRating.insert(newRating, new OrderedDoubleList<>(new ComparatorByShowName()));
            listOfShowsByRating.find(newRating).insert(s);
        }
    }

    @Override
    //O(1)
    public Iterator<Participation> iteratorParticipationByShow(String showID) throws ShowIdNotFoundException, ShowHasNoParticipationsException {
        Show s = getShow(showID);
        if (s == null) throw new ShowIdNotFoundException();
        return s.iteratorParticipation();
    }

    @Override
    //O(1)
    public Iterator<Show> listBestShows() throws NoShowsException, NoFinishedShowsException, NoRatedShowsException {
        if(showsByID.isEmpty()) throw new NoShowsException();
        if (showsInProductionCounter == showsByID.size()) throw new NoFinishedShowsException();
        if (listOfShowsByRating.isEmpty()) throw new NoRatedShowsException();
        return listShows(listOfShowsByRating.maxEntry().getKey());
    }

    @Override
    //O(1) because is an AVL with a max of 11 elements
    public Iterator<Show> listShows(int rating) throws InvalidShowRatingException, NoShowsException,
            NoFinishedShowsException, NoRatedShowsException, NoProductionsWithRatingException {
        if (rating < 0 || rating > 10) throw new InvalidShowRatingException();
        if (showsByID.isEmpty()) throw new NoShowsException();
        if (showsInProductionCounter == showsByID.size()) throw new NoFinishedShowsException();
        if (listOfShowsByRating.isEmpty()) throw new NoRatedShowsException();

        OrderedList<Show> shows = listOfShowsByRating.find(rating);
        if (shows == null || shows.isEmpty()) throw new NoProductionsWithRatingException();
        return shows.iterator();
    }

    @Override
    //O(1)
    public Iterator<Show> listTaggedShows(String tag) throws NoShowsException, NoTaggedProductionsException,
            NoShowsWithTagException {
        if (showsByID.isEmpty()) throw new NoShowsException();
        if (listOfShowsByTag.isEmpty()) throw new NoTaggedProductionsException();
        OrderedList<Show> shows = listOfShowsByTag.find(tag.toUpperCase());
        if (shows == null || shows.isEmpty()) throw new NoShowsWithTagException();

        return shows.iterator();
    }

    @Override
    //O(1)
    public Iterator<Show> showsByPersonID(String personID) throws PersonHasNoShowsException, PersonIdNotFoundException {
        Person p = getPerson(personID);
        if (!p.hasParticipation()) throw new PersonHasNoShowsException();
        return p.showIterator();
    }

    @Override
    //O(1)
    public Show getShow(String showID) throws ShowIdNotFoundException {
        Show s = showsByID.find(showID.toUpperCase());
        if (s == null) throw new ShowIdNotFoundException();
        return s;
    }

    @Override
    //O(1)
    public Person getPerson(String personID) throws PersonIdNotFoundException {
        Person p = personByID.find(personID.toUpperCase());
        if (p == null) throw new PersonIdNotFoundException();
        return p;
    }
}
