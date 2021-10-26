package show;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;
import participation.Participation;
import show.exceptions.InvalidShowRatingException;
import show.exceptions.ShowHasNoParticipationsException;
import show.exceptions.ShowInProductionException;
import show.exceptions.ShowNotInProductionException;

public class ShowClass implements Show {

    static final long serialVersionUID = 0L;

    private final String showID;
    private final int year;
    private final String title;
    private final List<Participation> participations;
    private final List<String> tags;
    private int rating;
    private final int reviewCount;
    private boolean premiered;

    public ShowClass(String showID, int year, String title) {
        this.showID = showID;
        this.year = year;
        this.title = title;
        rating = -1;
        reviewCount = 0;
        participations = new DoubleList<>();
        tags = new DoubleList<>();
        premiered = false;
    }

    @Override
    public String getShowID() {
        return showID;
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

    public void addParticipation(Participation part) {
        participations.addLast(part);
    }

    public void rate(int review) throws InvalidShowRatingException, ShowInProductionException {
        if (review < 0 || review > 10)
            throw new InvalidShowRatingException();
        if (!premiered)
            throw new ShowInProductionException(showID);
        this.rating = bdfiAlg.updateReview(rating, reviewCount, review);
    }

    public int getRating() {
        return rating;
    }

    @Override
    public boolean isRated() {
        return rating != -1;
    }

    @Override
    public void premiere() throws ShowNotInProductionException {
        if (!isInProduction())
            throw new ShowNotInProductionException(showID);
        premiered = true;
    }

    @Override
    public void addTag(String tag) {
        tags.addLast(tag);
    }

    @Override
    public Iterator<String> iteratorTags() {
        return tags.iterator();
    }

    @Override
    public Iterator<Participation> iteratorParticipations() throws ShowHasNoParticipationsException {
        if(participations.isEmpty()) throw new ShowHasNoParticipationsException(showID);
        return participations.iterator();
    }

    @Override
    public boolean hasParticipation() {
        return participations.size() != 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null | !(o instanceof Show)) return false;
        return ((Show) o).getShowID().equals(showID);
    }
}
