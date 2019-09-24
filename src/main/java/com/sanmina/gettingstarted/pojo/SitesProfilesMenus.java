/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanmina.gettingstarted.pojo;

import java.util.List;

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
public class SitesProfilesMenus {

    private List<Site> sites;
    private List<Profile> profiles;
    private List<Menu> menus;
    private UserInfo userInfo;
    private String token;

}