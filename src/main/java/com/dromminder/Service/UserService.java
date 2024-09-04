package com.dromminder.Service;



import com.dromminder.dto.LoginRequest;
import com.dromminder.dto.Response;
import com.dromminder.dto.UserDto;

public interface UserService {

	Response<?> login(LoginRequest loginRequest) throws Exception;

	Response<?> getById(Long userId);

	Response<?> saveUser(UserDto userDto);

	Response<?> getAllUser(Integer pageNo, int pageSize, Integer collegeId, String role);
	
	
}
