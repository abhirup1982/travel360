package com.easy.travel.flight.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightResponses {
    @JsonProperty("FareInfo")
    List<FlightResponse> flightResponses;

    public List<FlightResponse> getFlightResponses() {
        return flightResponses;
    }

    public void setFlightResponses(List<FlightResponse> flightResponses) {
        this.flightResponses = flightResponses;
    }

}
