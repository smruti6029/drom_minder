package com.dromminder.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dromminder.Service.CityService;
import com.dromminder.dto.Response;

@RestController
@RequestMapping("/api/city/")
public class CityController {

	@Autowired
	private CityService cityService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/getby/stateId")
	public ResponseEntity<?> getCity(@RequestParam("stateId") Integer stateId) {

		if (stateId == null) {
			return new ResponseEntity<>(new Response<>("State Id Required", null, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.BAD_REQUEST);
		}
		Response<?> response = cityService.getCitybyid(stateId);

		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));
	}

	@GetMapping("/cityByName")
	public ResponseEntity<?> getCityByName(@RequestParam("cityName") String cityName) {

		if (cityName == null || cityName.trim().isEmpty()) {
			return new ResponseEntity<>(
					new Response<>("City name cannot be empty", null, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.BAD_REQUEST);
		}

		Response<?> response = cityService.getCityByName(cityName);

		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));

	}

}
