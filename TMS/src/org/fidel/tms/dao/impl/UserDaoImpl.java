package org.fidel.tms.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.fidel.tms.dao.UserDao;
import org.fidel.tms.mapper.UserRowMapper;
import org.fidel.tms.model.Users;
import org.fidel.tms.utils.PasswordEncription;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.SystemURL;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
	
	DataSource dataSource;	
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;

	public void insertData(Users users) {

		String sql = "INSERT INTO users(user_name, password, user_status, ur_id) VALUES (?, ?, ?, ?)";

		jdbcTemplate.update(sql, new Object[] { users.getUser_name(), users.getPassword(), users.getUser_status(), users.getUr_id() });

	}

	public List<Users> getUserList() {

		String sql = "SELECT a.user_id, a.fname, a.mname, a.gname, a.user_name, a.password, a.user_status, a.ur_id, b.userrole_name FROM users a, usersrole b WHERE a.ur_id = b.ur_id";

		return jdbcTemplate.query(sql, new UserRowMapper());		
	}
	
	@Override
	public List<Users> getUserById(Users user) {
		
		String sql = "SELECT a.user_id, a.fname, a.mname, a.gname, a.user_name, a.password, a.user_status, a.ur_id, b.userrole_name FROM users a, usersrole b WHERE a.ur_id = b.ur_id and a.user_id = ?";

		return jdbcTemplate.query(sql, new Object[]{ user.getUser_id() }, new UserRowMapper());
	}
	
	@Override
	public int getHibernateUserCount(){
		String hql = "SELECT COUNT(b) FROM Users b";
		Query query = getSessionFactory().openSession().createQuery(hql);
		return ((Long)query.uniqueResult()).intValue();
	}

	@Override
	public void deleteData(String id) {
		String sql = "delete from users where user_id=" + id;
		jdbcTemplate.update(sql);
	}

	@Override
	public void updateData(Users users) {

		String sql = "UPDATE users SET user_name = ?, password = ?, user_status = ?, ur_id = ? WHERE user_id = ?";
		
		jdbcTemplate.update(sql, new Object[] { users.getUser_name(), users.getPassword(), users.getUser_status(), users.getUr_id() });

	}

	@Override
	public Users getUser(String id) {
		List<Users> userList = new ArrayList<Users>();
		String sql = "select * from users where user_id= " + id;
		userList = jdbcTemplate.query(sql, new UserRowMapper());
		return userList.get(0);
	}

	@Override
	public boolean validateUser(String username, String password) {
		String hql = "SELECT COUNT(b) FROM Users b WHERE user_name = '" + username + "' and password = '" + password + "'";
		Query query = getSessionFactory().openSession().createQuery(hql);
		int count = ((Long)query.uniqueResult()).intValue();
		
		if(count > 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean checkUserStatus(String username, String password) {
		String hql = "SELECT COUNT(b) FROM Users b WHERE user_name = '" + username + "' and password = '" + password + "' and user_status = 'Inactive'";
		Query query = getSessionFactory().openSession().createQuery(hql);
		int count = ((Long)query.uniqueResult()).intValue();
		
		if(count > 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int getUserId(String username) {
		String hql = "SELECT b.user_id FROM Users b WHERE b.user_name = '" + username + "'";
		Query query = getSessionFactory().openSession().createQuery(hql);
		return (Integer)query.list().get(0);
	}
	
	@Override
	public boolean saveUserInformation(Users users) {
		
		String encript_password = "";
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String create_date = TodayDate_YYYYMMDD.getToday();
		
		String sql = "INSERT INTO users(fname, mname, gname, user_name, password, user_status, ur_id, create_by, create_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			encript_password = PasswordEncription.encrypt(users.getPassword());
		} catch (Exception e) {			
			e.printStackTrace();
		}

		int rslt = jdbcTemplate.update(sql, new Object[] { users.getFname(), users.getMname(), users.getGname(), users.getUser_name(), encript_password, "Active", users.getUr_id(), loggedInUserId, create_date });
		
		if(rslt > 0){
						
			return true;
		}
		return false;
	}
	
	@Override
	public boolean saveInitialRequiredInformation(Users users) {
		
		String encript_password = "";
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String create_date = TodayDate_YYYYMMDD.getToday();
		
		////**** create the system database
		
		
		
		////**** to create System Administrator role
		String sql1 = "INSERT INTO usersrole(userrole_name, userrole_status, create_by, create_date) VALUES('System Admin', 'Active', 1, '" + create_date  + "')";
		
		int rslt1 = jdbcTemplate.update(sql1);
		
		if(rslt1 > 0){
		
			////**** to create a super admin user
			String sql2 = "INSERT INTO users(fname, mname, gname, user_name, password, user_status, ur_id, create_by, create_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			try {
				encript_password = PasswordEncription.encrypt(users.getPassword());
			} catch (Exception e) {			
				e.printStackTrace();
			}
	
			int rslt2 = jdbcTemplate.update(sql2, new Object[] { users.getFname(), users.getMname(), users.getGname(), users.getUser_name(), encript_password, "Active", users.getUr_id(), loggedInUserId, create_date });
			
			if(rslt2 > 0){
				
				////**** to relate the system admin with all system module
				String sql3 = "INSERT INTO usersrolemodulerelation(ur_id, m_id, urmr_status, create_by, create_date) "
								+ "SELECT 1, m_id, 'Active', 1, '" + create_date + "' FROM systemmodule";
				
				int rslt3 = jdbcTemplate.update(sql3);
				
				if(rslt3 > 0){
					
					////**** insert all the system URL's					
					String sql4 = SystemURL.getAllSystemUrl();
					
					int rslt4 = jdbcTemplate.update(sql4);
					
					if(rslt4 > 0){						
					
						////**** to give the system admin full authentication to access all URL's of the system
						String sql5 = "INSERT INTO usersauthentication (urmr_id, su_id, ua_status, create_by, create_date) " +
										"SELECT (select b.urmr_id from usersrolemodulerelation b, usersrole c where b.m_id = a.sm_id and c.ur_id = b.ur_id and c.ur_id = 1) urmr_id, a.su_id, 'Active', 1, '" + create_date + "' FROM systemurl a";
						
						int rslt5 = jdbcTemplate.update(sql5);
						
						if(rslt5 > 0){
							
							return true;
						}
					}
				}
			} 
		}
		return false;
	}

	@Override
	public boolean updateUserInformation(Users users) {
		
		String encript_password = "";
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String create_date = TodayDate_YYYYMMDD.getToday();
		
		String sql = "UPDATE users SET fname = ?, mname = ?, gname = ?, user_name = ?, password = ?, user_status = ?, ur_id = ?, update_by = ?, update_date = ? WHERE user_id = ?";
		
		try {
			encript_password = PasswordEncription.encrypt(users.getPassword());
		} catch (Exception e) {			
			e.printStackTrace();
		}

		int rslt = jdbcTemplate.update(sql, new Object[] { users.getFname(), users.getMname(), users.getGname(), users.getUser_name(), encript_password, users.getUser_status(), users.getUr_id(), loggedInUserId, create_date, users.getUser_id() });
		
		if(rslt > 0){
			
			return true;
		}
		return false;
	}	
	
	@Override
	public boolean updateUserNamePassword(Users users) {
		
		String encript_password = "";
		String old_encript_password = "";
		
		try {
			
			encript_password = PasswordEncription.encrypt(users.getPassword());
			
			old_encript_password = PasswordEncription.encrypt(users.getOldPassword());
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		users.setUser_id(SessionManager.getUserIdOnSession());
	
		List<Users> userList = getUserById(users);
		
		if(!userList.get(0).getPassword().equals(old_encript_password)){
			
			return false;
		}
		
		final String sql = "UPDATE users SET user_name = ?, password = ? WHERE user_id = ?";
		
		int rslt = jdbcTemplate.update(sql, new Object[] { users.getUser_name(), encript_password, users.getUser_id() });
		
		if(rslt > 0){
			
			return true;
		}
		
		return false;
	}
	
	
	/*
	 * getter and setter
	 */
	
	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
