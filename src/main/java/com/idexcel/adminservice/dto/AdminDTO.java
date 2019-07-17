package com.idexcel.adminservice.dto;

import com.idexcel.adminservice.entity.Address;

public class AdminDTO {
	
	private String name;
	private Address address;
	
	AdminDTO(){
		
	}
	
	AdminDTO(String name, Address address){
		this.name = name;
		this.address = address;
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
	
	
}
