package com.stefanomaglione.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stefanomaglione.domain.User;
import com.stefanomaglione.repositories.UserRepository;
import com.stefanomaglione.security.CustomSecurityUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  
  @Autowired
  private UserRepository userRepo;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
    User user = userRepo.findByUsername(username);
    
    if (user == null)
      throw new UsernameNotFoundException("Username and or password was incorrect.");
    
    return new CustomSecurityUser(user);
  }
  
}
