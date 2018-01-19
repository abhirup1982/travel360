package com.easy.travel.flight.service;

import com.easy.travel.flight.controller.CacheHandler;
import com.easy.travel.flight.domain.FlightResponses;
import com.easy.travel.flight.domain.FlightWrapper;
import com.easy.travel.flight.domain.FlightResponse;
import com.easy.travel.flight.model.LowFareSearchRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.List;

import static com.easy.travel.flight.controller.CacheHandler.CACHE_KEY;

@Service
public class FlightService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Value("${travel360.cache.url}")
    private String cache_url;

    @Value("${travel360.sabre.url}")
    private String sabre_url;

    private final ObjectMapper mapper = new ObjectMapper();

    public void put(FlightWrapper flightWrapper) throws Exception {
        taskExecutor.execute(new CacheHandler<FlightWrapper>(restTemplate, CACHE_KEY.FLIGHTS, cache_url, flightWrapper));
    }

    public String get(FlightWrapper flightWrapper, final LowFareSearchRequest request) throws Exception {
        FlightResponses flightResponses = null;
        System.setProperty("proxyHost", "proxy.cognizant.com");
        System.setProperty("proxyPort", "6050");
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm a"));
        ResponseEntity<String> result = restTemplate.getForEntity(cache_url + "/cache/flights/"+flightWrapper.getFlightRequestWrapper().toString(), String.class);
        if(result.getBody() != null){
            flightResponses = mapper.readValue(result.getBody(), FlightResponses.class);
        }else{
            String sabreRsp = restTemplate.getForObject(sabre_url + "/travel" + request.toRequestQuery(), String.class, new Object[] {});
            flightResponses = mapper.readValue(sabreRsp, FlightResponses.class);
            flightWrapper.setFlightResponses(flightResponses);
            if(flightResponses.getFlightResponses().size() > 0) {
                FlightResponse flightResponse = flightResponses.getFlightResponses().get(0);
                if(flightResponse.getDepartureDt() != null) {
                    put(flightWrapper);
                }
            }
        }
        if(flightResponses != null) {
            setDestination(flightWrapper.getFlightRequestWrapper().destination, flightResponses);
            return mapper.writeValueAsString(flightResponses);
        } else {
            return null;
        }
    }

    private void setDestination(String destination, FlightResponses flightResponses) {
        List<FlightResponse> list = flightResponses.getFlightResponses();
        if(list != null && list.size() > 0) {
            for(FlightResponse flightResponse : list) {
                if(flightResponse.getDepartureDt() != null && flightResponse.getDestination() == null) {
                    flightResponse.setDestination(destination);
                }
            }
        }
    }
}
