package com.example.uddhav.stopwatch.Model.POJO;

/**
 * Created by uddhav on 8/5/17.
 */

public class StopWatch {
    private long totalTime;
    private String user;

    public StopWatch(long totalTime, String user) {
        this.totalTime = totalTime;
        this.user = user;
    }

    public StopWatch() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }
}
