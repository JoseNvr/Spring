/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanmina.gettingstarted.controller;

/**
 *
 * @author nestor_milian
 */
import com.sanmina.gettingstarted.pojo.ApplicationMenu;
import com.sanmina.gettingstarted.pojo.ApplicationPlants;
import com.sanmina.gettingstarted.pojo.LdapAuth;
import com.sanmina.gettingstarted.pojo.ResponseApi;
import com.sanmina.gettingstarted.pojo.UserInfo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

/**
 *
 * @author nestor_milian
 */
@CrossOrigin
@RestController
public class IntranetSecurityController extends GeneralController {

    @RequestMapping(value = "/Auth/User/", method = RequestMethod.GET)
    public ResponseEntity<Object> GetUserInfo(@RequestParam("user") String user,
            @RequestParam(name = "password", required = false) String password,
            @RequestParam(name = "plant", required = false) String plant,
            @RequestParam("application") String application) {
        ResponseApi responseApi = new ResponseApi();
        UserInfo userInfo = null;
        try {
            if (user.contains("@sanmina.com") && password == null) { // WIth Google Account
                try {
                    userInfo = restTemplate.getForObject(plant8API + "/GetEmployeeNumber/?employee=" + user,
                            UserInfo.class);
                    if (!userInfo.getActive()) {
                        responseApi.setSuccess(false);
                        responseApi.setMessage("This user isn't active");
                        responseApi.setCode(401);
                        return new ResponseEntity<>(responseApi, HttpStatus.OK);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    responseApi.setSuccess(false);
                    responseApi.setMessage("Server Error, Please report this to IT");
                    responseApi.setCode(401);
                    return new ResponseEntity<>(responseApi, HttpStatus.OK);
                }
            } else if (user.contains("_") && password != null) {
                String urlLdap;
                urlLdap = plant8API + "/ldapAuth/?user=" + user + "&password=" + password;
                LdapAuth ldapAuth = restTemplate.getForObject(urlLdap, LdapAuth.class);
                userInfo = restTemplate.getForObject(plant8API + "/GetEmployeeNumber/?employee=" + user,
                        UserInfo.class);
                if (ldapAuth.getSuccess() && userInfo != null) {
                    if (!userInfo.getActive()) {
                        responseApi.setSuccess(false);
                        responseApi.setMessage("This user isn't active");
                        responseApi.setCode(401);
                        return new ResponseEntity<>(responseApi, HttpStatus.OK);
                    }
                } else {
                    responseApi.setSuccess(false);
                    responseApi.setMessage("Incorrect User Or Password");
                    responseApi.setCode(401);
                    return new ResponseEntity<>(responseApi, HttpStatus.OK);
                }
            } else {
                responseApi.setSuccess(false);
                responseApi.setMessage("Incorrect User Or Password");
                responseApi.setCode(401);
                return new ResponseEntity<>(responseApi, HttpStatus.OK);
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            responseApi.setSuccess(false);
            responseApi.setTitle("Application Server Error");
            responseApi.setType("danger");
            responseApi.setMessage("Server Error Report to IT");
            responseApi.setCode(500);
            return new ResponseEntity<>(responseApi, HttpStatus.OK);
        }
        String url = campusAPI + "/getApplicationPlants?username=" + userInfo.getUsername() + "&applicationIdName="
                + application;
        ApplicationPlants applicationPlants;
        try {
            applicationPlants = restTemplate.getForObject(url, ApplicationPlants.class);
            if (plant == null) {
                if (applicationPlants.getSuccess()) {
                    if (applicationPlants.getData().getPlants().size() > 0) {
                        plant = applicationPlants.getData().getPlants().get(0).getPlant();
                    } else {
                        responseApi.setSuccess(false);
                        responseApi.setTitle("Usuario sin permisos para la aplicación");
                        responseApi.setType("danger");
                        responseApi.setMessage("Este usuario no cuenta con permisos (1*)");
                        responseApi.setCode(401);
                        return new ResponseEntity<>(responseApi, HttpStatus.OK);
                    }
                } else {
                    responseApi.setSuccess(false);
                    responseApi.setTitle("Usuario sin permisos para la aplicación");
                    responseApi.setType("danger");
                    responseApi.setMessage("Este usuario no cuenta con permisos (2*)");
                    responseApi.setCode(401);
                    return new ResponseEntity<>(responseApi, HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseApi.setSuccess(false);
            responseApi.setTitle("Usuario sin permisos para la aplicación");
            responseApi.setType("danger");
            responseApi.setMessage("Este usuario no cuenta con permisos (3*)");
            responseApi.setCode(401);
            return new ResponseEntity<>(responseApi, HttpStatus.OK);
        }
        url = campusAPI + "/getApplicationPlantsProfilePermissionMenu?username=" + userInfo.getUsername()
                + "&applicationIdName=" + application + "&orgCode=" + plant;
        ApplicationMenu applicationMenu;
        try {
            applicationMenu = restTemplate.getForObject(url, ApplicationMenu.class);
            if (applicationMenu.getSuccess()) {
                if (applicationMenu.getData().getPermissions().size() == 0) {
                    responseApi.setSuccess(false);
                    responseApi.setTitle("Usuario sin permisos para la aplicación");
                    responseApi.setType("danger");
                    responseApi.setMessage("Este usuario no cuenta con permisos (4*)");
                    responseApi.setCode(401);
                    return new ResponseEntity<>(responseApi, HttpStatus.OK);
                } else {
                    applicationMenu.setPlants(applicationPlants.getData());
                }
            } else {
                responseApi.setSuccess(false);
                responseApi.setTitle("Usuario sin permisos para la aplicación");
                responseApi.setType("danger");
                responseApi.setMessage("Este usuario no cuenta con permisos (5*)");
                responseApi.setCode(401);
                return new ResponseEntity<>(responseApi, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseApi.setSuccess(false);
            responseApi.setTitle("Usuario sin permisos para la aplicación");
            responseApi.setType("danger");
            responseApi.setMessage("Este usuario no cuenta con permisos (6*)");
            responseApi.setCode(401);
            return new ResponseEntity<>(responseApi, HttpStatus.OK);
        }
        String token = jwtTokenProvider.createToken(userInfo.getUsername());
        applicationMenu.setUserInfo(userInfo);
        applicationMenu.setAuthToken(token);
        responseApi.setSuccess(true);
        responseApi.setData(applicationMenu);
        responseApi.setCode(200);
        responseApi.setMessage("Welcome " + userInfo.getName());
        return new ResponseEntity<>(responseApi, HttpStatus.OK);

    }

    @RequestMapping(value = "/Get/Token/User/", method = RequestMethod.GET)
    public ResponseEntity<Object> GetUser(@AuthenticationPrincipal UserDetails userDetails) {
        ResponseApi responseApi = new ResponseApi();
        responseApi.setData(userDetails.getUsername());
        return new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

}