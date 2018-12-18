/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanmina.gettingstarted.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 *
 * @author nestor_milian
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Menu {
    private List<MenuData> menu;
    private List<ProfileData> profile;
    private List<Permissions> permissions;

    public List<MenuData> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuData> menu) {
        this.menu = menu;
    }

    public List<ProfileData> getProfile() {
        return profile;
    }

    public void setProfile(List<ProfileData> profile) {
        this.profile = profile;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }

 
}
