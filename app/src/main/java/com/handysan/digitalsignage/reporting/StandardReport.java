package com.handysan.digitalsignage.reporting;

import android.content.Context;
import android.util.Log;

import com.handysan.digitalsignage.utils.KeyGen;

/**
 * Created by Rahul on 23/12/2016.
 */

public class StandardReport extends AbstractReport {

    public StandardReport(Context context, String dbName) {
        super(context, dbName);
    }

    @Override
    public String getUrlString() {
        Log.i("HandySan", "getUrlString: Returning new URL String");
        return "http://iamrahulap.comli.com/handysan/index.php?action=checkin&reg_code=" + new KeyGen().encode(this.context);
    }
}
