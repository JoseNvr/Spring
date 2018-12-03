/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanmina.springbootbasictemplate.pojo;

/**
 *
 * @author nestor_milian
 */
public class ApplicationMenu {

    private Boolean success;
    private String message;
    private Menu data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Menu getData() {
        return data;
    }

    public void setData(Menu data) {
        this.data = data;
    }
}
