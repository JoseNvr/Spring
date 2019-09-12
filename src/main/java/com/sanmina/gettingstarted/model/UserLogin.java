package com.sanmina.gettingstarted.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
/*
 * Si se desea utilizar Lombook es necesario eliminar Getter y Setter,
 * descomentando la dependecia del archivo pom.xml
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({ "password" })
@Table(catalog = "[db_SpringTest]", schema = "[dbo]")
public class UserLogin {


	@Id
	@Column(name = "[id]")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "[Name]")
	private String name;

	@Column(name = "[Username]")
	private String username;

	@Column(name = "[Password]")
	private String password;

	@Column(name = "[Plant]")
	private String plant;

	@Transient
	private List<String> roles = new ArrayList<>();

}
