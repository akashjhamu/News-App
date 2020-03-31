package com.example.user.newstoday;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class networkHelper {

    public static boolean  isnewtworkAvailable(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        try {
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();

            return networkInfo!=null&&networkInfo.isConnectedOrConnecting();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
