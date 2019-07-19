/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanmina.gettingstarted.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author nestor_milian
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Menu {
    private List<MenuData> menu;
    private List<ProfileData> profiles;
    private List<Permissions> permissions;

}
