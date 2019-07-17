package com.idexcel.adminservice.dao;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.idexcel.adminservice.entity.Admin;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {

	
	
}
