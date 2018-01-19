package com.easy.travel.transport.controller;

import com.easy.travel.transport.TransportService;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class TransportDetailsController {

    @Value("${sabre.url}")
    private String sabre_url;

    @Autowired
    private TransportService service;

	private static final Logger LOG = LoggerFactory.getLogger(TransportDetailsController.class);

    @RequestMapping(path = "/carrental/data", method = RequestMethod.GET)
    public String getCarAvailability(@RequestParam("pickUpDateTime")String pickUpDateTime,@RequestParam("locationCode") String locationCode,
                                     @RequestParam("returnDateTime") String returnDateTime) {
        System.setProperty("proxyHost", "proxy.cognizant.com");
        System.setProperty("proxyPort", "6050");
		String response = "";

		try {
			final String requestString = sabre_url+"/car?pickUpDateTime="+pickUpDateTime+"&returnDateTime="+returnDateTime+"&locationCode="+locationCode;
			LOG.debug("Request: " + requestString);
            response = service.getCarRentalDetails(requestString);
		} catch (HttpClientErrorException httpException) {
			if (httpException.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
				httpException.printStackTrace();
	            LOG.error("Error while getting data from webservice", httpException);
			}
		} 
		return response;
    }
}
