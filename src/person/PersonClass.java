package person;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;
import participation.Participation;

/**
 * Person's class containing all information regarding a person
 *
 * @author Guilherme Pocas (60236) g.pocas@campus.fct.unl.pt
 * @author Joao Oliveira (61052) jml.oliveira@campus.fct.unl.pt
 */
public class PersonClass implements Person {

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
    private final List<Participation> participation;

    /**
     * PersonsClass constructor
     *
     * @param personId   Person's ID
     * @param year       Person's year of birth
     * @param email      Person's email
     * @param telephone  Person's phone number
     * @param gender     Person's gender
     * @param name       Person's name
     */
    public PersonClass(String personId, int year, String email, String telephone, String gender, String name) {
        this.personID = personId;
        this.year = year;
        this.email = email;
        this.telephone = telephone;
        this.gender = gender;
        this.name = name;
        participation = new DoubleList<>();
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
    public void addParticipation(Participation part) {
        participation.addLast(part);
    }

    @Override
    public Iterator<Participation> iteratorParticipation() {
        return participation.iterator();
    }

    @Override
    public boolean hasParticipation() {
        return participation.size() != 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Person)) return false;
        return ((Person) o).getPersonID().equals(personID);
    }
}
