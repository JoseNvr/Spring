/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanmina.gettingstarted.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author nestor_milian
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationMenu {

    private Boolean success;
    private String message;
    private Menu data;
    private Plants plants;
    private String authToken;
    private UserInfo userInfo;

}
