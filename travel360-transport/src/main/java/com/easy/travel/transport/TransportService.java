package com.easy.travel.transport;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransportService {

    private static final Logger logger = LoggerFactory.getLogger(TransportService.class);

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultCarRental")
    public String getCarRentalDetails(String requestString) {
        return restTemplate.getForObject(requestString, String.class, new Object[] {});
    }

    public String defaultCarRental(String request, Throwable t) {
        t.printStackTrace();
        logger.debug("Sabre service not available. Please check");
        return "{\"OTA_VehAvailRateRS\":{\"VehAvailRSCore\": {\"VehVendorAvails\":{\"VehVendorAvail\":" +
                "[\n" +
                "  {\n" +
                "    \"RPH\": \"1\",\n" +
                "    \"VehAvailCore\": {\n" +
                "      \"RentalRate\": {\n" +
                "        \"AvailabilityStatus\": \"\",\n" +
                "        \"Vehicle\": {\n" +
                "          \"VehType\": [\n" +
                "            \"\"\n" +
                "          ]\n" +
                "        }\n" +
                "      },\n" +
                "      \"VehicleCharges\": {\n" +
                "        \"VehicleCharge\": {\n" +
                "          \"Mileage\": {\n" +
                "            \"Allowance\": \"\",\n" +
                "            \"ExtraMileageCharge\": \"\"\n" +
                "          },\n" +
                "          \"TotalCharge\": {\n" +
                "            \"Amount\": \"\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"Vendor\": {\n" +
                "      \"CompanyShortName\": \"* Please try after some time for your ride availability\"\n" +
                "    }\n" +
                "  }\n" +
                "]}}}}";
    }
}
