package com.sanmina.basictemplate.controller;

import com.sanmina.basictemplate.component.GoogleApiComponent;
import com.sanmina.basictemplate.repository.UserRepository;
import com.sanmina.basictemplate.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class GeneralController {

    public RestTemplate restTemplate = new RestTemplate();
    public String applicationName = "BasicTemplate";
    public String plant8API = "http://plant8.sanmina.com:8080/SanmAPI";
    public String campusAPI = "http://gdl1amwebl01.sanmina.com/WebService_APPM";
    public String Q42MesAPI = "http://production.42-q.com/mes-api/";

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GoogleApiComponent googleApiComponent;

}
