package org.fidel.tms.dao.impl;

import javax.sql.DataSource;

import org.fidel.tms.dao.UsersRoleModuleRelationDao;
import org.fidel.tms.model.UsersRoleModuleRelation;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRoleModuleRelationDaoImpl implements UsersRoleModuleRelationDao {
	
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean saveUsersRoleModuleRelation(UsersRoleModuleRelation usersRoleModuleRelation) {
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String cr_date = TodayDate_YYYYMMDD.getToday();
		
		Query query = getSessionFactory().openSession().createSQLQuery("INSERT INTO UsersRoleModuleRelation(ur_id, m_id, urmr_status, create_by, create_date) VALUES(:ur_id, :m_id, 'Active', :create_by, :create_date)");
		query.setParameter("ur_id", usersRoleModuleRelation.getUr_id());
		query.setParameter("m_id", usersRoleModuleRelation.getM_id());
		query.setParameter("create_by", loggedInUserId);
		query.setParameter("create_date", cr_date);
		
		if(query.executeUpdate() > 0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateUsersRoleModuleRelation(UsersRoleModuleRelation usersRoleModuleRelation) {

		Query query = getSessionFactory().openSession().createSQLQuery("DELETE FROM UsersRoleModuleRelation WHERE ur_id = :ur_id and m_id = :m_id");
		query.setParameter("ur_id", usersRoleModuleRelation.getUr_id());
		query.setParameter("m_id", usersRoleModuleRelation.getM_id());
				
		if(query.executeUpdate() > 0){
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
