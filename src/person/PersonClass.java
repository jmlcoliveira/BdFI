package person;

public class PersonClass implements Person {

    static final long serialVersionUID = 0L;

    private final String personID;
    private final int year;
    private final String email;
    private final String telephone;
    private final Gender gender;
    private final String name;

    public PersonClass(String personId, int year, String email, String telephone, Gender gender, String name) {
        this.personID = personId;
        this.year = year;
        this.email = email;
        this.telephone = telephone;
        this.gender = gender;
        this.name = name;
    }

    public String getPersonID() {
        return personID;
    }

    public int getYear() {
        return year;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }
}