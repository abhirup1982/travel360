package com.easy.travel.flight.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightResponse {
    @JsonProperty("LowestFare")
    private LowestFare fare;

    @JsonProperty("LowestNonStopFare")
    private LowestFare lowestNonStopFare;

    @JsonProperty("DestinationLocation")
    private String destination;

    @JsonProperty("DepartureDateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a")
    private Date departureDt;

    @JsonProperty("ReturnDateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a")
    private Date returnDt;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureDt() {
        return departureDt;
    }

    public void setDepartureDt(Date departureDt) {
        this.departureDt = departureDt;
    }

    public Date getReturnDt() {
        return returnDt;
    }

    public void setReturnDt(Date returnDt) {
        this.returnDt = returnDt;
    }

    public LowestFare getFare() {
        return fare;
    }

    public void setFare(LowestFare fare) {
        this.fare = fare;
    }

    public LowestFare getLowestNonStopFare() {
        return lowestNonStopFare;
    }

    public void setLowestNonStopFare(LowestFare lowestNonStopFare) {
        this.lowestNonStopFare = lowestNonStopFare;
    }

    public static class LowestFare {

        @JsonProperty("AirlineCodes")
        public List<String> airlinesCode;

        @JsonProperty("Fare")
        public String fare;
    }

}
