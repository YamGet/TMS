package org.fidel.tms.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.fidel.tms.dao.DriversDao;
import org.fidel.tms.mapper.DriversExtractor;
import org.fidel.tms.mapper.DriversRowMapper;
import org.fidel.tms.model.Drivers;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DriversDaoImpl implements DriversDao {
	
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Drivers> getAllDrivers() {		
		
		Query query = getSessionFactory().openSession().createQuery("FROM Drivers");
		
		return (List<Drivers>)query.list();
	}

	@Override
	public List<Drivers> getActiveDrivers() {
		
		return null;
	}

	@Override
	public boolean saveDrivers(Drivers drivers) {
		
		if(checkDrivers(drivers.getDriving_license_no())){
			return false;
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String cr_date = TodayDate_YYYYMMDD.getToday();
		
		Query query = getSessionFactory().openSession().createSQLQuery("INSERT INTO Drivers(fname, mname, gname, driving_license_no, local_phone, abroad_phone, dr_status, create_by, create_date) VALUES(:fname, :mname, :gname, :driving_license_no, :local_phone, :abroad_phone, 'Active', :loggedInUserId, :cr_date)");
		query.setParameter("fname", drivers.getFname());
		query.setParameter("mname", drivers.getMname());
		query.setParameter("gname", drivers.getGname());
		query.setParameter("driving_license_no", drivers.getDriving_license_no());
		query.setParameter("local_phone", drivers.getLocal_phone());
		query.setParameter("abroad_phone", drivers.getAbroad_phone());
		query.setParameter("loggedInUserId", loggedInUserId);
		query.setParameter("cr_date", cr_date);
		
		if(query.executeUpdate() > 0){
			return true;
		}		
		return false;
	}

	@Override
	public boolean updateDrivers(Drivers drivers) {
		
		if(checkDrivers(drivers.getDriving_license_no(), drivers.getDr_id())){
			return false;
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String update_date = TodayDate_YYYYMMDD.getToday();
		
		Query query = getSessionFactory().openSession().createSQLQuery("UPDATE Drivers SET fname = :fname, mname = :mname, gname = :gname, driving_license_no = :driving_license_no, local_phone = :local_phone, abroad_phone = :abroad_phone, dr_status = :dr_status, update_by = :loggedInUserId, update_date = :update_date WHERE dr_id = :dr_id");
		query.setParameter("fname", drivers.getFname());
		query.setParameter("mname", drivers.getMname());
		query.setParameter("gname", drivers.getGname());
		query.setParameter("driving_license_no", drivers.getDriving_license_no());
		query.setParameter("local_phone", drivers.getLocal_phone());
		query.setParameter("abroad_phone", drivers.getAbroad_phone());
		query.setParameter("dr_status", drivers.getDr_status());
		query.setParameter("loggedInUserId", loggedInUserId);
		query.setParameter("update_date", update_date);
		query.setParameter("dr_id", drivers.getDr_id());
		int rslt = query.executeUpdate();
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkDrivers(String driving_license_no) {

		Query query = getSessionFactory().openSession().createQuery("FROM Drivers b WHERE b.driving_license_no = :driving_license_no");
		query.setParameter("driving_license_no", driving_license_no);
		
		int count = query.list().size();
		
		if(count > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkDrivers(String driving_license_no, int dr_id) {

		Query query = getSessionFactory().openSession().createQuery("FROM Drivers b WHERE b.driving_license_no = :driving_license_no and b.dr_id <> :dr_id");
		query.setParameter("driving_license_no", driving_license_no);
		query.setParameter("dr_id", dr_id);
		
		int count = query.list().size();
		
		if(count > 0){
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	public DataSource getDataSource() {
		return dataSource;
	}
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
