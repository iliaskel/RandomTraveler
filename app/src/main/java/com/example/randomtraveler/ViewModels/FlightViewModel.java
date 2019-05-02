package com.example.randomtraveler.ViewModels;

import android.app.Application;

import com.example.randomtraveler.Database.Flight;
import com.example.randomtraveler.Repositories.FlightsRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class FlightViewModel extends AndroidViewModel {

    FlightsRepository flightsRepository;
    LiveData<List<Flight>> allFlights;

    public FlightViewModel(Application application) {
        super(application);
        flightsRepository = new FlightsRepository(application);
        allFlights = flightsRepository.getAllFlights();
    }

    public void insertAllFlights(List<Flight> flights){
        flightsRepository.insertAllFlights(flights);
    }

    public void deleteAllFlights(){
        flightsRepository.deleteAllFlights();
    }

    public LiveData<List<Flight>> getAllFlights(){
        return allFlights;
    }

}
