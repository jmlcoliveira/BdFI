package participation;

import person.Person;
import show.Show;

import java.io.Serializable;

public interface Participation extends Serializable {

    Person getPerson();

    Show getShow();

    String getDescription();

}
