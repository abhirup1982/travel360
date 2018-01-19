package com.easy.travel.transport.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class TransportController {

    private String SECRET = "Whatisyoursecret";

    @Value("${travel360.search.flight.url}")
    private String search_flight_url;

    @Value("${travel360.login.url}")
    private String login_url;

    @RequestMapping(path = "/home")
    public ModelAndView home(@RequestParam(value = "token") String security_token, @RequestParam("language") String language,
                             @RequestParam(value = "subscribed") String subscribed,
                             @RequestParam(value = "lastLoginAt") String lastLoginAt)
    {
        String username = validateToken(security_token);
        ModelAndView view = new ModelAndView("home");
        view.addObject("jwt_token", security_token);
        view.addObject("username", username);
        view.addObject("language", language);
        view.addObject("subscribed", subscribed);
        view.addObject("lastLoginAt", lastLoginAt);
        return view;
    }

    private String validateToken(String token) {
/*        if(token == null || token.trim().length() == 0) {
            return "abhirup";
        } else {*/
            String username = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return username;
        /*}*/
    }

    @RequestMapping(path = "/navigate/flight", method = RequestMethod.GET)
    public void navigateToFlight(@RequestParam(value = "token") String token,
                                 @RequestParam(value = "language") String language,
                                 @RequestParam(value = "subscribed") String subscribed,
                                 @RequestParam(value = "lastLoginAt") String lastLoginAt,
                                 HttpServletResponse rsp) throws ServletException, IOException {
        rsp.sendRedirect(search_flight_url + "/search/home?token="+token+"&language="+language+"&subscribed="+subscribed+"&lastLoginAt="+lastLoginAt);
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public RedirectView navigateToLogin() throws ServletException, IOException {
        return new RedirectView(login_url + "/login?logout");
    }
}