package com.dromminder.dto;

import java.util.Date;

import com.dromminder.model.College;

public class CollegeDto {

    private int id;
    private String name;
    private String address;
    private String colorCode;
    private Boolean isActive;
    private Date createdOn;
    private Date updatedOn;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    // Convert College entity to CollegeDto
    public static CollegeDto fromEntity(College college) {
        CollegeDto dto = new CollegeDto();
        dto.setId(college.getId());
        dto.setName(college.getName());
        dto.setAddress(college.getAddress());
        dto.setColorCode(college.getColorCode());
        dto.setIsActive(college.getIsActive());
        dto.setCreatedOn(college.getCreatedOn());
        dto.setUpdatedOn(college.getUpdatedOn());
        return dto;
    }
}

