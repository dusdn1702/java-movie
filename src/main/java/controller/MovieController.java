package controller;

import domain.Movie;
import domain.MovieRepository;
import domain.TicketRepository;
import view.InputView;
import view.OutputView;

import java.util.List;

public class MovieController {

    public static final int RESTART_FLAG = 2;

    public void run() {
        List<Movie> movies = MovieRepository.getMovies();
        do {
            OutputView.printMovies(movies);
            makeTicket();
        }while (receiveRestart());
    }

    private void makeTicket() {
        Movie movie = receiveMovie();

        int scheduleId = receiveSchedule(movie);
        int people = receivePeople(movie, scheduleId);
        TicketRepository.addTicket(movie, people);
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
