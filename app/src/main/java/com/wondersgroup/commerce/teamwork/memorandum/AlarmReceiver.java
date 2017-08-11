package com.wondersgroup.commerce.teamwork.memorandum;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 1229 on 2016/3/21.
 */
public class AlarmReceiver extends BroadcastReceiver {
    private final static String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (TAG.equals(intent.getAction())){
            Intent i = new Intent(context, AlarmActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
