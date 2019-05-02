package com.example.randomtraveler.Repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.randomtraveler.Database.FlightDao;
import com.example.randomtraveler.Database.Flight;
import com.example.randomtraveler.Database.FlightDatabase;
import com.example.randomtraveler.KiwiFlightsModel.Data;
import com.example.randomtraveler.KiwiFlightsModel.Feed;
import com.example.randomtraveler.KiwiFlightsModel.Route;
import com.example.randomtraveler.RetrofitInterfaces.KiwiFlightsService;
import com.example.randomtraveler.Utils.QueryMapHelper;
import com.example.randomtraveler.Utils.StringHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlightsRepository {

    private final String TAG = FlightsRepository.class.getSimpleName();
    private final String FLIGHT_SEARCH_BASE_URL = "https://api.skypicker.com";

    private FlightDao flightDao;
    private LiveData<List<Flight>> allFlights;
    private List<Flight> listOfFlights = new ArrayList<>();
    private StringHelper stringHelper = new StringHelper();

    public FlightsRepository(){
    }

    public FlightsRepository(Application application){
        FlightDatabase flightDatabase = FlightDatabase.getInstance(application);
        flightDao = flightDatabase.flightDao();
        allFlights = flightDao.getAllFlights();
    }

    public void fetchFlights(String startingPoint, String days, String budget){

        KiwiFlightsService kiwiFlightsService = getKiwiFlightsService();

       QueryMapHelper queryMapHelper = new QueryMapHelper();
       Map<String,String> queryMap = queryMapHelper.constructQueryMapForFlights(startingPoint.toUpperCase(),days,budget);
       Log.d(TAG, "fetchFlights: ::: " + queryMap);
        Call<Feed> flights = kiwiFlightsService.getFlightResults(queryMap);

        flights.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(@NonNull Call<Feed> call, @NonNull Response<Feed> response) {
                Feed feed = response.body();
                Log.d(TAG, "onResponse: response :: " + feed);
                try{
                    //deleteAllFlights();
                    Data[] flightDataArr =feed.getData();
                    Log.d(TAG, "onResponse: flightDataArr LENGTH:: " + flightDataArr.length);
                    Log.d(TAG, "onResponse: Data ::  " + Arrays.toString(flightDataArr));

                    for (Data flightData : flightDataArr) {
                        Route[] flightRoutesArr = flightData.getRoute();
                        Flight flight = new Flight();
                        for (Route flightRoute: flightRoutesArr){
                            if(!flightRoute.getIsReturn()){
                                flight.setGoing_city_from(flightRoute.getCityFrom());
                                flight.setGoing_city_to(flightRoute.getCityTo());
                                flight.setGoing_duration(flightData.getFly_duration());


                                flight.setGoing_time_departure(StringHelper.getDateFormatMMDD(flightRoute.getdTime()));
                                Log.d(TAG, "onResponse: going time dep" + flight.getGoing_time_departure());
                                flight.setGoing_time_arrival(StringHelper.getDateFormatMMDD(flightRoute.getaTime()));

                                Log.d(TAG, "onResponse: going." + flight.getGoing_time_arrival() );
                                flight.setGoing_airline(flightRoute.getAirline());
                                flight.setPrice(flightData.getPrice());
                                Log.d(TAG, "onResponse: price : " + flightData.getPrice());
                            }
                            else{
                                flight.setReturn_city_from(flightRoute.getCityFrom());
                                flight.setReturn_city_to(flightRoute.getCityTo());
                                flight.setReturn_duration(flightData.getFly_duration());

                                flight.setReturn_time_departure(StringHelper.getDateFormatMMDD(flightRoute.getdTime()));
                                Log.d(TAG, "onResponse: " + flightRoute.getaTime());
                                flight.setReturn_time_arrival(StringHelper.getDateFormatMMDD(flightRoute.getaTime()));

                                Log.d(TAG, "onResponse: ." + flight.getReturn_time_arrival() );
                                flight.setReturn_airline(flightRoute.getAirline());
                                flight.setPrice(flightData.getPrice());
                                Log.d(TAG, "onResponse: price : " + flightData.getPrice());
                            }
                        }
                        listOfFlights.add(flight);
                    }
                    insertAllFlights(listOfFlights);
                }catch (NullPointerException e){
                    Log.d(TAG, "onResponse: NULL");
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.d(TAG, "onFailure: ERROR");
            }

        });
    }

    public void insertAllFlights(List<Flight> allFlights){
        new insertAllFlightsAsyncTask(flightDao).execute(allFlights);
    }

    public void deleteAllFlights(){
        new deleteAllFlightsAsyncTask(flightDao).execute();
    }

    public LiveData<List<Flight>> getAllFlights(){
        return allFlights;
    }



    private static class insertAllFlightsAsyncTask extends AsyncTask<List<Flight>,Void,Void>{
        private FlightDao flightDao;
        private String TAG = insertAllFlightsAsyncTask.class.getSimpleName();

        private insertAllFlightsAsyncTask(FlightDao flightDao){
            this.flightDao = flightDao;
        }

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<Flight>... lists) {
            Log.d(TAG, "doInBackground: " + lists[0].toString());
            Log.d(TAG, "doInBackground: " + String.valueOf(lists[0]==null));
            Log.d(TAG, "doInBackground: " + String.valueOf(flightDao == null));
            flightDao.insertAllFlights(lists[0]);
            return null;
        }
    }

    private static class deleteAllFlightsAsyncTask extends AsyncTask<Void,Void,Void>{
        private FlightDao flightDao;

        private deleteAllFlightsAsyncTask(FlightDao flightDao){
            this.flightDao = flightDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            flightDao.deleteAllFlights();
            return null;
        }
    }

    private KiwiFlightsService getKiwiFlightsService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FLIGHT_SEARCH_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(KiwiFlightsService.class);
    }
}
