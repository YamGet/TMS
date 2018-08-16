package org.fidel.tms.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.fidel.tms.dao.TrucksDao;
import org.fidel.tms.mapper.TruckInformationExtractor;
import org.fidel.tms.mapper.TruckInformationRowMapper;
import org.fidel.tms.model.TruckAvailabilityStatus;
import org.fidel.tms.model.TruckInformation;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TrucksDaoImpl implements TrucksDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;

	@Override
	public List<TruckInformation> getActiveTrucks() {
		
		Query query = getSessionFactory().openSession().createQuery("FROM TruckInformation WHERE truck_status = 'Active'");
		
		return (List<TruckInformation>)query.list();
	}

	@Override
	public List<TruckInformation> getAllTrucks() {
		
		Query query = getSessionFactory().openSession().createQuery("FROM TruckInformation");
		
		return (List<TruckInformation>)query.list();
	}

	@Override
	public List<TruckInformation> getTrucksListByTruckType(String truck_type) {
		
		String truckType = "";
		
		if(truck_type.equals("BOTH")){
			
			truckType = "'DRY', 'FLUID'";
		} else {
			
			truckType = "'" + truck_type + "'";
		}
		
		Query query = getSessionFactory().openSession().createQuery("FROM TruckInformation WHERE truck_type in (" + truckType + ")");
		
		return (List<TruckInformation>)query.list();
	}

	@Override
	public boolean saveTrucks(TruckInformation trucks) {
		
		if(checkTruckExistance(trucks.getshanci_no(), trucks.getTruck_plate_no())){
			return false;
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String create_date = TodayDate_YYYYMMDD.getToday();
		
		Query query = getSessionFactory().openSession().createSQLQuery("INSERT INTO TruckInformation(shanci_no, truck_plate_no, side_no, truck_model, loading_capacity, truck_owner, truck_type, truck_status, create_by, create_date) VALUES(:shanci_no, :truck_plate_no, :side_no, :truck_model, :loading_capacity, :truck_owner, :truck_type, 'Active', :create_by, :create_date)");
		query.setParameter("shanci_no", trucks.getshanci_no());
		query.setParameter("truck_plate_no", trucks.getTruck_plate_no());
		query.setParameter("side_no", trucks.getSide_no());
		query.setParameter("truck_model", trucks.getTruck_model());
		query.setParameter("loading_capacity", trucks.getloading_capacity());
		query.setParameter("truck_owner", trucks.getTruck_owner());
		query.setParameter("truck_type", trucks.getTruck_type());
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
	public boolean updateTrucks(TruckInformation trucks) {
		
		if(checkTruckExistance(trucks.getshanci_no(), trucks.getTruck_plate_no(), trucks.getTri_id())){
			return false;
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String update_date = TodayDate_YYYYMMDD.getToday();
		
		Query query = getSessionFactory().openSession().createSQLQuery("UPDATE TruckInformation SET shanci_no = :shanci_no, truck_plate_no = :truck_plate_no, side_no = :side_no, truck_model = :truck_model, loading_capacity = :loading_capacity, truck_owner = :truck_owner, truck_type = :truck_type, truck_status = :truck_status, update_by = :update_by, update_date = :update_date WHERE tri_id = :tri_id");
		query.setParameter("shanci_no", trucks.getshanci_no());
		query.setParameter("truck_plate_no", trucks.getTruck_plate_no());
		query.setParameter("side_no", trucks.getSide_no());
		query.setParameter("truck_model", trucks.getTruck_model());
		query.setParameter("loading_capacity", trucks.getloading_capacity());
		query.setParameter("truck_owner", trucks.getTruck_owner());
		query.setParameter("truck_type", trucks.getTruck_type());
		query.setParameter("truck_status", trucks.getTruck_status());
		query.setParameter("update_by", loggedInUserId);
		query.setParameter("update_date", update_date);
		query.setParameter("tri_id", trucks.getTri_id());
		int rslt = query.executeUpdate();
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean checkTruckExistance(String shanci_no, String truck_plate_no) {
		
		Query query = getSessionFactory().openSession().createQuery("FROM TruckInformation b WHERE b.shanci_no = :shanci_no or b.truck_plate_no = :truck_plate_no");
		query.setParameter("shanci_no", shanci_no);
		query.setParameter("truck_plate_no", truck_plate_no);
		
		int count = query.list().size();
		
		if(count > 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean checkTruckExistance(String shanci_no, String truck_plate_no, int tri_id) {
		
		Query query = getSessionFactory().openSession().createQuery("FROM TruckInformation b WHERE (b.shanci_no = :shanci_no or b.truck_plate_no = :truck_plate_no) and b.tri_id <> :tri_id");
		query.setParameter("shanci_no", shanci_no);
		query.setParameter("truck_plate_no", truck_plate_no);
		query.setParameter("tri_id", tri_id);
		
		int count = query.list().size();
		
		if(count > 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void insertTrucksAvailability(TruckAvailabilityStatus trucksAvaStatus) {
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String create_date = TodayDate_YYYYMMDD.getToday();
		
		Query query = getSessionFactory().openSession().createSQLQuery("INSERT INTO truckavailabilitystatus(tri_id, tas_status, create_by, create_date) VALUES(:tri_id, 'AV', :create_by, :create_date)");
		query.setParameter("tri_id", trucksAvaStatus.getTri_id());
		query.setParameter("create_by", loggedInUserId);
		query.setParameter("create_date", create_date);
		query.executeUpdate();
	}

	@Override
	public List<TruckInformation> getLastTruckInserted() {
		
		String sql = "SELECT max(tri_id) as tri_id, truck_plate_no FROM TruckInformation";
		
		return jdbcTemplate.query(sql, new TruckInformationRowMapper());
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
