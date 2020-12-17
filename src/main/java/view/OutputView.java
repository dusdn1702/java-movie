package view;

import domain.Movie;
import domain.MovieInformation;
import domain.TicketInformation;

import java.util.List;
import java.util.Map;

public class OutputView {
    public static void printMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
    public static void printMovie(Movie movie){
        System.out.println(movie);
    }

    public static void printTickets(Map<MovieInformation, Integer> ticketInformation) {
        System.out.println("예매 내역");
        for(MovieInformation movieInformation : ticketInformation.keySet()){
            System.out.print(movieInformation.toString());
            System.out.println("예약 인원: "+ticketInformation.get(movieInformation)+"명");
        }
    }
}
