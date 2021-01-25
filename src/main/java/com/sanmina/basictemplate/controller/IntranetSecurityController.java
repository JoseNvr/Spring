/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanmina.basictemplate.controller;

import com.sanmina.basictemplate.pojo.ApplicationData;
import com.sanmina.basictemplate.pojo.ApplicationInfo;
import com.sanmina.basictemplate.pojo.ApplicationVersion;
import com.sanmina.basictemplate.pojo.LoginAuth;
import com.sanmina.basictemplate.pojo.ResponseApi;
import com.sanmina.basictemplate.pojo.UserInfo;
import com.sanmina.basictemplate.pojo.ldap.LoginRequest;
import com.sanmina.basictemplate.pojo.ldap.LoginResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

/**
 *
 * @author jorge_covarrubias
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class IntranetSecurityController extends GeneralController {

    @RequestMapping(value = "/Auth/User/", method = RequestMethod.POST)
    public ResponseEntity<Object> GetUserInfo(@RequestBody LoginAuth loginAuth) throws Exception {
        ResponseApi responseApi = new ResponseApi();
        UserInfo userInfo = null;
        LoginResponse validateAppM;
        ApplicationData applicationData;
        try {
            if (loginAuth.getUser().contains("@sanmina.com") && loginAuth.getPassword() == null) { // WIth Google
                                                                                                   // Account
                try {
                    loginAuth.setUser(loginAuth.getUser().replace("@sanmina.com", ""));
                    loginAuth.setUser(loginAuth.getUser().replace(".", "_"));
                    userInfo = restTemplate.getForObject(campusAPI + "/User/User/FindUser/" + loginAuth.getUser(),
                            UserInfo.class);
                    if (!userInfo.getActive()) {
                        responseApi.setSuccess(false);
                        responseApi.setMessage("This user isn't active");
                        responseApi.setCode(401);
                        return new ResponseEntity<>(responseApi, HttpStatus.UNAUTHORIZED);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    responseApi.setSuccess(false);
                    responseApi.setMessage("Server Error, Please report this to IT");
                    responseApi.setCode(401);
                    return new ResponseEntity<>(responseApi, HttpStatus.UNAUTHORIZED);
                }
            } else if (!loginAuth.getUser().contains("@sanmina.com") && loginAuth.getPassword() != null) {
                validateAppM = validateCredentials(loginAuth.getUser(), loginAuth.getPassword(),
                        loginAuth.getApplication());
                userInfo = restTemplate.getForObject(campusAPI + "/User/User/FindUser/" + loginAuth.getUser(),
                        UserInfo.class);
                if (validateAppM.getLogged() && userInfo != null) {
                    if (!userInfo.getActive()) {
                        responseApi.setSuccess(false);
                        responseApi.setMessage("This user isn't active");
                        responseApi.setCode(401);
                        return new ResponseEntity<>(responseApi, HttpStatus.UNAUTHORIZED);
                    }
                } else {
                    responseApi.setSuccess(false);
                    if (!validateAppM.getLoginMessage().isEmpty()) {
                        responseApi.setMessage(validateAppM.getLoginMessage());
                    } else {
                        responseApi.setMessage(validateAppM.getServerMessage());
                    }
                    responseApi.setCode(401);
                    return new ResponseEntity<>(responseApi, HttpStatus.UNAUTHORIZED);
                }
            } else {
                responseApi.setSuccess(false);
                responseApi.setMessage("Incorrect User Or Password");
                responseApi.setCode(401);
                return new ResponseEntity<>(responseApi, HttpStatus.UNAUTHORIZED);
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
        String url = campusAPI + "/ProfileApp/ProfileApp/getSiteProfileMenu/" + userInfo.getUserName() + "/"
                + loginAuth.getApplication();
        if (loginAuth.getPlant() == null) {
            try {
                applicationData = restTemplate.getForObject(url, ApplicationData.class);
                if (loginAuth.getPlant() == null) {
                    if (applicationData.getSuccess()) {
                        if (applicationData.getData().getSites().size() > 0) {
                            loginAuth.setPlant(applicationData.getData().getSites().get(0).getName());
                        } else {
                            responseApi.setSuccess(false);
                            responseApi.setTitle("Usuario sin permisos para la aplicación");
                            responseApi.setType("danger");
                            responseApi.setMessage("Este usuario no cuenta con permisos (1*)");
                            responseApi.setCode(401);
                            return new ResponseEntity<>(responseApi, HttpStatus.UNAUTHORIZED);
                        }
                    } else {
                        responseApi.setSuccess(false);
                        responseApi.setTitle("Usuario sin permisos para la aplicación");
                        responseApi.setType("danger");
                        responseApi.setMessage("Este usuario no cuenta con permisos (2*)");
                        responseApi.setCode(401);
                        return new ResponseEntity<>(responseApi, HttpStatus.UNAUTHORIZED);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                responseApi.setSuccess(false);
                responseApi.setTitle("Usuario sin permisos para la aplicación");
                responseApi.setType("danger");
                responseApi.setMessage("Este usuario no cuenta con permisos (3*)");
                responseApi.setCode(401);
                return new ResponseEntity<>(responseApi, HttpStatus.UNAUTHORIZED);
            }
        } else {
            try {
                ApplicationData applicationDataAll = restTemplate.getForObject(url, ApplicationData.class);
                url = url + "/" + loginAuth.getPlant();
                applicationData = restTemplate.getForObject(url, ApplicationData.class);
                applicationData.getData().setSites(applicationDataAll.getData().getSites());
                if (applicationData.getSuccess()) {
                    if (applicationData.getData().getProfiles().size() == 0) {
                        responseApi.setSuccess(false);
                        responseApi.setTitle("Usuario sin permisos para la aplicación");
                        responseApi.setType("danger");
                        responseApi.setMessage("Este usuario no cuenta con permisos (4*)");
                        responseApi.setCode(401);
                        return new ResponseEntity<>(responseApi, HttpStatus.UNAUTHORIZED);
                    }
                } else {
                    responseApi.setSuccess(false);
                    responseApi.setTitle("Usuario sin permisos para la aplicación");
                    responseApi.setType("danger");
                    responseApi.setMessage("Este usuario no cuenta con permisos (5*)");
                    responseApi.setCode(401);
                    return new ResponseEntity<>(responseApi, HttpStatus.UNAUTHORIZED);
                }
            } catch (Exception e) {
                e.printStackTrace();
                responseApi.setSuccess(false);
                responseApi.setTitle("Usuario sin permisos para la aplicación");
                responseApi.setType("danger");
                responseApi.setMessage("Este usuario no cuenta con permisos (6*)");
                responseApi.setCode(401);
                return new ResponseEntity<>(responseApi, HttpStatus.UNAUTHORIZED);
            }
        }
        String token = jwtTokenProvider.createToken(userInfo.getUserName(), loginAuth.getApplication(),
                loginAuth.getPlant());
        applicationData.getData().setUserInfo(userInfo);
        applicationData.getData().setToken(token);
        url = campusAPI + "/App/App/FindApp/" + loginAuth.getApplication();
        ApplicationInfo applicationInfo = restTemplate.getForObject(url, ApplicationInfo.class);
        url = campusAPI + "/App/App/getLastVersion/" + applicationInfo.getIdApp();
        ApplicationVersion applicationVersion = restTemplate.getForObject(url, ApplicationVersion.class);
        applicationData.getData()
                .setApplicationVersion(applicationVersion.getMajor() + "." + applicationVersion.getMinor() + "."
                        + applicationVersion.getBug() + "." + applicationVersion.getTest());
        responseApi.setSuccess(true);
        responseApi.setData(applicationData.getData());
        responseApi.setCode(200);
        responseApi.setMessage("Welcome " + userInfo.getName());
        return new ResponseEntity<>(responseApi, HttpStatus.OK);

    }

    @RequestMapping(value = "/Refresh/DataAndMenus/", method = RequestMethod.GET)
    public ResponseEntity<Object> RefreshDataAndMenus(@AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "plant", required = false) String plant,
            @RequestParam("application") String application) {
        ResponseApi responseApi = new ResponseApi();
        String url = campusAPI + "/ProfileApp/ProfileApp/getSiteProfileMenu/" + userDetails.getUsername() + "/"
                + application;
        ApplicationData applicationDataAll = restTemplate.getForObject(url, ApplicationData.class);
        url = url + "/" + plant;
        ApplicationData applicationData = restTemplate.getForObject(url, ApplicationData.class);
        applicationData.getData().setSites(applicationDataAll.getData().getSites());
        url = campusAPI + "/App/App/FindApp/" + application;
        ApplicationInfo applicationInfo = restTemplate.getForObject(url, ApplicationInfo.class);
        url = campusAPI + "/App/App/getLastVersion/" + applicationInfo.getIdApp();
        ApplicationVersion applicationVersion = restTemplate.getForObject(url, ApplicationVersion.class);
        applicationData.getData()
                .setApplicationVersion(applicationVersion.getMajor() + "." + applicationVersion.getMinor() + "."
                        + applicationVersion.getBug() + "." + applicationVersion.getTest());
        responseApi.setSuccess(true);
        responseApi.setData(applicationData.getData());
        responseApi.setCode(200);
        return new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    @RequestMapping(value = "/Get/Test/", method = RequestMethod.GET)
    public ResponseEntity<Object> GetUser(@AuthenticationPrincipal UserDetails userDetails) {
        ResponseApi responseApi = new ResponseApi();
        responseApi.setData(userDetails.getUsername());
        return new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    @RequestMapping(value = "/Post/Test/", method = RequestMethod.POST)
    public ResponseEntity<Object> PostUser(@AuthenticationPrincipal UserDetails userDetails) {
        ResponseApi responseApi = new ResponseApi();
        responseApi.setData(userDetails.getUsername());
        return new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    public LoginResponse validateCredentials(String user, String password, String appName) {
        String url = campusAPI + "/User/User/login";
        LoginRequest request = new LoginRequest();
        request.setUser(user);
        request.setPassword(password);
        request.setAppName(appName);
        LoginResponse response = restTemplate.postForObject(url, request, LoginResponse.class);

        return response;
    }

}