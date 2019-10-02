package com.sanmina.basictemplate.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.sanmina.basictemplate.model.UserLogin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin // (origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({ "/Spreadsheet" }) // Optional
public class SpreadSheetController extends GeneralController {

	/*
	 * Spreadsheet Test https://docs.google.com/spreadsheets/d/
	 * 1o70yF6FnKgXHp1_QVEBNkDh3ttLtMnkrgYoEVpRGrpQ/edit#gid=1651021798
	 */

	@RequestMapping(value = "/Get/Simple/Data", method = RequestMethod.GET)
	public ResponseEntity<Object> GetSimpleData() throws IOException, GeneralSecurityException {
		String spreadsheetId = "1o70yF6FnKgXHp1_QVEBNkDh3ttLtMnkrgYoEVpRGrpQ";
		String range = "ExampleGet!A1:F";
		Sheets sheets = googleApiComponent.createSheetsService();
		ValueRange valueRange = sheets.spreadsheets().values().get(spreadsheetId, range).execute();
		return new ResponseEntity<>(valueRange.getValues(), HttpStatus.OK);
	}

	@RequestMapping(value = "/Post/Simple/Data", method = RequestMethod.POST)
	public ResponseEntity<Object> PostSimpleData() throws IOException, GeneralSecurityException {
		List<UserLogin> userLoginList = userRepository.findAll();
		String spreadsheetId = "1o70yF6FnKgXHp1_QVEBNkDh3ttLtMnkrgYoEVpRGrpQ";
		String range = "ExamplePost!A2:C";
		Sheets sheets = googleApiComponent.createSheetsService();
		ValueRange valueRange = new ValueRange();
		List<List<Object>> dataValues = new ArrayList<>();
		for (UserLogin userlogin : userLoginList) {
			List<Object> data = new ArrayList<>();
			data.add(userlogin.getId());
			data.add(userlogin.getName());
			data.add(userlogin.getUsername());
			dataValues.add(data);
		}
		valueRange.setValues(dataValues);
		sheets.spreadsheets().values().update(spreadsheetId, range, valueRange).setValueInputOption("USER_ENTERED")
				.execute();
		return new ResponseEntity<>(valueRange.getValues(), HttpStatus.OK);
	}

}
