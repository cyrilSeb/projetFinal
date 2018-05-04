package com.exemple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exemple.model.User;
import com.exemple.repository.UserRepository;
import com.exemple.repository.UserRoleRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository UserRoleRepository;

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(arg0);
		if (user == null) {
			throw new UsernameNotFoundException("utilisateur inconnu");
		}

		return new CustomUserDetails(user, UserRoleRepository.findCustomRoleByUserName(arg0));
	}

}
