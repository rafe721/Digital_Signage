package com.example.clientcomm.server;

import android.os.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Rahul on 13/12/2016.
 */

public class ReceptionThread implements Runnable {

    /* Socket to communicate */
    private Socket _commSocket;

    private BufferedReader _input;

    private Handler _updateConversationHandler;

    private IncomingDataHandler _incomingDataHandler;

    ReceptionThread(Socket commSocket, IncomingDataHandler aHandler) {
        this._commSocket = commSocket;

        this._updateConversationHandler = new Handler();

        this._incomingDataHandler = aHandler;

        try {
            this._input = new BufferedReader(new InputStreamReader(this._commSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        StringBuilder receptionBuilder = new StringBuilder();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                receptionBuilder.append(_input.readLine()+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this._incomingDataHandler.update(receptionBuilder.toString());
    }

}
