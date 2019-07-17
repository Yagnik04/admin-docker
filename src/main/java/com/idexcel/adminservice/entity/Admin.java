package com.idexcel.adminservice.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Document(collection = "admins")
public class Admin {
	
	@Id
	private String id;
	
	private String name;
	private Address address;
	private String status;
	@Indexed
	private String createdBy;
	private String updatedBy;
	private LocalDate createdDate;
	private LocalDate updatedDate;
	
	
	public Admin(String name, Address address, String status, String createdBy, String updatedBy, LocalDate createdDate,
			LocalDate updatedDate) {
		
		this.name = name;
		this.address = address;
		this.status = status;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}
 
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", address=" + address + ", status=" + status + ", createdBy="
				+ createdBy + ", updatedBy=" + updatedBy + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + "]";
	} 
}
