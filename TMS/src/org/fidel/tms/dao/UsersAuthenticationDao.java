package org.fidel.tms.dao;

import org.fidel.tms.model.UsersAuthentication;

public interface UsersAuthenticationDao {
	
	public boolean relateURL(UsersAuthentication usersAuth);
	
	public boolean unrelateURL(UsersAuthentication usersAuth);

}
