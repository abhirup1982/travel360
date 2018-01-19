package com.easy.travel.flight.configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidateToken {

    private static Logger _log = LoggerFactory.getLogger(ValidateToken.class);
    private static String SECRET = "Whatisyoursecret";

    public static String validate(String token) {
        try {
            /*if(token == null || token.trim().length() == 0) {
                return "abhirup";
            } else {*/
                String username = Jwts.parser().setSigningKey(SECRET)
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject();
                return username;
            /*}*/
        } catch(MalformedJwtException e) {
            _log.error("Error while validating token", e);
        }
        return null;
    }
}
