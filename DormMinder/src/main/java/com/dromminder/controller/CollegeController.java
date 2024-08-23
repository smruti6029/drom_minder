package com.dromminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dromminder.Service.CollegeWiseBatchService;
import com.dromminder.dto.Response;
import com.dromminder.model.CollegeWiseBatch;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/college/")
public class CollegeController {

	@Autowired
	private CollegeWiseBatchService collegeWiseBatchService;

	@PostMapping("save/batch/")
	public ResponseEntity<?> saveCollegeWiseBatch(@RequestBody CollegeWiseBatch collegeWiseBatch) {
		Response<?> response = collegeWiseBatchService.save(collegeWiseBatch);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));

	}

	@GetMapping("getall/batch/")
	public ResponseEntity<?> getAllByCollegeId(@RequestParam("collegeID") Integer collegeId) {
		Response<?> response = collegeWiseBatchService.getAllByCollegeId(collegeId);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getResponseCode()));

	}

}
