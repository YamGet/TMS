package org.fidel.tms.service;

import java.util.List;

import org.fidel.tms.model.UsersAuthentication;

public interface UsersAuthenticationService {
	
	public List<UsersAuthentication> getUsersAuthenticationList();
	
	public boolean relateURL(UsersAuthentication usersAuth);
	
	public boolean unrelateURL(UsersAuthentication usersAuth);

}
