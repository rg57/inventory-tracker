package com.financial.Login;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface LoginRepo extends MongoRepository<Login, Integer>{
	
	public Login findLoginByisactive(boolean activeuser);
	
}
