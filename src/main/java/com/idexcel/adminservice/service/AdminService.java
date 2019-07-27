package com.idexcel.adminservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.idexcel.adminservice.entity.Admin;
import com.idexcel.adminservice.entity.Book;

public interface AdminService {

	public List<Admin> findAllAdmins();
	
	public Admin findById(String id);
	
	public void deleteById(String id);
	
	public String saveAdmin(Admin admin);
	
	public void updateAdmin(Admin admin);
	
	public boolean checkHead(String id);
	
	ResponseEntity<List> getBooksFromOtherRestEp();
	
	public ResponseEntity<Book> getBookFromOtherRestEp(int id);
}
