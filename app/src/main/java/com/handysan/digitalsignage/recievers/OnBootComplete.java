package com.handysan.digitalsignage.recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.handysan.digitalsignage.services.StartOnBoot;

public class OnBootComplete extends BroadcastReceiver {
    public OnBootComplete() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
            Intent serviceIntent = new Intent(context, StartOnBoot.class);
            context.startService(serviceIntent);
        }

    }
}
