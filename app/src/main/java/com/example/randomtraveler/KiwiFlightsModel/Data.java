package com.example.randomtraveler.KiwiFlightsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Data
{

    @SerializedName("fly_duration")
    @Expose
    private String fly_duration;

    @SerializedName("nightsInDest")
    @Expose
    private String nightsInDest;

    @SerializedName("price")
    @Expose
    private int price;

    @SerializedName("transfers")
    @Expose
    private String[] transfers;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("route")
    @Expose
    private Route[] route;

    public String getFly_duration() {
        return fly_duration;
    }

    public void setFly_duration(String fly_duration) {
        this.fly_duration = fly_duration;
    }

    public String getNightsInDest() {
        return nightsInDest;
    }

    public void setNightsInDest(String nightsInDest) {
        this.nightsInDest = nightsInDest;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String[] getTransfers() {
        return transfers;
    }

    public void setTransfers(String[] transfers) {
        this.transfers = transfers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Route[] getRoute() {
        return route;
    }

    public void setRoute(Route[] route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "Data{" +
                "fly_duration='" + fly_duration + '\'' +
                ", nightsInDest='" + nightsInDest + '\'' +
                ", price='" + price + '\'' +
                ", transfers=" + Arrays.toString(transfers) +
                ", id='" + id + '\'' +
                ", route=" + Arrays.toString(route) +
                '}';
    }
}