package participation;

import person.Person;
import show.Show;

public class ParticipationClass implements Participation {

    /**
     * the Person who participated
     */
    private final Person person;
    /**
     * the Show the Persons participated in
     */
    private final Show show;
    /**
     * the description of what the Person did
     */
    private final String description;

    /**
     * ParticipationClass constructor
     *
     * @param person       the Person
     * @param show         the Show
     * @param description  the description
     */
    public ParticipationClass(Person person, Show show, String description) {
        this.person = person;
        this.show = show;
        this.description = description;
    }

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public Show getShow() {
        return show;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
