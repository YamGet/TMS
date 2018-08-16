package org.fidel.tms.dao.impl;

import javax.sql.DataSource;

import org.fidel.tms.dao.UsersAuthenticationDao;
import org.fidel.tms.model.UsersAuthentication;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsersAuthenticationDaoImpl implements UsersAuthenticationDao {
	
	DataSource dataSource;	
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean relateURL(UsersAuthentication usersAuth) {
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String create_date = TodayDate_YYYYMMDD.getToday();
		
		String sql = "INSERT INTO UsersAuthentication(urmr_id, su_id, ua_status, create_by, create_date) VALUES(?, ?, 'Active', ?, ?)";
		
		int rslt = jdbcTemplate.update(sql, new Object[]{ usersAuth.getUsersRoleModuleRelation().getUrmr_id(), usersAuth.getSu_id(), loggedInUserId, create_date});
		
		if(rslt > 0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean unrelateURL(UsersAuthentication usersAuth) {
		
		String hql = "SELECT ua_id FROM UsersAuthentication WHERE urmr_id = :urmr_id and su_id = :su_id";
		
		Query query = getSessionFactory().openSession().createQuery(hql);
		
		query.setParameter("urmr_id", usersAuth.getUsersRoleModuleRelation().getUrmr_id());
		
		query.setParameter("su_id", usersAuth.getSu_id());
		
		int ua_id = (Integer)query.list().get(0);
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String update_date = TodayDate_YYYYMMDD.getToday();
		
		
		String sql = "UPDATE UsersAuthentication SET ua_status = 'Deleted', update_by = ?, update_date = ? WHERE ua_id = ?";
		
		int rslt = jdbcTemplate.update(sql, new Object[]{ loggedInUserId, update_date, ua_id});
		
		if(rslt > 0){
			return true;
		}
		return false;
	}

	
	
	
	public DataSource getDataSource() {
		return dataSource;
	}
	@Autowired
	public void setDataSource(DataSource dataSource) {
		//this.dataSource = dataSource;
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
