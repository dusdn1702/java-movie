package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

public class MovieController {

    public static final int RESTART_FLAG = 2;

    public void run() {
        List<Movie> movies = MovieRepository.getMovies();
        do {
            OutputView.printMovies(movies);
            makeTicket();
        }while (receiveRestart());
        OutputView.printTickets(TicketInformationRepository.getTickets());
        long point = receivePoint();
    }

    private long receivePoint() {
        long point = InputView.inputPoint();
        return point;
    }

    private void makeTicket() {
        Movie movie = receiveMovie();

        int scheduleId = receiveSchedule(movie);
        MovieInformation movieInformation = new MovieInformation(movie, movie.getSchedule(scheduleId));
        for(MovieInformation enrolledMovie: TicketInformationRepository.getTickets().keySet()){
            if(!enrolledMovie.isValidMovie(movieInformation)){
                throw new IllegalArgumentException("1시간 이상 차이 납니다.");
            }
        }

        int people = receivePeople(movie, scheduleId);
        TicketInformationRepository.addPeople(people, movieInformation);
    }

    private Movie receiveMovie() {
        int movieId = InputView.inputMovieId();
        Movie movie = MovieRepository.findById(movieId);
        OutputView.printMovie(movie);
        return movie;
    }

    private int receiveSchedule(Movie movie) {
        int scheduleId = InputView.inputScheduleId();
        movie.checkPastTime(scheduleId);
        movie.checkMoreThanZero(scheduleId);
        return scheduleId;
    }

    private int receivePeople(Movie movie, int scheduleId) {
        int people = InputView.inputPeople();
        movie.checkPositive(people);
        movie.checkPossiblePeople(scheduleId, people);
        return people;
    }

    private boolean receiveRestart() {
        int restartFlag = InputView.inputRestart();
        return restartFlag == RESTART_FLAG;
    }
}
