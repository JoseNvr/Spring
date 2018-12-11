package com.sanmina.gettingstarted.pojo;

// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;
/*
 * Si se desea utilizar Lombook es necesario eliminar Getter y Setter,
 * descomentando la dependecia del archivo pom.xml
 */
// @NoArgsConstructor
// @Getter
// @Setter
public class UserLoginData {

	private Integer id;
	private String name;
	private String userName;
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
