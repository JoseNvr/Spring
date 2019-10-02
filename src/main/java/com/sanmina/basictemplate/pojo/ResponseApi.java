/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanmina.basictemplate.pojo;

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
public class ResponseApi {
    private Boolean success;
    private String message;
    private String title;
    private Integer code;
    private Object data;
    private String type;

}
