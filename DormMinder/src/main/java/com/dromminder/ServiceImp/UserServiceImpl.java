package com.dromminder.ServiceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dromminder.Service.UserService;
import com.dromminder.Util.Validation;
import com.dromminder.dto.CollegeDto;
import com.dromminder.dto.LoginRequest;
import com.dromminder.dto.LoginResponse;
import com.dromminder.dto.Response;
import com.dromminder.dto.UserDto;
import com.dromminder.enums.Role;
import com.dromminder.model.College;
import com.dromminder.model.CredentialMaster;
import com.dromminder.model.State;
import com.dromminder.model.User;
import com.dromminder.repository.CollegeRepository;
import com.dromminder.repository.CredentialMasterRepository;
import com.dromminder.repository.StateRepository;
import com.dromminder.repository.UserRepository;
import com.dromminder.security.JwtTokenUtil;
import com.dromminder.security.JwtUserDetailsService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private CredentialMasterRepository credentialMasterRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private CollegeRepository collegeRepository;

	@Override
	public Response<?> login(LoginRequest loginRequest) throws Exception {
		try {
			Response<?> validationResponse = Validation.checkLoginRequest(loginRequest);
			if (validationResponse.getResponseCode() == HttpStatus.OK.value()) {
				LoginResponse loginResponse = new LoginResponse();

				UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

				if (userDetails != null) {

					Optional<CredentialMaster> credentialMasterOptional = credentialMasterRepository
							.findByEmail(loginRequest.getEmail());

					if (credentialMasterOptional.isPresent()) {

						CredentialMaster credentialMaster = credentialMasterOptional.get();

						if (credentialMaster.passwordMatches(loginRequest.getPassword())) {
							loginResponse.setUserId(credentialMaster.getUser().getId());
							loginResponse.setEmail(credentialMaster.getEmail());
							loginResponse.setCollegeId(credentialMaster.getUser().getCollegeId());

							loginResponse.setRole(credentialMaster.getUser().getRole().name());

							loginResponse.setToken(jwtTokenUtil.generateToken(userDetails));
							return new Response<>("Login success.", loginResponse, HttpStatus.OK.value());
						}
						return new Response<>("INVALID CREDENTIALS", null, HttpStatus.BAD_REQUEST.value());
					} else {
						return new Response<>("INVALID CREDENTIALS", null, HttpStatus.BAD_REQUEST.value());
					}
				} else {
					return new Response<>("INVALID CREDENTIALS", null, HttpStatus.BAD_REQUEST.value());
				}
			} else {
				return validationResponse;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>("Something went wrong", null, HttpStatus.BAD_REQUEST.value());
		}
	}

	@Override
	public Response<?> saveUser(UserDto userDto) {

		Optional<CredentialMaster> master = userDetailsService.getUserDetails();
		if (master.isEmpty()) {
			return new Response<>("Unauthorized", null, HttpStatus.BAD_REQUEST.value());

		}
		if (master.isPresent() && !master.get().getUser().getRole().equals(Role.ADMIN)) {
			return new Response<>("Unauthorized", null, HttpStatus.BAD_REQUEST.value());

		}

		try {
			Response<?> validationResponse = Validation.checkValidUser(userDto);
			if (validationResponse.getResponseCode() == HttpStatus.OK.value()) {
				Optional<User> existingUser = userRepository.findAllByPhoneNoEmail(userDto.getPhone(),
						userDto.getEmail());
				if (existingUser.isPresent() && (existingUser.get().getEmail().equals(userDto.getEmail())
						|| existingUser.get().getPhone().equals(userDto.getPhone()))) {
					return new Response<>("Email and phone number cannot be duplicate !!!", null,
							HttpStatus.BAD_REQUEST.value());
				}

				User user = UserDto.convertDtoToEntity(userDto);

				user.setCreatedOn(new Date());
				user.setUpdatedOn(new Date());
				user.setIsActive(true);
				User userSave = userRepository.save(user);

				CredentialMaster credentialMaster = new CredentialMaster();
				credentialMaster.setUser(userSave);
				credentialMaster.setEmail(userDto.getEmail());
				credentialMaster.setCreatedOn(new Date());
				credentialMaster.setUpdatedOn(new Date());
				credentialMaster.setIsActive(true);
				credentialMaster.setPassword("1234");

				CredentialMaster savedCredentialMaster = credentialMasterRepository.save(credentialMaster);

				if (savedCredentialMaster != null) {
					return new Response<>("User Registered Succefully  !!!", null, HttpStatus.OK.value());
				} else {
					return new Response<>("Failed in User Registeration!!!", null, HttpStatus.BAD_REQUEST.value());
				}
			} else {
				return validationResponse;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>("Register user service goes wrong.", null, HttpStatus.BAD_REQUEST.value());
		}
	}

	@Override
	public Response<?> getById(long userId) {
		try {
			Optional<User> user = userRepository.findById(userId);
			if (user != null && user.isPresent()) {

				UserDto convertEntityToDto = UserDto.convertEntityToDto(user.get());
				Optional<College> findById = collegeRepository.findById(user.get().getCollegeId().intValue());
				if (findById.isPresent()) {

					convertEntityToDto.setCollegeDto(CollegeDto.fromEntity(findById.get()));

				}
				return new Response<>("success", convertEntityToDto, HttpStatus.OK.value());
			}
			return new Response<>("User not found", null, HttpStatus.BAD_REQUEST.value());
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>("Something went wrong", null, HttpStatus.BAD_REQUEST.value());
		}
	}

	@Override
	public Response<?> searchByName(String userName) {

		return null;
	}

	@Override
	public Response<?> getAllUser(Integer collegeId, String role) {

		List<UserDto> userDtos = new ArrayList<>();

		List<User> findByCollegeIdAndRole = userRepository.findByCollegeIdAndRole(collegeId, role);

		if (findByCollegeIdAndRole != null && !findByCollegeIdAndRole.isEmpty()) {
			userDtos = findByCollegeIdAndRole.stream().map(user -> UserDto.convertEntityToDto(user))
					.collect(Collectors.toList());

		}

		return new Response<>("Success", userDtos, HttpStatus.OK.value());
	}

}
