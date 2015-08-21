package com.xinyang.calendarview.calendar.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.xinyang.calendarview.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by xq on 15/6/11.
 */
public class AlertDialogClass {

    /**
     * list style of alertDialog
     * arrayString: the item of list
     * callbackparam: 回调时的透传值，可选
     */
    public static void AlertDialogWithTimePicker(Context context, int icon, String title, String initTime, final callBack callback) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = mInflater.inflate(R.layout.alert_dialog_time_picker, null);
        final DatePicker mDatePicker = (DatePicker) mView.findViewById(R.id.work_alert_dialog_time_picker_date);
        final TimePicker mTimePicker = (TimePicker) mView.findViewById(R.id.work_alert_dialog_time_picker_time);
        mTimePicker.setIs24HourView(true);
        if (initTime != null) {
            Calendar current = Calendar.getInstance();
            current.setTimeInMillis(Long.parseLong(initTime));

            mDatePicker.init(current.get(Calendar.YEAR), current.get(Calendar.MONTH), current.get(Calendar.DAY_OF_MONTH), null);
            mTimePicker.setCurrentHour(current.get(Calendar.HOUR_OF_DAY));
            mTimePicker.setCurrentMinute(current.get(Calendar.MINUTE));
        }

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        if (icon != 0) {
            mBuilder.setIcon(icon);
        }

        if (title != null) {
            mBuilder.setTitle(title);
        }

        mBuilder.setView(mView);

        mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Date mDate = new Date();
                String tmp = mDate.getTime() + "";

                Calendar c = Calendar.getInstance();
                c.set(mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth(), mTimePicker.getCurrentHour(),
                        mTimePicker.getCurrentMinute());
                String result = c.getTimeInMillis() + "";

//                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmm");
//                result = formatter.format(c.getTime());
//                ParsePosition pos = new ParsePosition(0);
//                mDate = formatter.parse(result,pos);
//                result = mDate.getTime() + "";
                if (callback != null) {
                    callback.onResult(result);
                }

            }
        });

        mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        mBuilder.show();
    }

    public interface listCallBack {
        public void onResult(int which, String callbackparam);
    }

    public interface callBack {
        public void onResult(String result);
    }
}
