package com.easy.travel.flight.configuration;

import com.easy.travel.flight.domain.Advertisement;
import com.easy.travel.flight.domain.Notification;
import com.easy.travel.flight.domain.UserProfile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificationBuilder {

    private static SimpleDateFormat format = new SimpleDateFormat("MM-dd");

    public static Notification getNotification(String postTo, UserProfile userProfile) throws ParseException {
        String currentDateStr = format.format(new Date());
        String title = "";
        String body = "";

        if(userProfile.anniversaryDt != null) {
            long days = daysBetween(format.parse(currentDateStr), userProfile.anniversaryDt);
            title = "Hi " + userProfile.firstname + ", Happy Anniversary !!!!!";
            if(days != 0 && days < 7) {
                body = "We just missed your anniversary. We have some great romantic holiday packages for you";
            }
        }

        if(">60".equalsIgnoreCase(userProfile.age)) {
            title = "Health Emergency";
            body = "Call 18004449999 for any emergency";
        }

        return Notification.buildNotification(postTo, title, body);
    }

    private static long daysBetween(Date one, Date two) { long difference = (one.getTime()-two.getTime())/86400000; return Math.abs(difference); }

}
