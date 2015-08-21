package com.xinyang.calendarview.calendar.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xinyang.calendarview.R;
import com.xinyang.calendarview.calendar.model.SingleScheduleModel;

import java.util.List;

/**
 * Created by Administrator on 2015/7/29.
 */
public class ScheduleAdapter extends BaseAdapter {

    private TextView mScheduleStartTime;
    private TextView mScheduleEndTime;
    private TextView mScheduleTitle;
    private TextView mScheduleContent;
    private List<SingleScheduleModel> singleScheduleModels;

    public ScheduleAdapter(List<SingleScheduleModel> singleScheduleModels) {
        this.singleScheduleModels = singleScheduleModels;
    }

    @Override
    public int getCount() {
        return singleScheduleModels.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, null);
        }
        mScheduleStartTime = (TextView) convertView.findViewById(R.id.schedule_start_time);
        mScheduleEndTime = (TextView) convertView.findViewById(R.id.schedule_end_time);
        mScheduleTitle = (TextView) convertView.findViewById(R.id.schedule_title);
        mScheduleContent = (TextView) convertView.findViewById(R.id.schedule_content);

        Log.d("calendar", "mRemindTimeView5:" + singleScheduleModels.get(position).getStartTime());
        mScheduleStartTime.setText(singleScheduleModels.get(position).getStartTime());
        mScheduleEndTime.setText(singleScheduleModels.get(position).getEndTime());
        mScheduleTitle.setText(singleScheduleModels.get(position).getScheduleContent());
        mScheduleContent.setText(singleScheduleModels.get(position).getScheduleRemarks());

        return convertView;
    }
}
