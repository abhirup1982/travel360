package com.easy.travel.flight.service;

import com.easy.travel.flight.controller.CacheHandler;
import com.easy.travel.flight.domain.FlightWrapper;
import com.easy.travel.flight.domain.GeoCodeRS;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class GeoLocationService {

    @Value("${travel360.sabre.url}")
    private String sabre_url;

    @Value("${travel360.cache.url}")
    private String cache_url;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    public String get(String airportCode) throws IOException {
        String coordinates = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ResponseEntity<String> result = restTemplate.getForEntity(cache_url + "/cache/coordinates/"+airportCode, String.class);
        if(result.getBody() != null){
            coordinates = result.getBody();
        } else {
            ResponseEntity<String> entity = restTemplate.getForEntity(sabre_url + "/geocode?airportCode=" + airportCode, String.class);
            GeoCodeRS geoCodeRS = mapper.readValue(entity.getBody(), GeoCodeRS.class);
            coordinates = String.format("{\"latitude\":\"%s\",\"longitude\":\"%s\",\"city\":\"%s\"}"
                    ,geoCodeRS.results.get(0).geoCodeResponses.places.get(0).latitude
                    ,geoCodeRS.results.get(0).geoCodeResponses.places.get(0).longitude
                    ,geoCodeRS.results.get(0).geoCodeResponses.places.get(0).city);
            put(airportCode, coordinates);
        }
        return coordinates;
    }

    private void put(String airportCode, String coordinates) {
        taskExecutor.execute(new CacheHandler<String>(restTemplate, CacheHandler.CACHE_KEY.GEO, cache_url, airportCode, coordinates));
    }
}
