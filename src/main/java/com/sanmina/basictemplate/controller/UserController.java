package com.sanmina.basictemplate.controller;

import java.util.Optional;

import com.sanmina.basictemplate.model.UserLogin;
import com.sanmina.basictemplate.pojo.UserLoginData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin // (origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({ "/User" }) // Optional
public class UserController extends GeneralController {

	@RequestMapping(value = "/Get/Data", method = RequestMethod.GET)
	public ResponseEntity<Object> GetData(@RequestParam("username") String username) {
		Optional<UserLogin> userLogin = userRepository.findByUsername(username);
		return new ResponseEntity<>(userLogin, HttpStatus.OK);
	}

	@RequestMapping(value = "/Post/Data", method = RequestMethod.POST)
	public ResponseEntity<Object> PostData(@RequestBody UserLoginData userLoginData) {
		UserLogin userLogin = new UserLogin();
		userLogin.setName(userLoginData.getName());
		userLogin.setUsername(userLoginData.getUserName());
		userLogin.setPassword(userLoginData.getPassword());
		userRepository.save(userLogin);
		return new ResponseEntity<>(userLogin, HttpStatus.OK);
	}

}
