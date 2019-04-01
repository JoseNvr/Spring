package com.sanmina.gettingstarted.controller;

import com.sanmina.gettingstarted.component.GoogleApiComponent;
import com.sanmina.gettingstarted.repository.UserRepository;
import com.sanmina.gettingstarted.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class GeneralController {
    
    public RestTemplate restTemplate = new RestTemplate();
    public String plant8API = "http://plant8.sanmina.com:8080/SanmAPI";
    public String campusAPI = "http://customernet.p1.sanmina.com/IT/Isservice/api";
    public String Q42MesAPI = "http://production.42-q.com/mes-api/p2444dc1b/units/"; 

    @Autowired
    JwtTokenProvider jwtTokenProvider;
	@Autowired
	UserRepository userRepository;

	@Autowired
	GoogleApiComponent googleApiComponent;

}
