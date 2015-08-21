package com.xinyang.calendarview.calendar.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinyang.calendarview.R;
import com.xinyang.calendarview.calendar.model.RepeatEntity;

import java.util.List;

/**
 * Created by Administrator on 2015/7/30.
 */
public class RepeatAdapter extends BaseAdapter {

    private List<RepeatEntity> repeatEntities;

    public RepeatAdapter(List<RepeatEntity> repeatEntities) {
        this.repeatEntities = repeatEntities;
    }

    @Override
    public int getCount() {
        return repeatEntities.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.repeat_time_item, null);
        }
        TextView repeat_time_label = (TextView) convertView.findViewById(R.id.repeat_time_label);
        ImageView repeat_time_selected = (ImageView) convertView.findViewById(R.id.repeat_time_selected);
        repeat_time_label.setText(repeatEntities.get(position).getTimeLable());
        if (repeatEntities.get(position).isSelected()) {
            repeat_time_selected.setVisibility(View.VISIBLE);
        } else {
            repeat_time_selected.setVisibility(View.GONE);
        }
        return convertView;
    }
}
