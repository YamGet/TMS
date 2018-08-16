package org.fidel.tms.service;

import java.util.List;

import org.fidel.tms.model.Users;

public interface UserService {
	
	public List<Users> getAllUsersList();
	
	public List<Users> getUserById(Users user);
	
	public int getHibernateUserCount();
	
	public boolean validateUser(String username, String password);
	
	public boolean checkUserStatus(String username, String password);
	
	public int getUserId(String username);
	
	public boolean saveUserInformation(Users users);
	
	public boolean saveInitialRequiredInformation(Users users);
	
	public boolean updateUserInformation(Users users);
	
	public boolean updateUserNamePassword(Users users);

}
