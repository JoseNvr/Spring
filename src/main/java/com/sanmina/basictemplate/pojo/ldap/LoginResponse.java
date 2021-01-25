package com.sanmina.basictemplate.pojo.ldap;

import lombok.Data;

@Data
public class LoginResponse {
    Boolean logged;
    String loginMessage;
    String serverMessage;
}