package com.dromminder.Service;


import com.dromminder.dto.Response;

public interface CityService {


	
	public Response<?> getCitybyid(Integer id);

	public Response<?> getCityByName(String cityName);


}
