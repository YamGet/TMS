package org.fidel.tms.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.fidel.tms.dao.FuelCompanyDao;
import org.fidel.tms.model.FuelCard;
import org.fidel.tms.model.FuelCompany;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FuelCompanyDaoImpl implements FuelCompanyDao {
	
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public List<FuelCompany> getAllFuelCompanyList() {
		
		Query query = getSessionFactory().openSession().createQuery("FROM FuelCompany");
		
		return (List<FuelCompany>)query.list();
	}

	@Override
	public boolean saveFuelCompany(FuelCompany fuelCompany) {
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String cr_date = TodayDate_YYYYMMDD.getToday();

		Query query2 = getSessionFactory().openSession().createSQLQuery("INSERT INTO fuelcompany(company_name, contact_person_name, contact_person_phone, fc_status, create_by, create_date) VALUES(:company_name, :contact_person_name, :contact_person_phone, 'Active', :create_by, :create_date)");
		query2.setParameter("company_name", fuelCompany.getCompany_name());
		query2.setParameter("contact_person_name", fuelCompany.getContact_person_name());
		query2.setParameter("contact_person_phone", fuelCompany.getContact_person_phone());
		query2.setParameter("create_by", loggedInUserId);
		query2.setParameter("create_date", cr_date);
		
		if(query2.executeUpdate() > 0){
		
			return true;
		}
		
		return false;
	}

	@Override
	public List<FuelCompany> getFuelComponyByFc_id(int fc_id) {
		
		Query query = getSessionFactory().openSession().createQuery("FROM FuelCompany WHERE fc_id = :fc_id");
		query.setParameter("fc_id", fc_id);
		
		return (List<FuelCompany>)query.list();
	}

	@Override
	public boolean updateFuelCompany(FuelCompany fuelCompany) {
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String update_date = TodayDate_YYYYMMDD.getToday();

		Query query = getSessionFactory().openSession().createSQLQuery("UPDATE FuelCompany SET company_name = :company_name, contact_person_name = :contact_person_name, contact_person_phone = :contact_person_phone, fc_status = :fc_status, update_by = :update_by, update_date = :update_date WHERE fc_id = :fc_id");
		query.setParameter("company_name", fuelCompany.getCompany_name());
		query.setParameter("contact_person_name", fuelCompany.getContact_person_name());
		query.setParameter("contact_person_phone", fuelCompany.getContact_person_phone());
		query.setParameter("fc_status", fuelCompany.getFc_status());
		query.setParameter("update_by", loggedInUserId);
		query.setParameter("update_date", update_date);
		query.setParameter("fc_id", fuelCompany.getFc_id());
		
		if(query.executeUpdate() > 0){
			return true;
		}
		return false;
	}

	@Override
	public List<FuelCompany> getActiveFuelCompanyList() {

		Query query = getSessionFactory().openSession().createQuery("FROM FuelCompany WHERE fc_status = 'Active'");
		
		return (List<FuelCompany>)query.list();
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
