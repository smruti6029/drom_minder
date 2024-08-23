package com.dromminder.Service;



import com.dromminder.dto.LoginRequest;
import com.dromminder.dto.Response;
import com.dromminder.dto.UserDto;

public interface UserService {

	Response<?> login(LoginRequest loginRequest) throws Exception;

	Response<?> searchByName(String userName);

	Response<?> getById(long userId);

	Response<?> saveUser(UserDto userDto);

	Response<?> getAllUser(Integer hostelId, String role);
	
	
}
