package com.glearning.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.glearning.employee.dao.UserRepository;
import com.glearning.employee.entity.User;
import com.glearning.employee.security.MyUserDetails;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.getUserByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Could not found user!");
		}
		return new MyUserDetails(user);
	}

}
