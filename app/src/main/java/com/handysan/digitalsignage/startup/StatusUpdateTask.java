package com.handysan.digitalsignage.startup;

import android.content.Context;
import android.util.Log;

import com.example.clientcomm.utils.Properties;
import com.handysan.digitalsignage.reporting.StandardReport;

import java.util.TimerTask;

/**
 * Created by Rahul on 23/12/2016.
 */

public class StatusUpdateTask extends TimerTask {
    String dbName;
    Context context;
    Properties properties;

    public StatusUpdateTask(Context context, String dbName) {
        this.context = context;
        this.dbName = dbName;
    }

    @Override
    public void run() {
        Log.i("HandySan", "run: StatusUpdateTack Running");
        new StandardReport(this.context, this.dbName).send();
    }
}
