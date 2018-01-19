package com.easy.travel.flight.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;

        final String authHeader = request.getHeader("jwt_token");
        System.out.println(">>>>>>>>> token = " + authHeader + " <<<< " + authHeader.length());
        if (authHeader.length() > 0 && ValidateToken.validate(authHeader) == null) {
            throw new ServletException("Unauthorised Request");
        }
        chain.doFilter(req, res);
    }

}