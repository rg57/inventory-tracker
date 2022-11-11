package com.financial.Login;

import java.net.PasswordAuthentication;
import java.util.List;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.SequenceGeneratorService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginRepo loginrepo;
	
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private SequenceGeneratorService service;
	
	
	@PostMapping("/register")
	protected ResponseEntity<Login> registerUser(@RequestBody Login userDetails)
	{
		if(userDetails.getUserName()!=null && userDetails.getUserName()!="" 
				&& userDetails.getUserPass()!=null && userDetails.getUserPass()!="")
		{		
			
//			String name = userDetails.getUserName().replaceAll("[^A-Za-z]", "");
			
			if(loginrepo!=null)
			{
				List<Login> userdata = getLoginDetails();				
				for(Login useremail : userdata)
				{
					if(useremail.getUserEmail().equals(userDetails.getUserEmail()))
					{
						
						useremail.setId(0);
						return new ResponseEntity<>(useremail,HttpStatus.OK);
					}
				}
			}
			userDetails.setId((int) service.getNextSequence("LOGIN_SEQUENCE_NAME"));
			
			String keywords = userDetails.getUserId();
			
			//Encrypting password
			passwordEncoder = new BCryptPasswordEncoder();
			String encryptpassword = passwordEncoder.encode(userDetails.getUserPass());			
			userDetails.setUserPass(encryptpassword);
			
			//creating userid
			String userid= keywords+userDetails.getId();
			
			//Setting and Saving Data
			userDetails.setUserId(userid);
			userDetails.setIsactive(false);
			loginrepo.save(userDetails);
			return new ResponseEntity<>(userDetails,HttpStatus.OK);
			
		}
		return null;
	}
	
	@PostMapping("/authenticate")
	private Login userAuthentication(@RequestBody Login userDetails)
	{
		if(loginrepo!=null && userDetails!=null) {
			
			List<Login> userdata = getLoginDetails();				
			for(Login user : userdata)
			{
				if(user.getUserId().equals(userDetails.getUserId()))
				{
					BCryptPasswordEncoder decode = new BCryptPasswordEncoder();
					boolean match = decode.matches(userDetails.getUserPass(), user.getUserPass());
					System.out.println(match);
					if(match)
					{
						user.setIsactive(true);
						loginrepo.save(user);
						return user;
					}
				}
			}
			return null;
		}
		return null;
	}
	
	@GetMapping("/isuseractive")
	private Login isuseractive() {
						
			List<Login> userslist = getLoginDetails(); 
			for(Login activeuser : userslist)
			{
				if(activeuser.isIsactive())
				{
//					activeuser.setIsactive(true);
					loginrepo.save(activeuser);
					return activeuser;
				}
			}	
		
		return null;
	}
	
	
	
	@PostMapping("/logout")
	private boolean logoutuser(@RequestBody Login logindetails)
	{
		List<Login> userslist = getLoginDetails(); 
		for(Login findactiveuser : userslist)
		{
			if(findactiveuser.getUserId().equals(logindetails.getUserId()))
			{
				findactiveuser.setIsactive(false);
				loginrepo.save(findactiveuser);
				System.out.println(findactiveuser.getUserName());
				return true;
			}
		}
		
		return false;

	}
	
	public List<Login> getLoginDetails() {
		if(loginrepo!=null)
		{
			return loginrepo.findAll();
		}
		return null;
	}
	
}
