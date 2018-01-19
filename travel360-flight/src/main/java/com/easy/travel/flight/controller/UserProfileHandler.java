package com.easy.travel.flight.controller;

import com.easy.travel.flight.domain.Advertisement;
import com.easy.travel.flight.domain.Notification;
import com.easy.travel.flight.domain.UserProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class UserProfileHandler {

    @Value("${travel360.registration.url}")
    private String registration_url;

    private ObjectMapper mapper = new ObjectMapper();
    private SimpleDateFormat format = new SimpleDateFormat("MM-dd");

    @RequestMapping(path = "/flight/fetch/user/{username}")
    public String getUserProfiles(@PathVariable(name = "username") String username, HttpServletRequest req) throws IOException, ParseException {
        //return userService.getUserProfiles(username);

        List<Advertisement> advertisements = new ArrayList<>();

        UserProfile userProfile = mapper.readValue(jsonreponse, UserProfile.class);
        String currentDateStr = format.format(new Date());
        if(userProfile.anniversaryDt != null && daysBetween(format.parse(currentDateStr), userProfile.anniversaryDt) < 7) {
            Advertisement advertisement = new Advertisement();
            advertisement.image = Advertisement.Image.ROMANTIC_HOLIDAY;
            advertisement.caption = "Planning a Romantic Getaway ?";
            advertisement.link = "https://travel.usnews.com/Maui_HI/";
            advertisements.add(advertisement);
        }

        if(userProfile.pets) {
            Advertisement advertisement = new Advertisement();
            advertisement.image = Advertisement.Image.PETS_ACCESSORIES;
            advertisement.caption = "Love your Pets ?";
            advertisement.link = "https://www.apluspetsandplants.com/";
            advertisements.add(advertisement);
        }

        if(userProfile.hobbies != null) {
            List<String> interested = Arrays.asList(userProfile.hobbies.split(","));
            if(interested.contains("adventure")) {
                Advertisement advertisement = new Advertisement();
                advertisement.image = Advertisement.Image.ADVENTURE_SPORTS;
                advertisement.caption = "Thrilling Rides !!";
                advertisement.link = "https://www.sixflags.com/";
                advertisements.add(advertisement);
            }
        }

        return mapper.writeValueAsString(advertisements);
    }

    private final String jsonreponse = "{\n" +
            "\t\"id\": 1,\n" +
            "\t\"firstName\": \"sdsdsdsd\",\n" +
            "\t\"lastName\": \"\",\n" +
            "\t\"age\": \">60\",\n" +
            "\t\"pets\": true,\n" +
            "\t\"gender\": \"male\",\n" +
            "\t\"hobbies\": \"adventure\",\n" +
            "\t\"cpassword\": \"\",\n" +
            "\t\"password\": \"\",\n" +
            "\t\"emailId\": \"\",\n" +
            "\t\"anniversaryDt\": \"11-14\",\n" +
            "\t\"maritalstatus\": \"Yes\"\n" +
            "}";

    private static long daysBetween(Date one, Date two) { long difference = (one.getTime()-two.getTime())/86400000; return Math.abs(difference); }


    @RequestMapping(path = "/edit/profile")
    public void getUserProfiles(@RequestParam(name = "username") String username, HttpServletResponse rsp) throws IOException, ParseException {
        rsp.sendRedirect(registration_url + "/home?username="+username);
    }

}
