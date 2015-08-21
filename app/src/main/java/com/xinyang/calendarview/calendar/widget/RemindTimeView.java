package com.xinyang.calendarview.calendar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinyang.calendarview.R;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2015/7/29.
 */
public class RemindTimeView extends LinearLayout implements View.OnClickListener {

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT2 = new SimpleDateFormat("MM-dd HH:mm");
    private LinearLayout mStartDateView;
    private TextView mStartRemaindDate;
    private TextView mStartRemaindWeek;
    private TextView mStartRemaindTime;
    private LinearLayout mEndDateView;
    private TextView mEndRemaindDate;
    private TextView mEndRemaindWeek;
    private TextView mEndRemaindTime;

    private String startTime;
    private String endTime;

    public RemindTimeView(Context context) {
        super(context);
        initView();
    }

    public RemindTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RemindTimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.remaid_time_view, this);

        mStartDateView = (LinearLayout) view.findViewById(R.id.start_date_view);
        mStartDateView.setOnClickListener(this);
        mStartRemaindDate = (TextView) view.findViewById(R.id.start_remaind_date);
        mStartRemaindWeek = (TextView) view.findViewById(R.id.start_remaind_week);
        mStartRemaindTime = (TextView) view.findViewById(R.id.start_remaind_time);
        mEndDateView = (LinearLayout) view.findViewById(R.id.end_date_view);
        mEndDateView.setOnClickListener(this);
        mEndRemaindDate = (TextView) view.findViewById(R.id.end_remaind_date);
        mEndRemaindWeek = (TextView) view.findViewById(R.id.end_remaind_week);
        mEndRemaindTime = (TextView) view.findViewById(R.id.end_remaind_time);

        startTime = System.currentTimeMillis() + "";
        endTime = System.currentTimeMillis() + "";
        setStartTime(System.currentTimeMillis() + "");
        setEndTime(System.currentTimeMillis() + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_date_view:
                AlertDialogClass.AlertDialogWithTimePicker(getContext(), R.drawable.ic_add, "开始时间", System.currentTimeMillis() + "",
                        new AlertDialogClass.callBack() {
                            @Override
                            public void onResult(String result) {
                                setStartTime(result);
                            }
                        });
                break;
            case R.id.end_date_view:
                AlertDialogClass.AlertDialogWithTimePicker(getContext(), R.drawable.ic_add, "结束时间", System.currentTimeMillis() + "",
                        new AlertDialogClass.callBack() {
                            @Override
                            public void onResult(String result) {
                                setEndTime(result);
                            }
                        });
                break;
            default:
                break;
        }
    }

    private void setStartTime(String result) {
        startTime = result;
        Timestamp ts = new Timestamp(Long.parseLong(result));
        String formatTime = DEFAULT_DATE_FORMAT.format(ts);
        String formatTime2 = DEFAULT_DATE_FORMAT2.format(ts);
        String[] timeContent = formatTime2.split(" ");
        mStartRemaindDate.setText(timeContent[0].replace("-", "月") + "日");
        mStartRemaindTime.setText(timeContent[1]);
        try {
            Date date = DEFAULT_DATE_FORMAT.parse(formatTime);
            mStartRemaindWeek.setText(getWeekOfDate(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (Long.parseLong(endTime) < Long.parseLong(startTime)) {
            setEndTime(result);
        }
    }

    private void setEndTime(String result) {
        endTime = result;
        Timestamp ts = new Timestamp(Long.parseLong(result));
        String formatTime = DEFAULT_DATE_FORMAT.format(ts);
        String formatTime2 = DEFAULT_DATE_FORMAT2.format(ts);
        String[] timeContent = formatTime2.split(" ");
        mEndRemaindDate.setText(timeContent[0].replace("-", "月") + "日");
        mEndRemaindTime.setText(timeContent[1]);
        try {
            Date date = DEFAULT_DATE_FORMAT.parse(formatTime);
            mEndRemaindWeek.setText(getWeekOfDate(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
