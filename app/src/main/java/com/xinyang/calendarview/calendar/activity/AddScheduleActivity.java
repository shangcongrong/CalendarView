package com.xinyang.calendarview.calendar.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xinyang.calendarview.R;
import com.xinyang.calendarview.calendar.model.RepeatEntity;
import com.xinyang.calendarview.calendar.model.SingleScheduleModel;
import com.xinyang.calendarview.calendar.widget.FlowLayout;
import com.xinyang.calendarview.calendar.widget.RemindTimeView;
import com.xinyang.calendarview.calendar.widget.TimeUtils;
import com.xinyang.calendarview.calendar.widget.TitleView;

import java.util.Calendar;


public class AddScheduleActivity extends Activity implements View.OnClickListener {

    private TitleView mTitleView;
    private EditText mScheduleContent;
    private TextView mScheduleAccurateLocation;
    private TextView mScheduleLocation;
    private TextView mSchedulePeopleTitle;
    private FlowLayout mSchedulePeople;
    private RemindTimeView mRemindTimeView;
    private LinearLayout mScheduleRepeatView;
    private LinearLayout mScheduleRemindView;
    private TextView mScheduleRepeat;
    private TextView mScheduleRemind;
    private EditText mScheduleRemarks;
    private Button mBtnDelete;

    private AlarmManager mAlarmManager;
    private long repeatTime = 0;
    private long remindTime = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        initView();

        initData();
    }

    private void initView() {
        mTitleView = (TitleView) findViewById(R.id.title_view);
        mScheduleContent = (EditText) findViewById(R.id.schedule_content_et);
        mScheduleAccurateLocation = (TextView) findViewById(R.id.schedule_accurate_location);
        mScheduleLocation = (TextView) findViewById(R.id.schedule_location);
        mSchedulePeopleTitle = (TextView) findViewById(R.id.schedule_people_title);
        mSchedulePeople = (FlowLayout) findViewById(R.id.schedule_people);
        mRemindTimeView = (RemindTimeView) findViewById(R.id.remind_time_view);
        mScheduleRepeat = (TextView) findViewById(R.id.schedule_repeat);
        mScheduleRepeatView = (LinearLayout) findViewById(R.id.schedule_repeat_view);
        mScheduleRepeatView.setOnClickListener(this);
        mScheduleRemind = (TextView) findViewById(R.id.schedule_remind);
        mScheduleRemindView = (LinearLayout) findViewById(R.id.schedule_remind_view);
        mScheduleRemindView.setOnClickListener(this);
        mScheduleRemarks = (EditText) findViewById(R.id.schedule_remarks);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mBtnDelete.setOnClickListener(this);

        mTitleView.setTitleText("新增事件");
        mTitleView.setmTitleViewListener(new TitleView.TitleViewListener() {
            @Override
            public void finishActivity() {
                finish();
            }

            @Override
            public void saveSchedule() {
                if (remindTime != -1) {
                    if (repeatTime == 0) {
                        setSingleAlarm();
                    } else {
                        setRepeatAlarm();
                    }
                }
                saveScheduleToDb();
                finish();
            }
        });
    }

    private void initData() {
        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    private void setSingleAlarm() {
        Intent intent = new Intent(AddScheduleActivity.this, AlarmReceiver.class);
        intent.setAction("single");
        PendingIntent sender = PendingIntent.getBroadcast(AddScheduleActivity.this, 0, intent, 0);

        Log.d("calendar", "StartTime:" + TimeUtils.getTime(Long.parseLong(mRemindTimeView.getStartTime())));
        //设定一个五秒后的时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(mRemindTimeView.getStartTime()));
        calendar.add(Calendar.SECOND, -(int) remindTime);
        Log.d("calendar", "getTimeInMillis:" + TimeUtils.getTime(calendar.getTimeInMillis()) + "--currentTime:" + TimeUtils.getTime
                (System.currentTimeMillis()));
        mAlarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
        Log.d("calendar", "mRemindTimeView:" + TimeUtils.timeStamp2DefaultDateNoYear(mRemindTimeView.getStartTime()));
    }

    private void setRepeatAlarm() {
        Intent intent = new Intent(AddScheduleActivity.this, AlarmReceiver.class);
        intent.setAction("repeat");
        PendingIntent sender = PendingIntent.getBroadcast(AddScheduleActivity.this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(mRemindTimeView.getStartTime()));
        calendar.add(Calendar.SECOND, -(int) remindTime);
        mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), repeatTime * 1000, sender);
    }

    private void saveScheduleToDb(){
        Log.d("calendar", "mRemindTimeView:" + TimeUtils.timeStamp2DefaultOnlyDate(mRemindTimeView.getStartTime()));
        SingleScheduleModel singleScheduleModel = new SingleScheduleModel(TimeUtils.timeStamp2DefaultOnlyDate(mRemindTimeView
                .getStartTime()), TimeUtils.timeStamp2DefaultDateNoYear(mRemindTimeView
                .getStartTime()), TimeUtils.timeStamp2DefaultDateNoYear(mRemindTimeView.getEndTime()),
                mScheduleContent.getText().toString(), mScheduleRemarks.getText().toString());
        singleScheduleModel.save();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete:
                Intent intent = new Intent(AddScheduleActivity.this, AlarmReceiver.class);
                intent.setAction("short");
                PendingIntent sender = PendingIntent.getBroadcast(AddScheduleActivity.this, 0, intent, 0);
                mAlarmManager.cancel(sender);
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
                break;
            case R.id.schedule_repeat_view:
                Intent repeatIntent = new Intent(AddScheduleActivity.this, RepeatActivity.class);
                repeatIntent.putExtra("select_time", 1);
                startActivityForResult(repeatIntent, 301);
                break;
            case R.id.schedule_remind_view:
                Intent remindIntent = new Intent(AddScheduleActivity.this, RepeatActivity.class);
                remindIntent.putExtra("select_time", 2);
                startActivityForResult(remindIntent, 302);
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 301:
                RepeatEntity repeatEntity = (RepeatEntity) data.getSerializableExtra("repeat_entity");
                mScheduleRepeat.setText(repeatEntity.getTimeLable());
                repeatTime = repeatEntity.getTime();
                Log.d("calendar", "repeatTime:" + repeatTime);
                break;
            case 302:
                RepeatEntity remindEntity = (RepeatEntity) data.getSerializableExtra("repeat_entity");
                mScheduleRemind.setText(remindEntity.getTimeLable());
                remindTime = remindEntity.getTime();
                Log.d("calendar", "remindTime:" + remindTime);
                break;
            default:

                break;
        }
    }
}
