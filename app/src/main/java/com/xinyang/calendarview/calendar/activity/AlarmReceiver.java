package com.xinyang.calendarview.calendar.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    public AlarmReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("single")) {
            Toast.makeText(context, "single alarm", Toast.LENGTH_SHORT).show();
            Log.d("calendar", "single alarm");
            Vibrator vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);
        } else if (intent.getAction().equals("repeat")) {
            Toast.makeText(context, "repeat alarm", Toast.LENGTH_SHORT).show();
            Log.d("calendar", "repeat alarm");
            Vibrator vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);
        } else {
            Toast.makeText(context, "repeating alarm", Toast.LENGTH_SHORT).show();
        }
    }
}
