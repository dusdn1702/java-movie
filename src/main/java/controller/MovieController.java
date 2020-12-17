package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class MovieController {
    private static final int RESTART_FLAG = 2;
    private static final int DONE_FLAG = 1;

    public void run() {
        List<Movie> movies = MovieRepository.getMovies();
        do {
            OutputView.printMovies(movies);
            makeTicket();
        } while (receiveMoreTicket());
        OutputView.printTickets(TicketInformationRepository.getTickets());
        makeResult();
    }

    private void makeTicket() {
        try {
            Movie movie = receiveMovie();
            int scheduleId = receiveSchedule(movie);
            MovieInformation movieInformation = new MovieInformation(movie, movie.getSchedule(scheduleId));
            checkOneHour(movieInformation);
            int people = receivePeople(movie, scheduleId);
            TicketInformationRepository.addPeople(people, movieInformation);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            makeTicket();
        }
    }

    private void checkOneHour(MovieInformation movieInformation) {
        for (MovieInformation enrolledMovie : TicketInformationRepository.getTickets().keySet()) {
            if (!enrolledMovie.isValidMovie(movieInformation)) {
                throw new IllegalArgumentException("1시간 이상 차이 납니다.");
            }
        }
    }

    private Movie receiveMovie() {
        Movie movie = MovieRepository.findById(receiveNotNumber());
        OutputView.printMovie(movie);
        return movie;
    }

    private int receiveNotNumber() {
        try {
            return Integer.parseInt(InputView.inputMovieId());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요");
            return receiveNotNumber();
        }
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

    private boolean receiveMoreTicket() {
        try {
            int restartFlag = InputView.inputRestart();
            if (restartFlag == RESTART_FLAG) {
                return true;
            }
            if (restartFlag == DONE_FLAG) {
                return false;
            }
            throw new IllegalArgumentException("1과 2 중 하나를 입력해주세요.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return receiveMoreTicket();
        }
    }

    private long receivePoint() {
        try {
            long point = InputView.inputPoint();
            if (point < 0) {
                throw new IllegalArgumentException("없으면 0을 입력해주세요.");
            }
            return point;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return receivePoint();
        }
    }

    private void makeResult() {
        long total = TicketInformationRepository.calculatePrice(receivePoint());

        OutputView.printResult(Pay.calculateResult(InputView.inputPay(), total));
    }
}
