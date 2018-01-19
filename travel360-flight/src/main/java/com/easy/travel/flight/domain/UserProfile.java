package com.easy.travel.flight.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfile {
    public String firstname;
    public String lastName;
    public Date dateOfBirth;
    public long salary;
    public String favouriteDestinations;
    public String hobbies;
    public String maritalStatus;
    public int noOfChildren;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd")
    public Date anniversaryDt;
    public String occupation;
    public Images images;
    public boolean pets;
    public String age;

    public static class Images {
        public List<String> imagePaths;
    }
}
