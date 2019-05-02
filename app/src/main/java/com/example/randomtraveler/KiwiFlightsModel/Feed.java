package com.example.randomtraveler.KiwiFlightsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Feed
{
    @SerializedName("data")
    @Expose
    private Data[] data;

    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "data=" + Arrays.toString(data) +
                '}';
    }

}

