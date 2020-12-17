package domain;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    public static final int INDEX_FORMATTER = 1;
    public static final int MINIMUM_INDEX = 1;
    public static final int IMPOSSIBLE_CAPACITY = 0;
    private static final char NEW_LINE = '\n';
    private final int id;
    private final String name;
    private final int price;

    private List<PlaySchedule> playSchedules = new ArrayList<>();

    public Movie(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    void addPlaySchedule(PlaySchedule playSchedule) {
        playSchedules.add(playSchedule);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PlaySchedule playSchedule : playSchedules) {
            sb.append(playSchedule);
        }
        return id + " - " + name + ", " + price + "원" + NEW_LINE
                + sb.toString();
    }

    public String toTicketString() {
        return id + " - " + name + ", " + price + "원" + NEW_LINE;
    }

    public boolean isEqualId(int comparedId) {
        return id == comparedId;
    }

    public void checkPastTime(int index) {
        if (index < MINIMUM_INDEX || index > playSchedules.size()) {
            throw new IllegalArgumentException("옳지 않은 입력입니다.");
        }
        if (playSchedules.get(index - INDEX_FORMATTER).isPastTime()) {
            throw new IllegalArgumentException("이미 지난 시간표입니다.");
        }
    }

    public void checkMoreThanZero(int index) {
        if (playSchedules.get(index - INDEX_FORMATTER).isCapacityZero()) {
            throw new IllegalArgumentException("이미 꽉 찬 시간입니다.");
        }
    }

    public void checkPositive(int capacity) {
        if (capacity < IMPOSSIBLE_CAPACITY) {
            throw new IllegalArgumentException("옳지 않은 인원수입니다.");
        }
    }

    public void checkPossiblePeople(int scheduleId, int people) {
        if (playSchedules.get(scheduleId - INDEX_FORMATTER).isImpossibleCapacity(people)) {
            throw new IllegalArgumentException("모든 인원이 영화를 볼 수 없습니다.");
        }
    }

    public PlaySchedule getSchedule(int scheduleId) {
        return playSchedules.get(scheduleId - INDEX_FORMATTER);
    }

    public long calculatePriceWithPeople(Integer people) {
        return price * people;
    }
}
