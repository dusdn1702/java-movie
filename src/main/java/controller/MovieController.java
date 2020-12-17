package controller;

import domain.Movie;
import domain.MovieRepository;
import view.InputView;
import view.OutputView;

import java.util.List;

public class MovieController {
    public void run() {
        List<Movie> movies = MovieRepository.getMovies();
        OutputView.printMovies(movies);

        Movie movie = receiveMovie();

        int scheduleId = receiveSchedule(movie);
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
        movie.checkPossibleCapacity(scheduleId);
        return scheduleId;
    }
}
