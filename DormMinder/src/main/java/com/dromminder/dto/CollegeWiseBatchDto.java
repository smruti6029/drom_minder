package com.dromminder.dto;



import java.util.Date;

import com.dromminder.model.CollegeWiseBatch;

public class CollegeWiseBatchDto {

	private Integer id;
	private Integer collegeId;
	private String name;
	private Date createdOn;
	private Date updatedOn;

	public CollegeWiseBatchDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public CollegeWiseBatchDto(Integer id, Integer collegeId, String name, Date createdOn, Date updatedOn) {
		this.id = id;
		this.collegeId = collegeId;
		this.name = name;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}
	public static CollegeWiseBatchDto fromEntity(CollegeWiseBatch entity) {
		return new CollegeWiseBatchDto(entity.getId(), entity.getCollegeId(), entity.getName(), entity.getCreatedOn(),
				entity.getUpdatedOn());
	}

}
