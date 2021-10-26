package database;

import person.Gender;
import person.Person;
import person.PersonClass;
import person.exceptions.InvalidGenderException;
import person.exceptions.InvalidYearException;
import person.exceptions.PersonIdAlreadyExistsException;
import show.Show;
import show.ShowClass;
import show.exceptions.InvalidShowYearException;
import show.exceptions.ShowIDExistsException;

import java.io.Serializable;

public class DatabaseClass implements Database, Serializable {

    static final long serialVersionUID = 0L;

    private Person person;
    private Show show;

    public DatabaseClass() {

    }


    @Override
    public void addPerson(String id, int year, String email, String telephone, String gender, String name) throws InvalidYearException, InvalidGenderException, PersonIdAlreadyExistsException {
        if (year <= 0) throw new InvalidYearException();
        Gender gender1;
        try {
            gender1 = Gender.valueOf(gender.replace('-', '_').toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidGenderException();
        }
        if (person.getId().equals(id)) throw new PersonIdAlreadyExistsException(id);
        person = new PersonClass(id, year, email, telephone, gender1, name);
    }

    @Override
    public void addShow(String ShowID, int year, String title) throws InvalidShowYearException, ShowIDExistsException {
        if (year <= 0) throw new InvalidShowYearException();
        if (show.getShowID().equals(ShowID)) throw new ShowIDExistsException(ShowID);
        this.show = new ShowClass(ShowID, year, title);

    }

    @Override
    public void addParticipation() {

    }
}
