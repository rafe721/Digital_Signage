package com.handysan.digitalsignage;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import com.example.clientcomm.server.TCPServer;
import com.handysan.digitalsignage.media.CampaignUpdateHandler;

/**
 * Created by Rahul on 16/12/2016.
 */

public class ServerInitialisationTask  extends AsyncTask<Context, Integer, Integer> {

    protected void onPostExecute() {
        // TODO: check this.exception
        // TODO: do something with the feed
    }

    @Override
    protected Integer doInBackground(Context... contexts) {
        TCPServer campaignUpdateRelay = new TCPServer(contexts[0], contexts[0].getString(R.string.server_name), new CampaignUpdateHandler());
        campaignUpdateRelay.start();
        return null;
    }
}