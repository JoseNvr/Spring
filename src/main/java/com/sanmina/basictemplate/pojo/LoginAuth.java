/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanmina.basictemplate.pojo;

import lombok.Data;

/**
 *
 * @author jorge_covarrubias
 */
@Data
public class LoginAuth {
    private String user;
    private String password;
    private String plant;
    private String application;

}
