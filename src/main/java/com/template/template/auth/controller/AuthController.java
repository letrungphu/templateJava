package com.template.template.auth.controller;

import com.template.template.auth.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtService jwtService;

    @GetMapping("/login")
    @ResponseBody
    public HashMap<String, String> login(HttpServletRequest request) {
        System.out.println(">>> username: " + request.getParameter("username"));
        System.out.println(">>> password: " + request.getParameter("password"));
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getParameter("username"), request.getParameter("password")));
        if(auth.isAuthenticated()) {
            String token = jwtService.createToken(request.getParameter("username"));
            Date timeExpiration = jwtService.time();

            // Chuyển từ java.util.Date sang java.time.LocalDateTime
            LocalDateTime localDateTime = Instant.ofEpochMilli(timeExpiration.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dateExpiration = dtf.format(localDateTime); // Date for insert and update

            List<HashMap<String, String>> user = jwtService.getUserAndRole(request.getParameter("username"));

            HashMap<String, String> returnMap = new HashMap<String, String>();
            returnMap.put("token", token);
            returnMap.put("timeExpiration", dateExpiration);
            returnMap.put("user_name", user.get(0).get("USER_NAME").toString());
            returnMap.put("role", user.get(0).get("ROLE").toString());

            return returnMap;
        } else {
            throw new UsernameNotFoundException("Invalid user request...!");
        }
    }
}
