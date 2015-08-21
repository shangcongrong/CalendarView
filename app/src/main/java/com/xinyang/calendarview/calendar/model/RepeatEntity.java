package com.xinyang.calendarview.calendar.model;

import java.io.Serializable;

/**
 * Created by xinyang on 2015/7/30.
 */
public class RepeatEntity implements Serializable {

    private String timeLable;
    private long time;
    private boolean isSelected;

    public RepeatEntity() {

    }

    public RepeatEntity(String timeLable, long time, boolean isSelected) {
        this.timeLable = timeLable;
        this.time = time;
        this.isSelected = isSelected;
    }

    public String getTimeLable() {
        return timeLable;
    }

    public void setTimeLable(String timeLable) {
        this.timeLable = timeLable;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
