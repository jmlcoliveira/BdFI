package show;

import dataStructures.DoubleList;
import dataStructures.List;
import show.exceptions.InvalidShowRatingException;
import show.exceptions.ShowInProductionException;

public class ShowClass implements Show {

    static final long serialVersionUID = 0L;

    private final String idShow;
    private final int year;
    private final String title;
    private final List<String> participations;
    private final List<String> tags;
    private int rating;
    private boolean premiered;

    public ShowClass(String idShow, int year, String title) {
        this.idShow = idShow;
        this.year = year;
        this.title = title;
        rating = -1;
        participations = new DoubleList<String>();
        tags = new DoubleList<>();
    }

    @Override
    public String getShowID() {
        return idShow;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Boolean isInProduction() {
        return !premiered;
    }

    public void addParticipation(String description) {
        participations.addLast(description);
    }

    public void rate(int rating) throws InvalidShowRatingException, ShowInProductionException {
        if (rating < 0 || rating > 10)
            throw new InvalidShowRatingException();
        if (!premiered)
            throw new ShowInProductionException(idShow);
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }
}
