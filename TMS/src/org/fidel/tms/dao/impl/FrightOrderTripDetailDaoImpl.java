package org.fidel.tms.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.fidel.tms.dao.FrightOrderTripDetailDao;
import org.fidel.tms.mapper.FrightOrderTripDetailRowMapper;
import org.fidel.tms.mapper.FrightOrderTripDetailWithKMRowMapper;
import org.fidel.tms.model.FrightOrderTripDetail;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FrightOrderTripDetailDaoImpl implements FrightOrderTripDetailDao {
	
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean saveFrightOrderTripDetail(FrightOrderTripDetail frightOrderTripDetail) {
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String cr_date = TodayDate_YYYYMMDD.getToday();
		
		String hql = "INSERT INTO frightordertripdetail(fo_id, client_organization, loading_type, origin_place, destination_place, initial_km, loading_quantity, distance, price, fotd_status, create_by, create_date) VALUES(:fo_id, :client_organization, :loading_type, :origin_place, :destination_place, :initial_km, :loading_quantity, :distance, :price, 'Active', :create_by, :create_date)";

		Query query = getSessionFactory().openSession().createSQLQuery(hql);
		query.setParameter("fo_id", frightOrderTripDetail.getFo_id());
		query.setParameter("client_organization", frightOrderTripDetail.getClient_organization());
		query.setParameter("loading_type", frightOrderTripDetail.getLoading_type());
		query.setParameter("origin_place", frightOrderTripDetail.getOrigin_place());
		query.setParameter("destination_place", frightOrderTripDetail.getDestination_place());
		query.setParameter("initial_km", frightOrderTripDetail.getInitial_km());
		query.setParameter("loading_quantity", frightOrderTripDetail.getLoading_quantity());
		query.setParameter("distance", frightOrderTripDetail.getDistance());
		query.setParameter("price", frightOrderTripDetail.getPrice());
		query.setParameter("create_by", loggedInUserId);
		query.setParameter("create_date", cr_date);
		int rslt = query.executeUpdate();
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean updateFrightOrderTripDetail(FrightOrderTripDetail frightOrderTripDetail) {

		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String update_date = TodayDate_YYYYMMDD.getToday();
		
		String hql = "UPDATE frightordertripdetail SET client_organization = :client_organization, loading_type = :loading_type, origin_place = :origin_place, destination_place = :destination_place, initial_km = :initial_km, loading_quantity = :loading_quantity, distance = :distance, price = :price, update_by = :update_by, update_date = :update_date WHERE fotd_id = :fotd_id";
		
		Query query = getSessionFactory().openSession().createSQLQuery(hql);		
		query.setParameter("client_organization", frightOrderTripDetail.getClient_organization());
		query.setParameter("loading_type", frightOrderTripDetail.getLoading_type());
		query.setParameter("origin_place", frightOrderTripDetail.getOrigin_place());
		query.setParameter("destination_place", frightOrderTripDetail.getDestination_place());
		query.setParameter("initial_km", frightOrderTripDetail.getInitial_km());
		query.setParameter("loading_quantity", frightOrderTripDetail.getLoading_quantity());
		query.setParameter("distance", frightOrderTripDetail.getDistance());
		query.setParameter("price", frightOrderTripDetail.getPrice());
		query.setParameter("update_by", loggedInUserId);
		query.setParameter("update_date", update_date);
		query.setParameter("fotd_id", frightOrderTripDetail.getFotd_id());
		int rslt = query.executeUpdate();
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public List<FrightOrderTripDetail> getFrightOrderTripDetail(int fo_id) {
		
		String sql = "SELECT fotd_id, fo_id, client_organization, loading_type, origin_place, destination_place, initial_km, loading_quantity, distance, price, crv_number, dispatch_doc_ref_no, delivery_doc_ref_no, delivered_quantity, delivery_date, fright_note "
				+ "FROM FrightOrderTripDetail WHERE fo_id = ? ";
		
		return jdbcTemplate.query(sql,  new Object[] { fo_id }, new FrightOrderTripDetailWithKMRowMapper());		 
	}
	
	@Override
	public boolean closeFrightOrder(FrightOrderTripDetail frightOrderTripDetail) {
		
		int rslt = 0;
		
		try{
			int loggedInUserId = SessionManager.getUserIdOnSession();
			
			String update_date = TodayDate_YYYYMMDD.getToday();
			
			String sql = "UPDATE FrightOrderTripDetail SET dispatch_doc_ref_no = ?, delivery_doc_ref_no = ?, delivered_quantity = ?, delivery_date = ?, fright_note = ?, fotd_status = 'Closed', close_date = ?, update_by = ?, update_date = ? WHERE fo_id = ?";
			
			rslt = jdbcTemplate.update(sql, new Object[]{	frightOrderTripDetail.getDispatch_doc_ref_no(), 
																frightOrderTripDetail.getDelivery_doc_ref_no(),
																frightOrderTripDetail.getDelivered_quantity(),
																frightOrderTripDetail.getDelivery_date(), 
																frightOrderTripDetail.getFright_note(), update_date,
																loggedInUserId, update_date, frightOrderTripDetail.getFo_id()});
		} catch(Exception e){
			e.printStackTrace();
		}
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		} 
		
	}
		
	@Override
	public boolean updateFrightOrderPaymentAppointmentDate(FrightOrderTripDetail foDetail) {
		
		final String inprocessFrightOrderList = "UPDATE frightordertripdetail SET payment_appointment_date = ? WHERE fo_id = ?";
		
		int rslt = jdbcTemplate.update(inprocessFrightOrderList, new Object[]{ foDetail.getPayment_appointment_date(), foDetail.getFo_id() });
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void openTruckTrailAvailiability(int tri_id, int tli_id) {
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String update_date = TodayDate_YYYYMMDD.getToday();
		
		final String sql_tr = "UPDATE truckAvailabilityStatus SET fo_id = ?, date_from = ?, date_to = ?, tas_status = 'AV', update_by = ?, update_date = ? WHERE tri_id = ?";
		
		jdbcTemplate.update(sql_tr, new Object[]{ null, null, null, loggedInUserId, update_date, tri_id});
		
		final String sql_tl = "UPDATE trailAvailabilityStatus SET fo_id = ?, date_from = ?, date_to = ?, tlas_status = 'AV', update_by = ?, update_date = ? WHERE tli_id = ?";
		
		jdbcTemplate.update(sql_tl, new Object[]{ null, null, null, loggedInUserId, update_date, tli_id});
	}
	
	@Override
	public List<FrightOrderTripDetail> getFrightOrderTripDetailById(int fotd_id) {
		
		String sql = "SELECT fotd_id, fo_id, client_organization, loading_type, origin_place, destination_place, initial_km, loading_quantity, distance, price, crv_number, dispatch_doc_ref_no, delivery_doc_ref_no, delivered_quantity, delivery_date, fright_note "
				+ "FROM FrightOrderTripDetail WHERE fotd_id = ? ";
		
		return jdbcTemplate.query(sql,  new Object[] { fotd_id }, new FrightOrderTripDetailWithKMRowMapper());	
	}
	
	@Override
	public boolean updateFrightOrderTripDetailPrice(FrightOrderTripDetail foDetail) {
		
		final String sql = "UPDATE frightordertripdetail SET price = ?, crv_number = ? WHERE fo_id = ?";
		
		int rslt = jdbcTemplate.update(sql, new Object[]{ foDetail.getPrice(), foDetail.getCrv_number(), foDetail.getFo_id() });
		
		if(rslt > 0){
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

}
