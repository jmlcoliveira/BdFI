package BdFI;

public interface Person {
    /**
     * Returns the person's id
     *
     * @return the person's id
     */
    String getPersonID();

    /**
     * Returns the person's year
     *
     * @return the person's year
     */
    int getYear();

    /**
     * Returns the person's email
     *
     * @return the person's email
     */
    String getEmail();

    /**
     * Returns the person's telephone
     *
     * @return the person's telephone
     */
    String getTelephone();

    /**
     * Returns the person's gender
     *
     * @return the person's gender
     */
    String getGender();

    /**
     * Returns the person's name
     *
     * @return the person's name
     */
    String getName();

    /**
     * Returns <code>true</code> if person has any participation, <code>false</code> otherwise
     *
     * @return <code>true</code> if person has any participation, <code>false</code> otherwise
     */
    boolean hasParticipation();
}
