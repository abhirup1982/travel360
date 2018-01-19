package com.easy.travel.flight.controller;

import com.easy.travel.flight.configuration.ValidateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ScheduledThreadPoolExecutor;


@Controller
public class FlightSearchController {

    @Value("${travel360.login.url}")
    private String login_url;

    @Value("${travel360.login.full.url}")
    private String login_full_url;

    @Value("${travel360.search.transport.url}")
    private String search_transport_url;


    @Value("${travel360.notification.url}")
    private String notification_url;

    @Autowired
    private RestTemplate restTemplate;

    private DateTimeFormatter sdf = DateTimeFormatter.ofPattern("MM-dd-yyyy'T'HH:mm");

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView home1(@RequestParam(value = "token") String security_token
            , @RequestParam(value = "newUser", required = false)  boolean newUser
            , @RequestParam(value = "lastLoginAt", required = false) String lastLoginAt
            , @RequestParam(value = "subscribed") boolean subscribed
            , @RequestParam(value = "language") String language) {
        String username = ValidateToken.validate(security_token);
        ModelAndView view = new ModelAndView("home");
        view.addObject("username", username);
        view.addObject("jwt_token", security_token);
        view.addObject("new_user", newUser);
        view.addObject("lastLoginAt", lastLoginAt);
        view.addObject("subscribed", subscribed);
        view.addObject("language", language);
        if("en".equalsIgnoreCase(language)) {
            view.addObject("pointOfSale", "US");
        } else if("cn".equalsIgnoreCase(language)){
            view.addObject("pointOfSale", "CN");
        }
        return view;
    }

    @RequestMapping(path = "/transport", method = RequestMethod.GET)
    public RedirectView navigateToTransport(@RequestParam(value = "destination") String destination, @RequestParam(value = "pickuptime") String pickupTime,
                                            @RequestParam(value = "returnTime") String returnTime,
                                            @RequestParam(value = "token") String token,
                                            @RequestParam(value = "language") String language,
                                            @RequestParam(value = "subscribed") String subscribed,
                                            @RequestParam(value = "lastLoginAt") String lastLoginAt) throws ServletException, IOException {
        return new RedirectView(search_transport_url + "/transport/home?token="+token+"&destination="+destination+"&pickuptime="+pickupTime+"&returnTime="+returnTime
                +"&language="+language+"&subscribed="+subscribed+"&lastLoginAt="+lastLoginAt);
    }

    // + "&subscribed=" + $('#subscribed').val() + "&lastLoginAt=" + $('#lastLoginAt').val()

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public RedirectView navigateToLogin(@RequestParam String userName) throws ServletException, IOException {
        restTemplate.getForEntity(notification_url + "/send/stopnotify", String.class);
        restTemplate.postForEntity(login_url + "/save/loginDt?username="+userName, "",String.class, new Object[] {});
        return new RedirectView(login_full_url + "/login?logout");
    }
}
