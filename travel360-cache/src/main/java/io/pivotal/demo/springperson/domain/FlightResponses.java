package io.pivotal.demo.springperson.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class FlightResponses implements Serializable {
    @JsonProperty("FareInfo")
    List<FlightResponse> flightResponses;

    public List<FlightResponse> getFlightResponses() {
        return flightResponses;
    }

    public void setFlightResponses(List<FlightResponse> flightResponses) {
        this.flightResponses = flightResponses;
    }

}
