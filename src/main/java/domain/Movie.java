package domain;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private static final char NEW_LINE = '\n';
    public static final int INDEX_FORMATTER = 1;
    public static final int MINIMUM_INDEX = 1;

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

    public boolean isEqualId(int comparedId) {
        return id==comparedId;
    }

    public void checkPastTime(int index){
        if(index< MINIMUM_INDEX || index>playSchedules.size()){
            throw new IllegalArgumentException("옳지 않은 입력입니다.");
        }
        if(playSchedules.get(index- INDEX_FORMATTER).isPastTime()){
            throw new IllegalArgumentException("이미 지난 시간표입니다.");
        }
    }
}
