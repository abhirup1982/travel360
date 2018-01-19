package com.easy.travel.flight.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LowFareSearchRequest extends BaseDomainRequest {

    @JsonProperty("origin")
    private String origin;

    @JsonProperty("destination")
    private String destination;
    
    @JsonProperty("lengthofstay")
    private Integer lengthofstay;
    
    @JsonProperty("departuredate")
    private String departuredate;
    
    @JsonProperty("minfare")
    private Integer minfare;
    
    @JsonProperty("maxfare")
    private Integer maxfare;
    
    @JsonProperty("pointofsalecountry")
    private String pointofsalecountry;

    @JsonProperty("theme")
    private Theme theme;

    @JsonProperty("returndate")
    private String returndate;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getLengthofstay() {
        return lengthofstay;
    }

    public void setLengthofstay(Integer lengthofstay) {
        this.lengthofstay = lengthofstay;
    }

    public String getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(String departuredate) {
        this.departuredate = departuredate;
    }

    public Integer getMinfare() {
        return minfare;
    }

    public void setMinfare(Integer minfare) {
        this.minfare = minfare;
    }

    public Integer getMaxfare() {
        return maxfare;
    }

    public void setMaxfare(Integer maxfare) {
        this.maxfare = maxfare;
    }

    public String getPointofsalecountry() {
        return pointofsalecountry;
    }

    public void setPointofsalecountry(String pointofsalecountry) {
        this.pointofsalecountry = pointofsalecountry;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }
}
