/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanmina.basictemplate.pojo;

import java.util.Date;

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
public class ApplicationVersion {
    private Integer idApp;
    private Integer idAppVersion;
    private String description;
    private Date date;
    private Boolean isLastVersion;
    private Integer major;
    private Integer minor;
    private Integer bug;
    private Integer test;

}
