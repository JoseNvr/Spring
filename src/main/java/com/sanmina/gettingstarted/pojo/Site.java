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
 * @author jorge_covarrubias
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Site {

    private Integer idSite;
    private String name;
    private String codeSite;
    private String alias;
    private Boolean active;

}
