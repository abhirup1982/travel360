package com.easy.travel.flight.controller;

import com.easy.travel.flight.domain.GeoCodeRS;
import com.easy.travel.flight.service.GeoLocationService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class GeoLocationController {

    @Autowired
    private GeoLocationService geoLocationService;

    @RequestMapping(path = "/flight/geoCode" , method = RequestMethod.GET)
    public String getCoordinates(@RequestParam(value = "airportCode") String airportCode) throws IOException {
        System.setProperty("proxyHost", "proxy.cognizant.com");
        System.setProperty("proxyPort", "6050");
        return geoLocationService.get(airportCode);
    }
}
