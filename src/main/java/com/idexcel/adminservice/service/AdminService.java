package com.idexcel.adminservice.service;

import java.util.List;

import com.idexcel.adminservice.entity.Admin;

public interface AdminService {

	public List<Admin> findAllAdmins();
	
	public Admin findById(String id);
	
	public void deleteById(String id);
	
	public String saveAdmin(Admin admin);
	
	public void updateAdmin(Admin admin);
	
	public boolean checkHead(String id);
	
	
}
