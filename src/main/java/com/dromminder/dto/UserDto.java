package com.dromminder.dto;

import java.util.Date;

import com.dromminder.model.City;
import com.dromminder.model.State;
import com.dromminder.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDto {
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String role;

	private Integer stateId;
	private Integer cityId;
	private Integer collegeId;
	private String address;
	private Integer batchId;

	private StateDto stateDto;

	private CityDto cityDto;
	
	private CollegeDto collegeDto;
	
	private CollegeWiseBatchDto batchDto;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdOn;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updatedOn;
	private Boolean isActive;
	
	private String imageUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public StateDto getStateDto() {
		return stateDto;
	}

	public void setStateDto(StateDto stateDto) {
		this.stateDto = stateDto;
	}

	public CityDto getCityDto() {
		return cityDto;
	}

	public void setCityDto(CityDto cityDto) {
		this.cityDto = cityDto;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CollegeDto getCollegeDto() {
		return collegeDto;
	}

	public void setCollegeDto(CollegeDto collegeDto) {
		this.collegeDto = collegeDto;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public CollegeWiseBatchDto getBatchDto() {
		return batchDto;
	}

	public void setBatchDto(CollegeWiseBatchDto batchDto) {
		this.batchDto = batchDto;
	}

	public static User convertDtoToEntity(UserDto userDto) {
		User user = new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPhone(userDto.getPhone());
		user.setAddress(userDto.getAddress());
		user.setState(new State(userDto.getStateId()));
		user.setCity(new City(userDto.getCityId()));
		user.setBatch(userDto.getBatchId());
		user.setCollegeId(userDto.getCollegeId().longValue());
		return user;
	}

	public User convertDtoToEntity() {
		User user = new User();
		user.setId(this.id);
		user.setName(this.name);
		user.setEmail(this.email);
		user.setPhone(this.phone);
		user.setCreatedOn(this.createdOn);
		user.setUpdatedOn(this.updatedOn);
		user.setIsActive(this.isActive);
		return user;
	}

	public static UserDto convertEntityToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPhone(user.getPhone());
		userDto.setCreatedOn(user.getCreatedOn());
		userDto.setUpdatedOn(user.getUpdatedOn());
		userDto.setIsActive(user.getIsActive());
		userDto.setRole(user.getRole().name());
		userDto.setStateDto(StateDto.convertEntityToDTO(user.getState()));
		userDto.setCityDto(CityDto.convertEntityToDTO(user.getCity()));
		userDto.setImageUrl(user.getImageUrl());

		return userDto;
	}

}
