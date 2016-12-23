package com.example.clientcomm.server;

import android.content.Context;

import com.example.clientcomm.utils.Properties;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Rahul on 13/12/2016.
 */

public class TCPServer extends Thread {

    private int _preferedPort = 11000;
    private ServerSocket _serverSocket;
    private Properties _properties;
    private IncomingDataHandler _aDataHandler;

    public TCPServer(Context aContext, String databaseName, IncomingDataHandler aDataHandler) {
        _aDataHandler = aDataHandler;
        _properties = new Properties(aContext, databaseName);
        try {
            _serverSocket = new ServerSocket(_preferedPort);

            // set current address and port for use later on.
            _properties.setCurrentIpAddress(InetAddress.getLocalHost().toString());
            _properties.setCurrentPort(_serverSocket.getLocalPort());

        } catch (IOException ioe) {
            _properties.setCurrentIpAddress(ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    @Override
    public void run() {
        Socket socket = null;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                socket = _serverSocket.accept();

                // new thread that takes over communication for a particular client
                ReceptionThread commThread = new ReceptionThread(socket, this._aDataHandler); // responsible for communicating back.
                new Thread(commThread).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
