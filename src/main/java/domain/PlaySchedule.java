package domain;

import utils.DateTimeUtils;

import java.time.LocalDateTime;

import static utils.DateTimeUtils.format;

public class PlaySchedule {
    private final LocalDateTime startDateTime;
    private int capacity;

    public PlaySchedule(LocalDateTime startDateTime, int capacity) {
        this.startDateTime = startDateTime;
        this.capacity = capacity;
    }

    public boolean isPastTime(){
        return startDateTime.isBefore(LocalDateTime.now());
    }

    public boolean isImpossibleCapacity(int input){
        return capacity-input<0;
    }

    @Override
    public String toString() {
        return "시작시간: " + format(startDateTime) + " 예약가능인원: " + capacity + "\n";
    }

    public boolean isCapacityZero() {
        return capacity==0;
    }
}
