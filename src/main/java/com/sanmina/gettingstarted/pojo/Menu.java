/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanmina.gettingstarted.pojo;

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
public class Menu {
    private Integer idMenu;
    private String name;
    private String description;
    private String link;
    private String orden;
    private String active;
    private Integer idFatherMenu;

}
