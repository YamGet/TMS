package org.fidel.tms.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.fidel.tms.dao.TrailsDao;
import org.fidel.tms.mapper.TrailInformationRowMapper;
import org.fidel.tms.mapper.TruckInformationRowMapper;
import org.fidel.tms.model.TrailAvailabilityStatus;
import org.fidel.tms.model.TrailInformation;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TrailsDaoImpl implements TrailsDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;

	@Override
	public List<TrailInformation> getActiveTrails() {
		
		return null;
	}

	@Override
	public List<TrailInformation> getAllTrails() {
		
		Query query = getSessionFactory().openSession().createQuery("FROM TrailInformation");
		
		return (List<TrailInformation>) query.list();
	}

	@Override
	public boolean saveTrailInformation(TrailInformation trail) {
		
		if(checkTrailExistance(trail.getTrail_plate_no())){
			return false;
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String create_date = TodayDate_YYYYMMDD.getToday();
		
		Query query = getSessionFactory().openSession().createSQLQuery("INSERT INTO TrailInformation(trail_plate_no, loading_capacity, trail_owner, trail_type, trail_status, create_by, create_date) VALUES(:trail_plate_no, :loading_capacity, :trail_owner, :trail_type, 'Active', :create_by, :create_date)");
		query.setParameter("trail_plate_no", trail.getTrail_plate_no());
		query.setParameter("loading_capacity", trail.getLoading_capacity());
		query.setParameter("trail_owner", trail.getTrail_owner());
		query.setParameter("trail_type", trail.getTrail_type());
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
	public boolean updateTrailInformation(TrailInformation trail) {
		
		if(checkTrailExistance(trail.getTrail_plate_no(), trail.getTli_id())){
			return false;
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String update_date = TodayDate_YYYYMMDD.getToday();
		
		Query query = getSessionFactory().openSession().createSQLQuery("UPDATE TrailInformation SET trail_plate_no = :trail_plate_no, loading_capacity = :loading_capacity, trail_owner = :trail_owner, trail_type = :trail_type, trail_status = :trail_status, update_by = :update_by, update_date = :update_date WHERE tli_id = :tli_id");
		query.setParameter("trail_plate_no", trail.getTrail_plate_no());
		query.setParameter("loading_capacity", trail.getLoading_capacity());
		query.setParameter("trail_owner", trail.getTrail_owner());
		query.setParameter("trail_type", trail.getTrail_type());
		query.setParameter("trail_status", trail.getTrail_status());
		query.setParameter("update_by", loggedInUserId);
		query.setParameter("update_date", update_date);
		query.setParameter("tli_id", trail.getTli_id());
		int rslt = query.executeUpdate();
		
		if(rslt > 0){
			return true;
		} else {		
			return false;
		}
	}

	@Override
	public boolean checkTrailExistance(String plate_no) {
		
		Query query = getSessionFactory().openSession().createQuery("FROM TrailInformation b WHERE b.trail_plate_no = :trail_plate_no");
		query.setParameter("trail_plate_no", plate_no);
		
		int rslt = query.list().size();
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}		
	}

	@Override
	public boolean checkTrailExistance(String plate_no, int tli_id) {
		
		Query query = getSessionFactory().openSession().createQuery("FROM TrailInformation b WHERE b.trail_plate_no = :trail_plate_no and b.tli_id <> :tli_id");
		query.setParameter("trail_plate_no", plate_no);
		query.setParameter("tli_id", tli_id);
		
		int rslt = query.list().size();
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void insertTrailsAvailability(TrailAvailabilityStatus trail) {

		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String create_date = TodayDate_YYYYMMDD.getToday();
		
		Query query = getSessionFactory().openSession().createSQLQuery("INSERT INTO trailavailabilitystatus(tli_id, tlas_status, create_by, create_date) VALUES(:tli_id, 'AV', :create_by, :create_date)");
		query.setParameter("tli_id", trail.getTli_id());
		query.setParameter("create_by", loggedInUserId);
		query.setParameter("create_date", create_date);
		query.executeUpdate();
		
	}

	@Override
	public List<TrailInformation> getLastInsertedTrail(TrailAvailabilityStatus trail) {

		String sql = "SELECT max(tli_id) as tli_id, trail_plate_no FROM TrailInformation";
		
		return jdbcTemplate.query(sql, new TrailInformationRowMapper());
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
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

	

}
