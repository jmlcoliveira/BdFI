package participation;

import person.Person;
import show.Show;

public class ParticipationClass implements Participation {

    private final Person person;
    private final Show show;
    private final String description;

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
