package com.idexcel.adminservice.controller;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.idexcel.adminservice.dto.AdminDTO;
import com.idexcel.adminservice.entity.Admin;
import com.idexcel.adminservice.entity.Book;
import com.idexcel.adminservice.exception.*;
import com.idexcel.adminservice.service.AdminService;

@RestController
@RequestMapping("/api")
public class AdminServiceRestController {

	private AdminService adminService;
	
	
	@Autowired
	public AdminServiceRestController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	public AdminServiceRestController() {
	
	}
	
	@GetMapping("/")
	public String checkEndpoint() {
		return "Hello world";
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBookFromOtherRestEp(@PathVariable int id){
		return adminService.getBookFromOtherRestEp(id);
	}
	
	@GetMapping("/books")
	public ResponseEntity<List> getBooksFromOtherRestEp(){
		return adminService.getBooksFromOtherRestEp();
	}
	
	@GetMapping("/lenders")
	public List<Admin> getAdmins(){
		
		return adminService.findAllAdmins();
	}
	
	@GetMapping("/lenders/{id}")
	public Admin getAdmin(@PathVariable String id){
		
		Admin admin = adminService.findById(id);
		//ModelMapper mapper = new ModelMapper();
		if(admin == null) {
			throw new AdminNotFoundException("admin not found with id "+id);
		}
		
		return adminService.findById(id);
	}
	
	@PostMapping("/lenders")
	public void insertAdmin(@RequestBody AdminDTO admin) {
		
		
		Admin adminRes = new Admin(admin.getName(), admin.getAddress(), null, null, null, null, null);
		adminRes.setStatus("new");
		adminRes.setCreatedBy(admin.getName());
		adminRes.setUpdatedBy(admin.getName());
		adminRes.setCreatedDate(LocalDate.now());
		adminRes.setUpdatedDate(LocalDate.now());
		adminService.saveAdmin(adminRes);
		
	}
	
	@PutMapping("/lenders/{id}")
	public void updateAdmin(@PathVariable String id, @RequestBody Admin admin) {
	
		admin.setId(id);
		adminService.updateAdmin(admin);
	}
	
	@DeleteMapping("/lenders/{id}")
	public void deleteAdmin(@PathVariable String id) {
		adminService.deleteById(id);
	}
	
	@RequestMapping(method=RequestMethod.HEAD, path="/lenders/{id}")
	public ResponseEntity<String> checkHead(@PathVariable String id) {
		HttpHeaders responseHeader = new HttpHeaders();
		responseHeader.set("Admin Service header", "Contains Admin Information");
		ResponseEntity<String> response = new ResponseEntity<String>("Header Info", responseHeader, HttpStatus.OK);
		
		if(adminService.checkHead(id)) {
			System.out.println("true");
			return response;
		}
		System.out.println("false");
		
		return new ResponseEntity<String>("Header Info", responseHeader, HttpStatus.NOT_FOUND);
	}
	
	@PatchMapping("/lenders/{id}/status")
	public void updateStatus(@PathVariable String id, @RequestBody Admin admin) {
		
		
		if(!admin.getId().equals(id)) {
		throw new IdConflictException("Id does not match");	
		}
		Admin adminRes = adminService.findById(id);
		adminRes.setStatus(admin.getStatus());
		adminService.updateAdmin(adminRes);
	}
}
	