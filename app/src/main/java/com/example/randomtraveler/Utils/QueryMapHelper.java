package com.example.randomtraveler.Utils;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple class made to return the QueryMap used on Retrofit calls
 */

public class QueryMapHelper {

    private String TAG = QueryMapHelper.class.getSimpleName();

   private String FLY_FROM_STRING_TAG = "fly_from";
   private String PRICE_TO_STRING_TAG = "price_to";
   private String NIGHTS_IN_DESTINATION_STRING_TAG_STRING_TAG = "nights_in_dst_from";
   private String NIGHTS_IN_DESTINATION_STRING_TAG = "nights_in_dst_to";
   private String TYPE_FLIGHTS_STRING_TAG= "type_flights";
   private String RETURN_STRING_TAG = "return";
   private String MAX_STOPOVERS_STRING_TAG = "max_stopovers";
   private String PARTNER_STRING_TAG = "partner";

    public Map<String,String> constructQueryMapForFlights(String startingPoint, String days, String budget){

            Map<String,String> kiwiQueryMap = new HashMap<>();

            kiwiQueryMap.put(FLY_FROM_STRING_TAG,startingPoint);
            kiwiQueryMap.put(PRICE_TO_STRING_TAG,budget);
            kiwiQueryMap.put(NIGHTS_IN_DESTINATION_STRING_TAG_STRING_TAG,"1");
            kiwiQueryMap.put(NIGHTS_IN_DESTINATION_STRING_TAG,days);
            kiwiQueryMap.put(TYPE_FLIGHTS_STRING_TAG,"lcc");
            kiwiQueryMap.put(RETURN_STRING_TAG,"1");
            kiwiQueryMap.put(MAX_STOPOVERS_STRING_TAG,"0");
            kiwiQueryMap.put(PARTNER_STRING_TAG,"picky");
        Log.d(TAG, "constructQueryMapForFlights: " + kiwiQueryMap
        );
        Log.d(TAG, "constructQueryMapForFlights: " + kiwiQueryMap.toString());
            return kiwiQueryMap;
    }

}
