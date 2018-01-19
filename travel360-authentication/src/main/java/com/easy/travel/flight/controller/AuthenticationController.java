package com.easy.travel.flight.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
public class AuthenticationController {

    private long EXPIRATIONTIME = 1000 * 60 * 60 * 1; // 1 hour
    private String SECRET = "Whatisyoursecret";
    public static final String HEADER_STRING = "Authorization";


    @RequestMapping("/getToken/{username}")
    public void getToken(HttpServletResponse response, @PathVariable("username") String username) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .setIssuedAt(new Date()).
                        setIssuer("JWT Demonstrator App Authorization Server")
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        response.addHeader(HEADER_STRING,JWT);
    }

    @RequestMapping("/validateToken")
    public boolean isTokenValid(HttpServletRequest request) throws JsonProcessingException {
        System.out.println("************************");
        String authorization = request.getHeader(HEADER_STRING);
        if (authorization != null) {
            String token = authorization.substring(7);
            if(token != null)
            {
                String username = Jwts.parser().setSigningKey(SECRET)
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject();
                if(username != null) {
                    return true;
                }
            }
        }
        return false;
    }
}
