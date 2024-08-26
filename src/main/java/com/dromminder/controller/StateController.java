package com.dromminder.controller;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dromminder.Service.StateService;
import com.dromminder.dto.Response;

@RestController
@RequestMapping("/api/state/")
public class StateController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StateService stateService;

	@GetMapping("/all")
	public ResponseEntity<?> getAllstates() {
		Response<?> allstate = stateService.getAllstate();
		return new ResponseEntity<>(allstate, HttpStatus.valueOf(allstate.getResponseCode()));

	}

}
