package com.example.randomtraveler.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Flight.class}, version = 3)
public abstract class FlightDatabase extends RoomDatabase {

    private static FlightDatabase instance;

    public abstract FlightDao flightDao();

    public static synchronized FlightDatabase getInstance(Context context){

        if(instance == null){
            instance = Room
                    .databaseBuilder(context.getApplicationContext(),FlightDatabase.class,"flights_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
