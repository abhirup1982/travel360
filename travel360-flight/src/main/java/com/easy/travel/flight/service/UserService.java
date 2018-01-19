package com.easy.travel.flight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${travel360.userprofile.url}")
    private String sabre_url;

    public String getUserProfiles(String username) {
        return restTemplate.getForEntity(sabre_url + "/user/profile/" + username, String.class).getBody();
    }
}
