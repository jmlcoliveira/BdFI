package database;

import person.*;
import person.exceptions.*;
import show.*;
import show.exceptions.*;
import show.exceptions.InvalidShowIDException;
import show.exceptions.ShowNotInProductionException;

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

    @Override
    public void premiereShow(String showID) throws ShowNotInProductionException, InvalidShowIDException {
        if(showID.equals(show.getShowID()) && !show.isInProduction())
            throw new ShowNotInProductionException(showID);
        if(!showID.equals(show.getShowID()))
            throw new InvalidShowIDException(showID);
        show.premiere();
    }

    @Override
    public void removeShow(String showID) throws ShowNotInProductionException, InvalidShowIDException {
        if(showID.equals(show.getShowID()) && !show.isInProduction())
            throw new ShowNotInProductionException(showID);
        if(!showID.equals(show.getShowID()))
            throw new InvalidShowIDException(showID);
        show = null;
    }

    @Override
    public void reviewShow(String showID, int review) throws InvalidShowRatingException, ShowInProductionException, InvalidShowIDException {
        if(!showID.equals(show.getShowID()))
            throw new InvalidShowIDException(showID);
        show.rate(review);
    }
}
