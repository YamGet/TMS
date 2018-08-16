package org.fidel.tms.service.impl;

import java.util.List;

import org.fidel.tms.dao.UsersAuthenticationDao;
import org.fidel.tms.model.UsersAuthentication;
import org.fidel.tms.service.UsersAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersAuthenticationServiceImpl implements UsersAuthenticationService {
	
	@Autowired
	UsersAuthenticationDao usersAuthenticationDao;

	@Override
	public List<UsersAuthentication> getUsersAuthenticationList() {
		
		return null;
	}

	@Override
	public boolean relateURL(UsersAuthentication usersAuth) {
		
		return usersAuthenticationDao.relateURL(usersAuth);
	}

	@Override
	public boolean unrelateURL(UsersAuthentication usersAuth) {
		
		return usersAuthenticationDao.unrelateURL(usersAuth);
	}

}
