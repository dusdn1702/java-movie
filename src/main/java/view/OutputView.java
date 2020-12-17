package view;

import domain.Movie;
import domain.MovieInformation;

import java.util.List;
import java.util.Map;

public class OutputView {
    public static void printMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    public static void printMovie(Movie movie) {
        System.out.println(movie);
    }

    public static void printTickets(Map<MovieInformation, Integer> ticketInformation) {
        System.out.println("예매 내역");
        for (MovieInformation movieInformation : ticketInformation.keySet()) {
            System.out.print(movieInformation.toString());
            System.out.println("예약 인원: " + ticketInformation.get(movieInformation) + "명");
            System.out.println();
        }
    }

    public static void printResult(long calculate) {
        System.out.println();
        System.out.println("최종 결제한 금액은 " + calculate + "원 입니다.");
        System.out.println("예매를 완료했습니다. 즐거운 영화 관람되세요.");
    }
}
