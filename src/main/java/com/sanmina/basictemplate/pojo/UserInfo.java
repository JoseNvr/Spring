/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanmina.basictemplate.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author nestor_milian
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfo {
    private String name;
    private String lastName;
    private String userName;
    private String employeeNumber;
    private String mail;
    private String number;
    private Boolean active;

   
}
