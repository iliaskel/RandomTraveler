package com.example.randomtraveler.Database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


@Dao
public interface FlightDao {

    @Insert
    void insertAllFlights(List<Flight> flights);

    @Query("DELETE FROM flights_table")
    void deleteAllFlights();

    @Query("SELECT * FROM flights_table")
    LiveData<List<Flight>> getAllFlights();

}
