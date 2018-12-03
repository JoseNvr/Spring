/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanmina.springbootbasictemplate.controller;

import org.springframework.web.client.RestTemplate;

/**
 *
 * @author nestor_milian
 */
public class GeneralClass {
    public RestTemplate restTemplate = new RestTemplate();
    public String plant8API = "http://plant8.sanmina.com:8080/SanmAPI";
    public String campusAPI = "http://customernet.p1.sanmina.com/IT/Isservice/api";
    public String Q42MesAPI = "http://production.42-q.com/mes-api/p2444dc1b/units/"; 
}
