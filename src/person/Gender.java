package person;

public enum Gender {
    FEMALE("Female"),
    MALE("Male"),
    OTHER("Other"),
    NOT_PROVIDED("Not-provided");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
