package com.dromminder.ServiceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dromminder.Service.CityService;
import com.dromminder.dto.CityDto;
import com.dromminder.dto.Response;
import com.dromminder.model.City;
import com.dromminder.repository.CityRepository;

@Service
public class CityServiceImp implements CityService {

	@Autowired
	private CityRepository cityRepository;

	public Response<?> getCitybyid(Integer id) {

		List<City> getcitys = cityRepository.findByStateId(id);

		List<CityDto> citys = new ArrayList<>();

		getcitys.forEach(x -> {
			CityDto obj = new CityDto(x);
			citys.add(obj);
		});

		return new Response<List<CityDto>>("ok", citys, HttpStatus.OK.value());

	}

	public Response<?> getCityByName(String cityName) {

		try {
			List<City> findByName = cityRepository.findByName(cityName);
			return new Response<>("Success", findByName, HttpStatus.OK.value());
		} catch (Exception e) {
			return new Response<>("Error retrieving cities by name", null, HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

}
