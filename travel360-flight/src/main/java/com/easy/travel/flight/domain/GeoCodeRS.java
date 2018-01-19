package com.easy.travel.flight.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoCodeRS implements Serializable {
    @JsonProperty(value = "Results")
    public List<Results> results;

    public static class Results {
        @JsonProperty(value = "GeoCodeRS")
        public GeoCodeResponse geoCodeResponses;
    }

    public static class GeoCodeResponse {
        @JsonProperty(value = "status")
        public String status;
        @JsonProperty(value = "Place")
        public List<Place> places;
    }

    public static class Place {
        @JsonProperty(value = "latitude")
        public String latitude;
        @JsonProperty(value = "longitude")
        public String longitude;
        @JsonProperty(value = "Name")
        public String name;
        @JsonProperty(value = "City")
        public String city;
        @JsonProperty(value = "State")
        public String state;
        @JsonProperty(value = "Country")
        public String country;
    }
}
