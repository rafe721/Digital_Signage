package com.handysan.digitalsignage.startup;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.clientcomm.utils.Properties;
import com.handysan.digitalsignage.R;
import com.handysan.digitalsignage.reporting.StandardReport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class CommService extends Service {
    public CommService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("HandySan", "CommService Started");
        Timer timer = new Timer();
        TimerTask statusUpdateTask = new StatusUpdateTask(this, this.getString(R.string.db_name));
        Log.i("HandySan", "Timer: " + timer.toString() + " and Task: " + statusUpdateTask);
        timer.scheduleAtFixedRate(statusUpdateTask, 0L, 5*60*1000);
        Log.i("HandySan", "CommService Setup Completed");
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

}
