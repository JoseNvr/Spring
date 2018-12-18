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
import com.sanmina.gettingstarted.pojo.LdapAuth;
import com.sanmina.gettingstarted.pojo.ResponseApi;
import com.sanmina.gettingstarted.pojo.UserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/Get/User/Info/", method = RequestMethod.GET)
    public ResponseEntity<Object> GetUserInfo(@RequestParam("user") String user,
            @RequestParam(name = "password", required = false) String password,
            @RequestParam("application") String application) {
        ResponseApi responseApi = new ResponseApi();
        try {
            if (user.contains("@sanmina.com") && password == null) { // WIth Google Account
                try {
                    UserInfo userInfo = restTemplate.getForObject(plant8API + "/GetEmployeeNumber/?employee=" + user,
                            UserInfo.class);
                    if (!userInfo.getActive()) {
                        responseApi.setMessage("This user isn't active");
                        responseApi.setCode(401);
                        return new ResponseEntity(responseApi, HttpStatus.OK);
                    }
                    responseApi.setData(userInfo);
                    responseApi.setCode(200);
                    responseApi.setMessage("Welcome " + userInfo.getName());
                    return new ResponseEntity<>(responseApi, HttpStatus.OK);
                } catch (Exception e) {
                    e.printStackTrace();
                    responseApi.setMessage("Server Error, Please report this to IT");
                    responseApi.setCode(401);
                    return new ResponseEntity(responseApi, HttpStatus.OK);
                }
            } else if (user.contains("_") && password != null) {

                String urlLdap;
                urlLdap = plant8API + "/ldapAuth/?user=" + user + "&password=" + password;
                LdapAuth ldapAuth = restTemplate.getForObject(urlLdap, LdapAuth.class);
                UserInfo userInfo = restTemplate.getForObject(plant8API + "/GetEmployeeNumber/?employee=" + user,
                        UserInfo.class);
                responseApi.setSuccess(true);
                if (ldapAuth.getSuccess() && userInfo != null) {
                    if (!userInfo.getActive()) {
                        responseApi.setMessage("This user isn't active");
                        responseApi.setCode(401);
                        return new ResponseEntity(responseApi, HttpStatus.OK);
                    }
                    responseApi.setData(userInfo);
                    responseApi.setCode(200);
                    responseApi.setMessage("Welcome ");
                    return new ResponseEntity<>(responseApi, HttpStatus.OK);

                } else {
                    responseApi.setMessage("Incorrect User Or Password");
                    responseApi.setCode(401);
                    return new ResponseEntity(responseApi, HttpStatus.OK);
                }
            } else {
                responseApi.setMessage("Incorrect User Or Password");
                responseApi.setCode(401);
                return new ResponseEntity(responseApi, HttpStatus.OK);
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            responseApi.setSuccess(Boolean.TRUE);
            responseApi.setTitle("Ciena Rework Server Error");
            responseApi.setType("danger");
            responseApi.setMessage("Server Error Report to IT");
            responseApi.setCode(500);
        }

        return new ResponseEntity<>(responseApi, HttpStatus.OK);

    }

    @RequestMapping(value = "/Get/User/Menu/", method = RequestMethod.GET)
    public ResponseEntity<Object> GetUserPlantMenu(@RequestParam("user") String user,
            @RequestParam("application") String application, @RequestParam("plant") String plant) {
        ResponseApi responseApi = new ResponseApi();
        try {
            String url;
            url = campusAPI + "/getApplicationPlantsProfilePermissionMenu?username=" + user + "&applicationIdName="
                    + application + "&orgCode=" + plant;
            ApplicationMenu applicationMenu = restTemplate.getForObject(url, ApplicationMenu.class);

            if (applicationMenu.getData().getPermissions().size() == 0) {

                responseApi.setSuccess(true);
                responseApi.setCode(200);
                responseApi.setMessage("No Autorizado");

            } else {
                responseApi.setSuccess(true);
                responseApi.setData(applicationMenu.getData());
                responseApi.setCode(200);
                responseApi.setMessage("Ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseApi.setSuccess(Boolean.TRUE);
            responseApi.setTitle("Ciena Rework Server Error");
            responseApi.setType("danger");
            responseApi.setMessage("Server Error Report to IT");
            responseApi.setCode(500);
        }
        return new ResponseEntity<>(responseApi, HttpStatus.OK);
    }
}