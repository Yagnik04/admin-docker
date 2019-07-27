package com.idexcel.adminservice.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.idexcel.adminservice.dao.AdminRepository;
import com.idexcel.adminservice.entity.Address;
import com.idexcel.adminservice.entity.Admin;
import com.idexcel.adminservice.entity.Book;
import com.idexcel.adminservice.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private AdminRepository adminRepository;
	
	@Autowired
	public AdminServiceImpl(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	
	@Override
	public List<Admin> findAllAdmins() {
		// TODO Auto-generated method stub
		List<Admin> admins = adminRepository.findAll(Sort.by(Direction.DESC,"createdDate"));
		
		return admins;
	}

	@Override
	public Admin findById(String id) {
		// TODO Auto-generated method stub
		Optional<Admin> result = adminRepository.findById(id);
		Admin admin = null;
		if(result.isPresent()) {
			admin = result.get();
		}
		return admin;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		adminRepository.deleteById(id);
	}

	@Override
	public String saveAdmin(Admin admin) {
		// TODO Auto-generated method stub
		
		adminRepository.insert(admin);
		
		return admin.getId();
	}

	@Override
	public void updateAdmin(Admin admin) {
		// TODO Auto-generated method stub

		adminRepository.save(admin);
	}

	@Override
	public boolean checkHead(String id) {
		return adminRepository.existsById(id);
	}

	@Override
	public ResponseEntity<Book> getBookFromOtherRestEp(int id) {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Book> book = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/todos/" +id, Book.class);
		return book;
	}
	
	public ResponseEntity<List> getBooksFromOtherRestEp(){
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List> books = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/todos", List.class);
		return books;
	}
	
	
}
