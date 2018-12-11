package com.sanmina.gettingstarted.controller;

import com.sanmina.gettingstarted.component.GoogleApiComponent;
import com.sanmina.gettingstarted.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class GeneralController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	GoogleApiComponent googleApiComponent;

}
