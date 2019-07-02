package com.jdirectionsapp12345.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Share {

    public static Boolean NoInternetConnection(Context context) {

        if (isOnline(context)) {
            return false;
        } else {
            try {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();

                alertDialog.setTitle("Info");
                alertDialog.setMessage("Internet not available, your internet connectivity and try again");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);

                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {



                    }
                });

                alertDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            //   Toast.makeText(this, "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


}
