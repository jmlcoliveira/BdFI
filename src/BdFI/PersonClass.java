package BdFI;

/**
 * Person's class containing all information regarding a person
 *
 * @author Guilherme Pocas (60236) g.pocas@campus.fct.unl.pt
 * @author Joao Oliveira (61052) jml.oliveira@campus.fct.unl.pt
 */
public class PersonClass implements PersonPrivate {

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
     * List containing all shows where this person participated
     */
    private Show showsOfPerson;

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
        showsOfPerson = null;
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
    public void addShow(Show part) {
        if (showsOfPerson == null)
            showsOfPerson = part;
    }

    @Override
    public boolean hasParticipation() {
        return showsOfPerson != null;
    }
}