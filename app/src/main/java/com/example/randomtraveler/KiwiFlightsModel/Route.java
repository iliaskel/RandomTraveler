package com.example.randomtraveler.KiwiFlightsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Route
{

    @SerializedName("cityFrom")
    @Expose
    private String cityFrom;

    @SerializedName("dTimeUTC")
    @Expose
    private String dTimeUTC;

    @SerializedName("aTimeUTC")
    @Expose
    private String aTimeUTC;

    @SerializedName("flight_no")
    @Expose
    private String flight_no;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("airline")
    @Expose
    private String airline;

    @SerializedName("flyTo")
    @Expose
    private String flyTo;

    @SerializedName("return")
    @Expose
    private String isReturn;

    @SerializedName("cityTo")
    @Expose
    private String cityTo;

    @SerializedName("flyFrom")
    @Expose
    private String flyFrom;

    @SerializedName("aTime")
    @Expose
    private String aTime;

    @SerializedName("dTime")
    @Expose
    private String dTime;

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getdTimeUTC() {
        return dTimeUTC;
    }

    public void setdTimeUTC(String dTimeUTC) {
        this.dTimeUTC = dTimeUTC;
    }

    public String getaTimeUTC() {
        return aTimeUTC;
    }

    public void setaTimeUTC(String aTimeUTC) {
        this.aTimeUTC = aTimeUTC;
    }

    public String getFlight_no() {
        return flight_no;
    }

    public void setFlight_no(String flight_no) {
        this.flight_no = flight_no;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlyTo() {
        return flyTo;
    }

    public void setFlyTo(String flyTo) {
        this.flyTo = flyTo;
    }

    public boolean getIsReturn() {
        return !isReturn.equals("0");
    }

    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public String getFlyFrom() {
        return flyFrom;
    }

    public void setFlyFrom(String flyFrom) {
        this.flyFrom = flyFrom;
    }

    public String getaTime() {
        return aTime;
    }

    public void setaTime(String aTime) {
        this.aTime = aTime;
    }

    public String getdTime() {
        return dTime;
    }

    public void setdTime(String dTime) {
        this.dTime = dTime;
    }

    @Override
    public String toString() {
        return "Route{" +
                "cityFrom='" + cityFrom + '\'' +
                ", dTimeUTC='" + dTimeUTC + '\'' +
                ", aTimeUTC='" + aTimeUTC + '\'' +
                ", flight_no='" + flight_no + '\'' +
                ", price='" + price + '\'' +
                ", id='" + id + '\'' +
                ", airline='" + airline + '\'' +
                ", flyTo='" + flyTo + '\'' +
                ", isReturn='" + isReturn + '\'' +
                ", cityTo='" + cityTo + '\'' +
                ", flyFrom='" + flyFrom + '\'' +
                ", aTime='" + aTime + '\'' +
                ", dTime='" + dTime + '\'' +
                '}';
    }
}

