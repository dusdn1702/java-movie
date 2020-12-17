package domain;

import java.util.Map;

public class TicketInformation {
    private MovieInformation movieInformation;
    private int people;

    public TicketInformation(MovieInformation movieInformation, int people) {
        this.movieInformation = movieInformation;
        this.people = people;
    }

    @Override
    public String toString() {
        return "예약 인원: "+people;
    }

}
