/**
 * 
 */
package org.fidel.tms.service.impl;

import java.util.List;

import org.fidel.tms.dao.UserDao;
import org.fidel.tms.model.Users;
import org.fidel.tms.service.UserService;
import org.fidel.tms.utils.PasswordEncription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	public List<Users> getAllUsersList() {
		
		return userDao.getUserList();
	}

	@Override
	public int getHibernateUserCount() {
		
		return userDao.getHibernateUserCount();
	}

	@Override
	public boolean validateUser(String username, String password) {
		
//		if(username.equals("tsuperadmin") && password.equals("pass*123")){
//			
//			return true;
//		}
		
		String dec_password = "";
		
		try {
			dec_password = PasswordEncription.encrypt(password);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return userDao.validateUser(username, dec_password);
	}
	
	@Override
	public boolean checkUserStatus(String username, String password) {
		
		String dec_password = "";
		
		try {
			dec_password = PasswordEncription.encrypt(password);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return userDao.checkUserStatus(username, dec_password);
	}

	@Override
	public int getUserId(String username) {
		
		return userDao.getUserId(username);
	}

	@Override
	public boolean saveUserInformation(Users user) {
		
		return userDao.saveUserInformation(user);
	}
	
	@Override
	public boolean saveInitialRequiredInformation(Users user) {
		
		return userDao.saveInitialRequiredInformation(user);
	}

	@Override
	public List<Users> getUserById(Users user) {
		
		return userDao.getUserById(user);
	}

	@Override
	public boolean updateUserInformation(Users users) {
		
		return userDao.updateUserInformation(users);
	}

	@Override
	public boolean updateUserNamePassword(Users users) {
		
		return userDao.updateUserNamePassword(users);
	}

}
