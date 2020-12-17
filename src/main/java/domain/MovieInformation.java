package domain;

import utils.DateTimeUtils;

import java.util.Map;

public class MovieInformation {
    private Movie movie;
    private PlaySchedule playSchedule;

    public MovieInformation(Movie movie, PlaySchedule playSchedule) {
        this.movie = movie;
        this.playSchedule = playSchedule;
    }

    public boolean isValidMovie(MovieInformation movieInformation) {
        return playSchedule.isInOneHour(movieInformation.playSchedule, this.playSchedule);
    }

    @Override
    public String toString() {
        return movie.toTicketString() +playSchedule.toTicketString();
    }

    public long makePriceWithPeople(Integer people) {
        return movie.calculatePriceWithPeople(people);
    }
}
