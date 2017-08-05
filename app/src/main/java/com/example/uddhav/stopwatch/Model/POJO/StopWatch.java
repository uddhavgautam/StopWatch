package com.example.uddhav.stopwatch.Model.POJO;

/**
 * Created by uddhav on 8/5/17.
 */

public class StopWatch {
    private Long value;
    private String unit;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
