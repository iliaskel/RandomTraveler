package com.example.randomtraveler.Database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "flights_table")
public class Flight {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String going_city_from;
    private String going_city_to;
    private String going_time_departure;
    private String going_time_arrival;
    private String going_duration;
    private String going_airline;
    private String return_city_from;
    private String return_city_to;
    private String return_time_departure;
    private String return_time_arrival;
    private String return_duration;
    private String return_airline;
    private int price;


    public Flight(String going_city_from, String going_city_to, String going_time_departure, String going_time_arrival, String going_duration, String going_airline,
                  String return_city_from, String return_city_to, String return_time_departure, String return_time_arrival, String return_duration, String return_airline,
                  int price)
    {
        this.going_city_from = going_city_from;
        this.going_city_to = going_city_to;
        this.going_time_departure = going_time_departure;
        this.going_time_arrival = going_time_arrival;
        this.going_duration = going_duration;
        this.going_airline = going_airline;
        this.return_city_from = return_city_from;
        this.return_city_to = return_city_to;
        this.return_time_departure = return_time_departure;
        this.return_time_arrival = return_time_arrival;
        this.return_duration = return_duration;
        this.return_airline = return_airline;
        this.price = price;
    }

    @Ignore
    public Flight(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoing_city_from() {
        return going_city_from;
    }

    public void setGoing_city_from(String going_city_from) {
        this.going_city_from = going_city_from;
    }

    public String getGoing_city_to() {
        return going_city_to;
    }

    public void setGoing_city_to(String going_city_to) {
        this.going_city_to = going_city_to;
    }

    public String getGoing_time_departure() {
        return going_time_departure;
    }

    public void setGoing_time_departure(String going_time_departure) {
        this.going_time_departure = going_time_departure;
    }

    public String getGoing_time_arrival() {
        return going_time_arrival;
    }

    public void setGoing_time_arrival(String going_time_arrival) {
        this.going_time_arrival = going_time_arrival;
    }

    public String getGoing_duration() {
        return going_duration;
    }

    public void setGoing_duration(String going_duration) {
        this.going_duration = going_duration;
    }

    public String getReturn_city_from() {
        return return_city_from;
    }

    public void setReturn_city_from(String return_city_from) {
        this.return_city_from = return_city_from;
    }

    public String getReturn_city_to() {
        return return_city_to;
    }

    public void setReturn_city_to(String return_city_to) {
        this.return_city_to = return_city_to;
    }

    public String getReturn_time_departure() {
        return return_time_departure;
    }

    public void setReturn_time_departure(String return_time_departure) {
        this.return_time_departure = return_time_departure;
    }

    public String getReturn_time_arrival() {
        return return_time_arrival;
    }

    public void setReturn_time_arrival(String return_time_arrival) {
        this.return_time_arrival = return_time_arrival;
    }

    public String getReturn_duration() {
        return return_duration;
    }

    public void setReturn_duration(String return_duration) {
        this.return_duration = return_duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String getGoing_airline() {
        return going_airline;
    }

    public void setGoing_airline(String going_airline) {
        this.going_airline = going_airline;
    }

    public String getReturn_airline() {
        return return_airline;
    }

    public void setReturn_airline(String return_airline) {
        this.return_airline = return_airline;
    }
}
