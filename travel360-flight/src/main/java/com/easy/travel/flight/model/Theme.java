package com.easy.travel.flight.model;

public enum Theme {
    BEACH("beach"),MOUNTAINS("mountains"),DISNEY("disney"), GAMBLING("gambling"), HISTORIC("historic"), NATIONAL_PARKS("national-parks"),
    OUTDOORS("outdoors"), ROMANTIC("romantic"), SHOPPING("shopping"), SKIING("skiing"),  THEME_PARK("theme-park"), CARIBBEAN("caribbean");

    private String value;

    Theme(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Theme get(String value) {
        for(Theme theme: values()) {
            if(theme.value.equalsIgnoreCase(value)) {
                return theme;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
