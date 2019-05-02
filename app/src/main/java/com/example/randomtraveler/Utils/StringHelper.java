package com.example.randomtraveler.Utils;


import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {

    private static String TAG = StringHelper.class.getSimpleName();

    public static boolean emailCheck(String email){

        String expression = "^[\\w\\.]+@([\\w]+\\.)+[A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }
    }

    public static String getDateFormatMMDD(String unixTimestamp){
        // convert seconds to milliseconds
        Date date = new java.util.Date(Integer.valueOf(unixTimestamp)*1000L);

        SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMM d, h:mma");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-2"));
        Log.d(TAG, "getDateFormatMMDD: " +sdf.format(date));
        return sdf.format(date);

    }


}
