package com.easy.travel.flight.controller;

import com.easy.travel.flight.domain.FlightWrapper;
import com.easy.travel.flight.model.LowFareSearchRequest;
import com.easy.travel.flight.model.Theme;
import com.easy.travel.flight.service.FlightService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class FlightController {

    private static final Logger LOG = LoggerFactory.getLogger(FlightController.class);

    private DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private FlightService flightService;

    @RequestMapping(path = "/findOriginAirport", method = RequestMethod.GET)
    public String getOriginAirports(@RequestParam("term") String term) {
        return "[{\n" +
                "\n" +
                "\t\"id\": \"1\",\n" +
                "\t\"label\": \"Kolkata\",\n" +
                "\t\"value\": \"CCU\"\n" +
                "},\n" +
                "{\n" +
                "\t\"id\": \"2\",\n" +
                "\t\"label\": \"Mumbai\",\n" +
                "\t\"value\": \"BOM\"\n" +
                "},\n" +
                "{\n" +
                "\t\"id\": \"3\",\n" +
                "\t\"label\": \"Chennai\",\n" +
                "\t\"value\": \"MAA\"\n" +
                "},\n" +
                "{\n" +
                "\t\"id\": \"4\",\n" +
                "\t\"label\": \"Andaman\",\n" +
                "\t\"value\": \"IXZ\"\n" +
                "}]";
    }

    @RequestMapping(path = "/findDestAirport", method = RequestMethod.GET)
    public String getDestAirports(@RequestParam("term") String term) {
        return "[{\n" +
                "\n" +
                "\t\"id\": \"1\",\n" +
                "\t\"label\": \"Kolkata\",\n" +
                "\t\"value\": \"CCU\"\n" +
                "},\n" +
                "{\n" +
                "\t\"id\": \"2\",\n" +
                "\t\"label\": \"Mumbai\",\n" +
                "\t\"value\": \"BOM\"\n" +
                "},\n" +
                "{\n" +
                "\t\"id\": \"3\",\n" +
                "\t\"label\": \"Chennai\",\n" +
                "\t\"value\": \"MAA\"\n" +
                "},\n" +
                "{\n" +
                "\t\"id\": \"4\",\n" +
                "\t\"label\": \"Andaman\",\n" +
                "\t\"value\": \"IXZ\"\n" +
                "}]";
    }

    @RequestMapping(path = "/searchFlights", method = RequestMethod.GET)
    public String searchFlights(@RequestParam("term") String term) {
        return "[{\n" +
                "\n" +
                "\t\"id\": \"Dromas ardeola\",\n" +
                "\t\"label\": \"Crab-Plover\",\n" +
                "\t\"value\": \"Crab-Plover\"\n" +
                "},\n" +
                "{\n" +
                "\t\"id\": \"Larus sabini\",\n" +
                "\t\"label\": \"Sabine`s Gull\",\n" +
                "\t\"value\": \"Sabine`s Gull\"\n" +
                "},\n" +
                "{\n" +
                "\t\"id\": \"Vanellus gregarius\",\n" +
                "\t\"label\": \"Sociable Lapwing\",\n" +
                "\t\"value\": \"Sociable Lapwing\"\n" +
                "},\n" +
                "{\n" +
                "\t\"id\": \"Oenanthe isabellina\",\n" +
                "\t\"label\": \"Isabelline Wheatear\",\n" +
                "\t\"value\": \"Isabelline Wheatear\"\n" +
                "}]";
    }

    @RequestMapping(path = "/flight/data", method = RequestMethod.GET)
    public String searchFlights(@RequestParam(value = "theme", required = false) String theme, @RequestParam(value = "origin", required = false) String origin,
                                   @RequestParam(value = "departuredate", required = false) String departureDt, @RequestParam(value = "returndate", required = false) String returnDt,
                                   @RequestParam(value = "maxfare", required = false) Integer maxFare, @RequestParam(value = "minfare", required = false) Integer minFare,
                                   @RequestParam(value = "destination", required = false) String destination, @RequestParam(value = "pointofsale", required = false) String pointOfSaleCountry,
                                   @RequestParam(value = "nonstop", required = false) boolean nonStop) throws IOException {
        String response = null;

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        System.setProperty("proxyHost", "proxy.cognizant.com");
        System.setProperty("proxyPort", "6050");
        try {
            final Theme reqTheme = Theme.get(theme);
            final LowFareSearchRequest request = new LowFareSearchRequest();
            request.setOrigin(origin);
            request.setDestination(destination);
            request.setDeparturedate(departureDt);
            request.setReturndate(returnDt);

            if(returnDt != null && returnDt.length() > 0) {
                request.setLengthofstay(Math.abs(LocalDate.parse(returnDt, sdf).until(LocalDate.parse(departureDt, sdf)).getDays()));
            } else if(theme == null || theme.length() == 0) {
                request.setLengthofstay(15);
            }

            if(reqTheme != null) {
                request.setTheme(reqTheme);
            }
            request.setMaxfare(maxFare);
            request.setMinfare(minFare);
            request.setPointofsalecountry(pointOfSaleCountry);

            FlightWrapper flightWrapper = new FlightWrapper();
            FlightWrapper.FlightRequestWrapper wrapper = new FlightWrapper.FlightRequestWrapper();
            wrapper.origin = origin;
            wrapper.destination = destination;
            wrapper.departureDate = departureDt;
            wrapper.returnDate = returnDt;
            if(returnDt != null && returnDt.length() > 0) {
                wrapper.lengthofstay = Math.abs(LocalDate.parse(returnDt, sdf).until(LocalDate.parse(departureDt, sdf)).getDays());
            } else if(theme == null || theme.length() == 0) {
                wrapper.lengthofstay = 15;
            }
            wrapper.theme = theme;
            wrapper.maxFare = String.valueOf(maxFare);
            wrapper.minFare = String.valueOf(minFare);
            wrapper.pointOfSaleCountry = pointOfSaleCountry;
            flightWrapper.setFlightRequestWrapper(wrapper);
            response = flightService.get(flightWrapper, request);
        } catch(Exception cacheException) {
            cacheException.printStackTrace();
            LOG.error("Error while getting data from cache", cacheException);
        }

        return response;
    }
}
