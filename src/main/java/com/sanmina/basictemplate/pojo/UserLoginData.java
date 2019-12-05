package com.sanmina.basictemplate.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserLoginData {

	private Integer id;
	private String name;
	private String userName;
	private String password;

}
