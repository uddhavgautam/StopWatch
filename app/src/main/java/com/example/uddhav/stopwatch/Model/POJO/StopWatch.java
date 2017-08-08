package com.example.uddhav.stopwatch.Model.POJO;

/**
 * Created by uddhav on 8/5/17.
 */

public class StopWatch {
    private String totalTime;
    private String user;

    public StopWatch(String totalTime, String user) {
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

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

}
