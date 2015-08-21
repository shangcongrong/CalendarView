package com.xinyang.calendarview.calendar.model;


import com.orm.SugarRecord;

/**
 * Created by Administrator on 2015/7/30.
 */
public class SingleScheduleModel extends SugarRecord<SingleScheduleModel> {

    private String flagDate;
    private String startTime;
    private String endTime;
    private String scheduleContent;
    private String scheduleRemarks;

    public SingleScheduleModel() {

    }

    public SingleScheduleModel(String flagDate, String startTime, String endTime, String scheduleContent, String scheduleRemarks) {
        this.flagDate = flagDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.scheduleContent = scheduleContent;
        this.scheduleRemarks = scheduleRemarks;
    }

    public String getFlagDate() {
        return flagDate;
    }

    public void setFlagDate(String flagDate) {
        this.flagDate = flagDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getScheduleContent() {
        return scheduleContent;
    }

    public void setScheduleContent(String scheduleContent) {
        this.scheduleContent = scheduleContent;
    }

    public String getScheduleRemarks() {
        return scheduleRemarks;
    }

    public void setScheduleRemarks(String scheduleRemarks) {
        this.scheduleRemarks = scheduleRemarks;
    }
}
