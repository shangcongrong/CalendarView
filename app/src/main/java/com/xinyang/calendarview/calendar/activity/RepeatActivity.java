package com.xinyang.calendarview.calendar.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xinyang.calendarview.R;
import com.xinyang.calendarview.calendar.adapter.RepeatAdapter;
import com.xinyang.calendarview.calendar.model.RepeatEntity;
import com.xinyang.calendarview.calendar.widget.TitleView;

import java.util.ArrayList;
import java.util.List;


public class RepeatActivity extends Activity {

    private TitleView mTitleView;
    private ListView mRepeatLv;
    private int timeFlag;
    private String[] repeatTimeLabel = {"永不", "每天", "每周", "每月", "每年"};
    private long[] repeatTime = {0, 86400, 604800, -1, -2};
    private String[] remindTimeLabel = {"无", "事件发生时", "5分钟前", "15分钟前", "30分钟前", "1小时前", "2小时前", "1天前", "2天前", "1周前"};
    private long[] remindTime = {-1, 0, 300, 900, 1800, 3600, 7200, 86400, 172800, 604800};
    private List<RepeatEntity> repeatEntities;
    private RepeatAdapter mRepeatAdapter;
    private RepeatEntity repeatEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat);

        initView();

        initData();
    }

    private void initView() {
        mTitleView = (TitleView) findViewById(R.id.title_view);
        mTitleView.getmTitleBarRightTv().setVisibility(View.GONE);
        mTitleView.setmTitleViewListener(new TitleView.TitleViewListener() {
            @Override
            public void finishActivity() {
                Intent repeatIntent = new Intent();
                repeatIntent.putExtra("repeat_entity", repeatEntity);
                switch (timeFlag) {
                    case 1:
                        setResult(301, repeatIntent);
                        break;
                    case 2:
                        setResult(302, repeatIntent);
                        break;
                }
                finish();
            }

            @Override
            public void saveSchedule() {

            }
        });
        mRepeatLv = (ListView) findViewById(R.id.repeat_lv);
    }

    private void initData() {
        timeFlag = getIntent().getIntExtra("select_time", 1);
        repeatEntities = new ArrayList<RepeatEntity>();
        switch (timeFlag) {
            case 1:
                mTitleView.setTitleText("重复");
                for (int i = 0; i < repeatTimeLabel.length; i++) {
                    RepeatEntity repeatEntity;
                    if (i == 0) {
                        repeatEntity = new RepeatEntity(repeatTimeLabel[0], repeatTime[0], true);
                        this.repeatEntity = repeatEntity;
                    } else {
                        repeatEntity = new RepeatEntity(repeatTimeLabel[i], repeatTime[i], false);
                    }
                    repeatEntities.add(repeatEntity);
                }
                mRepeatAdapter = new RepeatAdapter(repeatEntities);
                break;
            case 2:
                mTitleView.setTitleText("提醒");
                for (int i = 0; i < remindTimeLabel.length; i++) {
                    RepeatEntity remindEntity;
                    if (i == 0) {
                        remindEntity = new RepeatEntity(remindTimeLabel[0], remindTime[0], true);
                        this.repeatEntity = remindEntity;
                    } else {
                        remindEntity = new RepeatEntity(remindTimeLabel[i], remindTime[i], false);
                    }
                    repeatEntities.add(remindEntity);
                }
                mRepeatAdapter = new RepeatAdapter(repeatEntities);
                break;
        }
        mRepeatLv.setAdapter(mRepeatAdapter);
        mRepeatLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < repeatEntities.size(); i++) {
                    if (i == position) {
                        repeatEntities.get(i).setSelected(true);
                        repeatEntity = repeatEntities.get(i);
                    } else {
                        repeatEntities.get(i).setSelected(false);
                    }
                }
                mRepeatAdapter.notifyDataSetChanged();
            }
        });


    }


}
