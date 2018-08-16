package org.fidel.tms.dao;

import java.util.List;

import org.fidel.tms.model.Users;

public interface UserDao {
	
	public void insertData(Users users);

	public List<Users> getUserList();

	public void updateData(Users users);

	public void deleteData(String id);

	public Users getUser(String id);
	
	public int getHibernateUserCount();
	
	public boolean validateUser(String username, String password);
	
	public boolean checkUserStatus(String username, String password);
	
	public int getUserId(String username);
	
	public boolean saveUserInformation(Users user);
	
	public boolean saveInitialRequiredInformation(Users user);
	
	public List<Users> getUserById(Users user);
	
	public boolean updateUserInformation(Users users);
	
	public boolean updateUserNamePassword(Users users);

}
