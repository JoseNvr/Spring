package com.sanmina.gettingstarted.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

@Entity
/*
 * Si se desea utilizar Lombook es necesario eliminar Getter y Setter,
 * descomentando la dependecia del archivo pom.xml
 */
// @NoArgsConstructor
// @Getter
// @Setter
@JsonIgnoreProperties({ "password" })
@Table(catalog = "[db_SpringTest]", schema = "[dbo]")
public class UserLogin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "[id]")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "[Name]")
	private String name;

	@Column(name = "[Username]")
	private String userName;

	@Column(name = "[Password]")
	private String password;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
