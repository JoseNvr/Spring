package com.sanmina.basictemplate.pojo.ldap;

import lombok.Data;

@Data
public class LoginRequest {
    String user;
    String password;
    String appName;

}