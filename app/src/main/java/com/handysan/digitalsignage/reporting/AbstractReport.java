package com.handysan.digitalsignage.reporting;

import android.content.Context;
import android.util.Log;

import com.example.clientcomm.utils.Properties;
import com.handysan.digitalsignage.utils.KeyGen;

import java.io.IOException;
import java.io.StringBufferInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by Rahul on 23/12/2016.
 */

public abstract class AbstractReport extends HashMap<String, String> implements Reportable {

    Context context;
    String dbName;

    public AbstractReport (Context context, String dbName) {
        this.context = context;
    }

    abstract public String getUrlString();

    @Override
    public void send() {
        Log.i("HandySan", "send(): Sending New Report");
        HttpURLConnection cmsConnection;
        try
        {
            cmsConnection = (HttpURLConnection)new URL(getUrlString()).openConnection();
            cmsConnection.setRequestProperty("User-Agent", "HandySan_Client");
            cmsConnection.setRequestProperty("Connection", "close");
            cmsConnection.setConnectTimeout(6000);
            cmsConnection.connect();
            if (cmsConnection.getResponseCode() == 200)
            {
                System.out.println("Success");
                return;
            }
        }
        catch (MalformedURLException localMalformedURLException)
        {
            System.out.println("Failure");
            localMalformedURLException.printStackTrace();
            return;
        }
        catch (IOException localIOException)
        {
            localIOException.printStackTrace();
        } catch (Exception e) {
            Log.i("HandySan", "send(): Exception: " + e.getCause().getMessage());
        }
    }
}
