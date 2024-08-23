package com.dromminder.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;

import com.dromminder.dto.LoginRequest;
import com.dromminder.dto.Response;
import com.dromminder.dto.UserDto;
import com.dromminder.model.CollegeWiseBatch;


public class Validation {

	public static Response<?> checkLoginRequest(LoginRequest loginRequest) {
		if (loginRequest.getEmail() == null || loginRequest.getEmail().isBlank()) {
			return new Response<>("Email cannot be empty", null, HttpStatus.BAD_REQUEST.value());
		} else if (!isValidEmail(loginRequest.getEmail())) {
			return new Response<>("Invalid email format", null, HttpStatus.BAD_REQUEST.value());
		} else if (loginRequest.getPassword() == null || loginRequest.getPassword().isBlank()) {
			return new Response<>("Password cannot be empty", null, HttpStatus.BAD_REQUEST.value());
		} else {
			return new Response<>("success", null, HttpStatus.OK.value());
		}
	}

	public static boolean isValidPhoneNumber(String phoneNumber) {
		String phoneRegex = "^[0-9]{10}$";
		Pattern pattern = Pattern.compile(phoneRegex);
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}

	public static boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static Response<?> checkValidUser(UserDto userDto) {

		if (userDto.getEmail() == null || userDto.getEmail().isBlank()) {
			return new Response<>("Email cannot be empty", null, HttpStatus.BAD_REQUEST.value());
		} else if (!isValidEmail(userDto.getEmail())) {
			return new Response<>("Invalid email format", null, HttpStatus.BAD_REQUEST.value());
		} else if (userDto.getPhone() == null || userDto.getPhone().isBlank()) {
			return new Response<>("Phone Number cannot be empty", null, HttpStatus.BAD_REQUEST.value());
		} else if (userDto.getName() == null || userDto.getName().isBlank()) {
			return new Response<>("Name cannot be empty", null, HttpStatus.BAD_REQUEST.value());
		} else {
			return new Response<>("success", null, HttpStatus.OK.value());
		}
	}

	public static Response<?> vallidateCollegeWiseBatchSave(CollegeWiseBatch collegeWiseBatch) {
		if (collegeWiseBatch.getName() == null || collegeWiseBatch.getName().isBlank()) {
			return new Response<>("Name cannot be empty", null, HttpStatus.BAD_REQUEST.value());
		}

		else if (collegeWiseBatch.getCollegeId() == null) {
			return new Response<>("Provide College Id", null, HttpStatus.BAD_REQUEST.value());
		}

		return null;

	}

}
