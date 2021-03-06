package BdFI;

import dataStructures.Iterator;
import dataStructures.orderedDictionaries.AVLTree;
import dataStructures.orderedDictionaries.OrderedDictionary;

/**
 * Person's class containing all information regarding a person
 *
 * @author Guilherme Pocas (60236) g.pocas@campus.fct.unl.pt
 * @author Joao Oliveira (61052) jml.oliveira@campus.fct.unl.pt
 */
class PersonClass implements PersonPrivate {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * Person's ID
     */
    private final String personID;
    /**
     * Person's year of birth
     */
    private final int year;
    /**
     * Person's email
     */
    private final String email;
    /**
     * Person's phone number
     */
    private final String telephone;
    /**
     * Person's gender
     */
    private final String gender;
    /**
     * Person's name
     */
    private final String name;

    /**
     * OrderedDictionary containing all shows where this person participated, ordered by showID
     * Key - showID
     * Value - show
     */
    private final OrderedDictionary<String, Show> showsOfPerson;

    /**
     * Class constructor
     *
     * @param personId  Person's ID
     * @param year      Person's year of birth
     * @param email     Person's email
     * @param telephone Person's phone number
     * @param gender    Person's gender
     * @param name      Person's name
     */
    public PersonClass(String personId, int year, String email, String telephone, String gender, String name) {
        this.personID = personId;
        this.year = year;
        this.email = email;
        this.telephone = telephone;
        this.gender = gender;
        this.name = name;
        showsOfPerson = new AVLTree<>();
    }

    @Override
    public String getPersonID() {
        return personID;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getTelephone() {
        return telephone;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    //O(log(n))
    public void addShow(Show show) {
        showsOfPerson.insert(show.getShowID(), show);
    }

    @Override
    //O(log(n))
    public void removeShow(Show show) {
        showsOfPerson.remove(show.getShowID());
    }

    @Override
    public boolean hasParticipation() {
        return showsOfPerson.size() != 0;
    }

    @Override
    //O(log(n))
    public Iterator<Show> showIterator() {
        return showsOfPerson.iteratorValues();
    }
}
