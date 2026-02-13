package com.cn.hotel.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter { //OncePerRequestFilter is type of authentication filter which executes once for every api request
    @Autowired
    private JwtAuthenticationHelper jwtHelper;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");

        //format for JWT token in Header -> Authorization
        // Bearer yeyedfdfsadf.edfsdlfdfhsyfsle.dfsjdfljdosiefe
        String username = null;
        String token = null;

        if(requestHeader!=null && requestHeader.startsWith("Bearer")){
            token  = requestHeader.split(" ")[1]; //extracting the token part

            //decoding the token and extracting the username from it
            username = jwtHelper.getUsernameFromToken(token);
            //extract user details from username
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //checking if the token is expired or not for this user
                if (!jwtHelper.isTokenExpired(token)) {
                    //we bypassed the authentication manager and provider step
                    //manually getting the user details and creating the authentication object with principles and authority
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(token /*pass userDetails as principle not token*/, null, userDetails.getAuthorities()); //creating an authentication object, credentials == null since we are not using username password, we are authenticating the jwt token, userDetails.getauthority() returns the role of the user
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // holds the HTTPRequest
                    //storing it in a local thread [SecurityContextHolder is a wrapper around it]
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        //once the token is verified call the authentication filter
        filterChain.doFilter(request, response);
    }
}

/**
 * so if parseClaimsJws() is checking the token expiration why are we manually checking it later,
 * and if in every request the security context is getting null why put a null check there too?
 *
 * 1. Separation of Concerns: getUsernameFromToken is meant to get the name. isTokenExpired is meant to check the time.
 * Explicitly calling both makes the code's intent very clear, even if the work was technically done twice.
 *
 * 2. Filter Chaining: Spring Security has a "Chain" of filters. It is possible (and common in complex apps) to have multiple authentication filters.
 *Scenario: Imagine you have an ApiKeyFilter followed by your JwtFilter. If the ApiKeyFilter already found a valid key and authenticated the request, it populates the SecurityContext.
 *
 * The Check: Your JwtFilter checks == null to see if the request is already authenticated. If it is, it skips its logic to save CPU and database resources (by not calling userDetailsService again).
 */