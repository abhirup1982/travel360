package com.easy.travel.auth.web;

import com.easy.travel.auth.model.User;
import com.easy.travel.auth.service.UserService;
import com.easy.travel.auth.validator.UserValidator;
import com.easy.travel.auth.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Value("${travel360.authentication.url}")
    private String authentication_url;

    @Value("${travel360.search.flight.url}")
    private String search_flight_url;

    @Autowired
    private RestTemplate restTemplate;

    public static final String HEADER_STRING = "Authorization";


    @RequestMapping(value = {"/login", "/doChangeLocale"}, method = RequestMethod.GET)
    public String login(Locale locale, Model model, String error, String logout, HttpServletRequest req) {
        String language = LocaleContextHolder.getLocale().getLanguage().replaceAll("/.", "");
        req.getSession().setAttribute("language", language);
        model.addAttribute("lang", language);
        if (error != null)
            model.addAttribute("error", "invalid.username");

        if (logout != null) {
            model.addAttribute("message", "success.logout");
            SecurityContextHolder.clearContext();
        }
        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public void welcome(HttpServletRequest req, HttpServletResponse rsp) throws IOException, ServletException {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy'T'HH:mm");
        if(userName != null) {
            String token = getToken(userName);
            User user = userService.findByUsername(userName);
            String lastLoginAt = sdf.format(new Date(user.getLastLoginAt().getTime()));
            rsp.sendRedirect(search_flight_url + "/search/home?token="+token+"&lastLoginAt=" + lastLoginAt + "&subscribed=" + user.isNotified()+"&language="+req.getSession().getAttribute("language"));
        }
    }

    private String getToken(String userName) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(authentication_url + "/auth/getToken/" + userName, HttpMethod.GET, entity, String.class);
        HttpHeaders rspheaders = response.getHeaders();
        return rspheaders.get(HEADER_STRING).get(0);
    }

    @RequestMapping(value = "/save/user", method = RequestMethod.POST)
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());
        if(existingUser == null) {
            userService.save(user);
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/save/loginDt", method = RequestMethod.POST)
    public ResponseEntity<String> saveLastLogin(@RequestParam(value = "username") String userName) {
        User user = userService.findByUsername(userName);
        user.setLastLoginAt(Timestamp.valueOf(LocalDateTime.now()));
        userService.saveAndFlush(user);
        return ResponseEntity.ok().build();
    }
}
