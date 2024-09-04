package com.dromminder.dto;

public class PaginationRequestDto {

	private Integer pageNo;
	private Integer pageSize;
	private Integer collegeId;
	private String role;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public PaginationRequestDto(Integer pageNo, Integer pageSize, Integer collegeId, String role) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.collegeId = collegeId;
		this.role = role;
	}

}
