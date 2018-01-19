package io.pivotal.demo.springperson.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class FlightResponse  implements Serializable {
    @JsonProperty("LowestFare")
    private LowestFare fare;

    @JsonProperty("LowestNonStopFare")
    private LowestFare nonStopFare;

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

    public LowestFare getNonStopFare() {
        return nonStopFare;
    }

    public void setNonStopFare(LowestFare nonStopFare) {
        this.nonStopFare = nonStopFare;
    }

    public static class LowestFare  implements Serializable {

        @JsonProperty("AirlineCodes")
        public List<String> airlinesCode;

        @JsonProperty("Fare")
        public String fare;
    }

}
