package com.exemple.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import model.User;



public class CustomUserDetails extends User implements UserDetails{

	List<String> roles;
	
	public CustomUserDetails(User user, List<String> roles){
		super(user);
		this.roles=roles;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		String roles=StringUtils.collectionToCommaDelimitedString(this.roles);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		return super.isEnable();
	}

}
