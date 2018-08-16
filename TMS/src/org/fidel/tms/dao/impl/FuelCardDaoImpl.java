package org.fidel.tms.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.fidel.tms.dao.FuelCardDao;
import org.fidel.tms.model.FuelCard;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FuelCardDaoImpl implements FuelCardDao {
	
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<FuelCard> getAllFuelCardList() {
		
		Query query = getSessionFactory().openSession().createQuery("FROM FuelCard");
		
		return (List<FuelCard>)query.list();
	}

	@Override
	public boolean saveFuelCard(FuelCard fuelCard) {
		
		if(findFuelCardByFcNo(fuelCard.getFc_no()).size() > 0){
			return false;
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String cr_date = TodayDate_YYYYMMDD.getToday();

		Query query = getSessionFactory().openSession().createSQLQuery("INSERT INTO FuelCard(fc_no, fc_company, fc_status, create_by, create_date) VALUES(:fc_no, :fc_company, 'Active', :create_by, :create_date)");
		query.setParameter("fc_no", fuelCard.getFc_no());
		query.setParameter("fc_company", fuelCard.getFc_company());
		query.setParameter("create_by", loggedInUserId);
		query.setParameter("create_date", cr_date);
		
		if(query.executeUpdate() > 0){
			
			Query query2 = getSessionFactory().openSession().createSQLQuery("INSERT INTO fuelcardavailabilitystatus(fc_id, fca_status, create_by, create_date) VALUES(:fc_id, 'AC', :create_by, :create_date)");
			query2.setParameter("fc_id", getLastInsertedFuelCardId());
			query2.setParameter("create_by", loggedInUserId);
			query2.setParameter("create_date", cr_date);
			query2.executeUpdate();
			
			return true;
		}
		return false;
	}
	
	@Override
	public List<FuelCard> findFuelCardByFcNo(String fc_no) {
		
		Query query = getSessionFactory().openSession().createQuery("FROM FuelCard WHERE fc_no = :fc_no");
		query.setParameter("fc_no", fc_no);
		
		return (List<FuelCard>)query.list();
	}
	
	@Override
	public boolean updateFuelCard(FuelCard fuelCard) {
		
		List<FuelCard> fcList = getAllFuelCardList();
		
		for(int i = 0; i < fcList.size(); i++){
			
			if(fcList.get(i).getFc_id() != fuelCard.getFc_id() && fcList.get(i).getFc_no().equalsIgnoreCase(fuelCard.getFc_no())){
				
				return false;
			}
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String update_date = TodayDate_YYYYMMDD.getToday();

		Query query = getSessionFactory().openSession().createSQLQuery("UPDATE FuelCard SET fc_no = :fc_no, fc_company = :fc_company, fc_status = :fc_status, update_by = :update_by, update_date = :update_date WHERE fc_id = :fc_id");
		query.setParameter("fc_no", fuelCard.getFc_no());
		query.setParameter("fc_company", fuelCard.getFc_company());
		query.setParameter("fc_status", fuelCard.getFc_status());
		query.setParameter("update_by", loggedInUserId);
		query.setParameter("update_date", update_date);
		query.setParameter("fc_id", fuelCard.getFc_id());
		
		if(query.executeUpdate() > 0){
			return true;
		}
		return false;
	}
	
	public int getLastInsertedFuelCardId(){
		
		Query query = getSessionFactory().openSession().createQuery("FROM FuelCard");
		
		List<FuelCard> rslt = (List<FuelCard>)query.list();
		
		return rslt.get(rslt.size()-1).getFc_id();
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
