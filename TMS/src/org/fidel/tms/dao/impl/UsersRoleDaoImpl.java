package org.fidel.tms.dao.impl;

import java.util.List;

import org.fidel.tms.dao.UsersRoleDao;
import org.fidel.tms.model.UsersRole;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRoleDaoImpl implements UsersRoleDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<UsersRole> getActiveUsersRole() {
		
		Query query = getSessionFactory().openSession().createQuery("FROM UsersRole WHERE userrole_status = 'Active'");
		
		return (List<UsersRole>) query.list();
	}

	@Override
	public List<UsersRole> getAllUsersRole() {

		Query query = getSessionFactory().openSession().createQuery("FROM UsersRole");
		
		return (List<UsersRole>) query.list();
	}

	@Override
	public boolean saveUsersRole(UsersRole usersRole) {
		
		if(checkUsersRole(usersRole.getUserrole_name())){
			return false;
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String create_date = TodayDate_YYYYMMDD.getToday();
		
		Query query = getSessionFactory().openSession().createSQLQuery("INSERT INTO UsersRole(userrole_name, userrole_status, create_by, create_date) VALUES(:userrole_name, 'Active', :create_by, :create_date)");
		query.setParameter("userrole_name", usersRole.getUserrole_name());
		query.setParameter("create_by", loggedInUserId);
		query.setParameter("create_date", create_date);
		int rslt = query.executeUpdate();
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateUsersRole(UsersRole usersRole) {

		if(checkUsersRole(usersRole.getUserrole_name(), usersRole.getUr_id())){
			return false;
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String update_date = TodayDate_YYYYMMDD.getToday();
		
		Query query = getSessionFactory().openSession().createSQLQuery("UPDATE UsersRole SET userrole_name = :userrole_name, userrole_status = :userrole_status, update_by = :update_by, update_date = :update_date WHERE ur_id = :ur_id");
		query.setParameter("userrole_name", usersRole.getUserrole_name());
		query.setParameter("userrole_status", usersRole.getUserrole_status());
		query.setParameter("update_by", loggedInUserId);
		query.setParameter("update_date", update_date);
		query.setParameter("ur_id", usersRole.getUr_id());
		int rslt = query.executeUpdate();
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkUsersRole(String userrole_name) {

		Query query = getSessionFactory().openSession().createQuery("FROM UsersRole WHERE userrole_name = :userrole_name");
		query.setParameter("userrole_name", userrole_name);
		int size = query.list().size();
		
		if(size > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkUsersRole(String userrole_name, int ur_id) {
		
		Query query = getSessionFactory().openSession().createQuery("FROM UsersRole WHERE userrole_name = :userrole_name and ur_id <> :ur_id");
		query.setParameter("userrole_name", userrole_name);
		query.setParameter("ur_id", ur_id);
		int size = query.list().size();
		
		if(size > 0){
			return true;
		} else {
			return false;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
