package com.easy.travel.flight.domain;

public class FlightWrapper {
    private FlightRequestWrapper flightRequestWrapper;
    private FlightResponses flightResponses;

    public FlightRequestWrapper getFlightRequestWrapper() {
        return flightRequestWrapper;
    }

    public void setFlightRequestWrapper(FlightRequestWrapper flightRequestWrapper) {
        this.flightRequestWrapper = flightRequestWrapper;
    }

    public FlightResponses getFlightResponses() {
        return flightResponses;
    }

    public void setFlightResponses(FlightResponses flightResponses) {
        this.flightResponses = flightResponses;
    }

    public static class FlightRequestWrapper {
        public String origin;
        public String destination;
        public String departureDate;
        public String returnDate;
        public String theme;
        public String maxFare;
        public String minFare;
        public String pointOfSaleCountry;
        public Integer lengthofstay;

        @Override
        public String toString() {
            return "origin:"+this.origin+":destination:"+this.destination+":departureDate:"+this.departureDate+":returnDate:"+this.returnDate+":theme:"+this.theme
                    +":minFare:"+this.minFare+":maxFare:"+this.maxFare+":pointOfSaleCountry:"+this.pointOfSaleCountry+":lengthOfStay:"+this.lengthofstay;
        }
    }
}
