package org.fidel.tms.dao.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.fidel.tms.dao.FrightOrderDao;
import org.fidel.tms.mapper.AssociationRowMapper;
import org.fidel.tms.mapper.CouponDisseminationRowMapper;
import org.fidel.tms.mapper.CouponDisseminationWithCouponTypeRowMapper;
import org.fidel.tms.mapper.CouponRegistrationRowMapper;
import org.fidel.tms.mapper.DriversRowMapper;
import org.fidel.tms.mapper.FrightOrderRowMapper;
import org.fidel.tms.mapper.FrightOrderWithFrightDetailAdvancePaymentRowMapper;
import org.fidel.tms.mapper.FrightOrderWithFrightDetailRowMapper;
import org.fidel.tms.mapper.TrailInformationRowMapper;
import org.fidel.tms.mapper.TruckInformationRowMapper;
import org.fidel.tms.model.AdvancePayment;
import org.fidel.tms.model.Associations;
import org.fidel.tms.model.CouponDissemination;
import org.fidel.tms.model.CouponRegistration;
import org.fidel.tms.model.Drivers;
import org.fidel.tms.model.ExpenseType;
import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.model.FrightOrderTripDetail;
import org.fidel.tms.model.Payment;
import org.fidel.tms.model.TrailInformation;
import org.fidel.tms.model.TruckInformation;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FrightOrderDaoImp implements FrightOrderDao {
		
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<FrightOrder> getCompleteFrightOrderList() {
		
		final String frightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, f.dispatch_doc_ref_no, f.delivery_doc_ref_no, f.delivered_quantity, f.origin_place, f.destination_place, f.client_organization, f.loading_quantity, f.price, f.crv_number, a.commission, f.payment_appointment_date, f.payment_request_date, f.close_date, c.truck_owner "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e, frightordertripdetail f "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and f.fotd_status = 'Active'";
		
		return jdbcTemplate.query(frightOrderList, new FrightOrderWithFrightDetailRowMapper());
	}


	@Override
	public List<FrightOrder> getCompleteFrightOrderListByTruckType(String truck_type) {
		
		String truckType = "";
		
		if(truck_type.equals("BOTH")){
			
			truckType = "'DRY', 'FLUID'";
		} else {
			
			truckType = "'" + truck_type + "'";
		}
		
		final String frightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, f.dispatch_doc_ref_no, f.delivery_doc_ref_no, f.delivered_quantity, f.origin_place, f.destination_place, f.client_organization, f.loading_quantity, f.price, f.crv_number, a.commission, f.payment_appointment_date, f.payment_request_date, f.close_date, c.truck_owner "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e, frightordertripdetail f "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and f.fotd_status = 'Active' and c.truck_type in (" + truckType + ")";
		
		return jdbcTemplate.query(frightOrderList, new FrightOrderWithFrightDetailRowMapper());
	}


	
	@Override
	public List<FrightOrder> getIncompleteFrightOrderList() {
		
		final String frightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, a.commission "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and a.fo_id not in (select fo_id from frightordertripdetail)";
		
		return jdbcTemplate.query(frightOrderList, new FrightOrderRowMapper());
	}

	@Override
	public List<FrightOrder> getAllFrightOrderList() {
		
		final String frightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, a.commission "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id";
		
		return jdbcTemplate.query(frightOrderList, new FrightOrderRowMapper());
	}

	@Override
	public List<FrightOrder> getFrightOrder(int fo_id) {

		final String frightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, a.commission "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and a.fo_id = " + fo_id;
		
		return jdbcTemplate.query(frightOrderList, new FrightOrderRowMapper());
	}
	
	@Override
	public List<Associations> getAssociationList() {		
		
		final String getAssociationList = "SELECT a_id, association_name, association_status FROM associations WHERE association_status = 'Active'";

		return jdbcTemplate.query(getAssociationList, new AssociationRowMapper());
	}

	@Override
	public List<TruckInformation> getAvailableTrucks() {
		
		final String getAvailableTrucks = "SELECT a.tri_id, a.truck_plate_no FROM truckinformation a, truckavailabilitystatus b WHERE a.tri_id = b.tri_id and b.tas_status = 'AV' and a.truck_status = 'Active'";
		
		return jdbcTemplate.query(getAvailableTrucks, new TruckInformationRowMapper());
	}

	@Override
	public List<TrailInformation> getAvailableTrails() {
		
		final String getAvailableTrails = "SELECT a.tli_id, a.trail_plate_no FROM trailinformation a, trailavailabilitystatus b WHERE a.tli_id = b.tli_id and b.tlas_status = 'AV' and a.trail_status = 'Active'";
		
		return jdbcTemplate.query(getAvailableTrails, new TrailInformationRowMapper());
	}
	
	@Override
	public List<TrailInformation> getAvailableTrailsBySelectedTruckCarryingType(int tri_id) {
		
		final String getAvailableTrails = "SELECT a.tli_id, a.trail_plate_no FROM trailinformation a, trailavailabilitystatus b WHERE a.tli_id = b.tli_id and b.tlas_status = 'AV' and a.trail_status = 'Active' and a.trail_type like (select truck_type from truckinformation where tri_id = " + tri_id + ")";
		
		return jdbcTemplate.query(getAvailableTrails, new TrailInformationRowMapper());
	}

	@Override
	public List<Drivers> getAvailableDrivers() {
		
		final String getAvailableDrivers = "SELECT distinct a.dr_id, a.fname, a.mname, a.gname, a.local_phone, a.abroad_phone, a.driving_license_no, a.dr_status "
				+ "FROM drivers a "
				+ "WHERE a.dr_status = 'Active' and (a.dr_id not in (select d.dr_id from frightordertripdetail c, frightorder d where d.fo_id = c.fo_id and c.fotd_status = 'Active') and a.dr_id not in (select d.dr_id from frightorder d where d.fo_id not in (select fo_id from frightordertripdetail )) or a.dr_id not in (select dr_id from frightorder))";
		
		return jdbcTemplate.query(getAvailableDrivers, new DriversRowMapper());
	}
	
	@Override
	public List<TruckInformation> getAvailableTrucksForUpdate(int tri_id) {
		
		final String getAvailableTrucks = "SELECT a.tri_id, a.truck_plate_no FROM truckinformation a, truckavailabilitystatus b WHERE a.tri_id = b.tri_id and (b.tas_status = 'AV' or b.tri_id = ?) and a.truck_status = 'Active'";
		
		return jdbcTemplate.query(getAvailableTrucks, new Object[]{ tri_id }, new TruckInformationRowMapper());
	}

	@Override
	public List<TrailInformation> getAvailableTrailsForUpdate(int tli_id) {

		final String getAvailableTrails = "SELECT a.tli_id, a.trail_plate_no FROM trailinformation a, trailavailabilitystatus b WHERE a.tli_id = b.tli_id and (b.tlas_status = 'AV' or b.tli_id = ?) and a.trail_status = 'Active'";
		
		return jdbcTemplate.query(getAvailableTrails, new Object[]{ tli_id }, new TrailInformationRowMapper());
	}

	@Override
	public List<Drivers> getAvailableDriversForUpdate(int dr_id) {
		
		final String getAvailableDrivers = "SELECT distinct a.dr_id, a.fname, a.mname, a.gname, a.local_phone, a.abroad_phone, a.driving_license_no, a.dr_status "
				+ "FROM drivers a "
				+ "WHERE a.dr_status = 'Active' or a.dr_id = ? and (a.dr_id not in (select d.dr_id from frightordertripdetail c, frightorder d where d.fo_id = c.fo_id and c.fotd_status = 'Active') and a.dr_id not in (select d.dr_id from frightorder d where d.fo_id not in (select fo_id from frightordertripdetail )) or a.dr_id not in (select dr_id from frightorder))";
		
		return jdbcTemplate.query(getAvailableDrivers, new Object[]{ dr_id }, new DriversRowMapper());
	}
	
	@Override
	public List<FrightOrderTripDetail> getFrightOrderTripDetail(int fo_id) {

		String hql = "SELECT fo_id, client_organization, loading_type, origin_place, destination_place, loading_quantity, distance, price FROM frightordertripdetail WHERE fo_id = :fo_id ";
		
		Query query = getSessionFactory().openSession().createQuery(hql);		
		query.setParameter("fo_id", fo_id);
		
		return (List<FrightOrderTripDetail>)query.list();
	}

	@Override
	public boolean insertNewFrightOrder(FrightOrder frightOrder) {
		
		if(checkFrightOrderInfoByFonAndAssociation(frightOrder.getFon(), frightOrder.getA_id())){
			return false;
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String cr_date = TodayDate_YYYYMMDD.getToday();
		
		final String getAvailableTrails = "INSERT INTO frightorder(a_id, fon, tri_id, tli_id, dr_id, trip, total_coupon_amount, commission, date_from, date_to, fo_status, create_by, create_date) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Active', ?, ?) ";
		int rslt = -1;
		
		try{
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");				
			Date date_f = df.parse(frightOrder.getDate_from().toString());
			Date date_t = df.parse(frightOrder.getDate_to().toString());
			
			rslt = jdbcTemplate.update(getAvailableTrails, new Object[]{
							frightOrder.getA_id(), frightOrder.getFon(), frightOrder.getTri_id(), frightOrder.getTli_id(), 
							frightOrder.getDr_id(), frightOrder.getTrip(), frightOrder.getTotal_coupon_amount(), 
							frightOrder.getCommission(), date_f, date_t, loggedInUserId, cr_date
					});
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		if(frightOrder.getTotal_coupon_amount() > 0){
		
			insertCouponDissemination(frightOrder);
		}
		
		if(rslt > 0){
			return true; 
			//return insertCouponDissemination(frightOrder);
		} else {
			return false;
		}

	}
	
	@Override
	public boolean updateFrightOrder(FrightOrder frightOrder) {

		if(checkUpdateFrightOrderInfoByFonAndAssociation(frightOrder.getFon(), frightOrder.getA_id(), frightOrder.getFo_id())){
			return false;
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String update_date = TodayDate_YYYYMMDD.getToday();
		
		final String getAvailableTrails = "UPDATE frightorder SET a_id = ?, fon = ?, tri_id = ?, tli_id = ?, dr_id = ?, total_coupon_amount = ?, commission = ?, date_from = ?, date_to = ?, fo_status = ?, update_by = ?, update_date = ? WHERE fo_id = ? ";
		int rslt = -1;
		
		try{
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");				
			Date date_f = df.parse(frightOrder.getDate_from().toString());
			Date date_t = df.parse(frightOrder.getDate_to().toString());
			
			rslt = jdbcTemplate.update(getAvailableTrails, new Object[]{
							frightOrder.getA_id(), frightOrder.getFon(), frightOrder.getTri_id(), frightOrder.getTli_id(), 
							frightOrder.getDr_id(), frightOrder.getTotal_coupon_amount(), frightOrder.getCommission(), 
							date_f, date_t, frightOrder.getFo_status(), loggedInUserId, update_date, frightOrder.getFo_id()
					});
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean insertCouponDissemination(FrightOrder frightOrder){
		
		boolean rslt = false;
		
		
		//////**** to check already assigned coupon values **** //////
		final String getAssignedCouponList = "SELECT cd_id, c_id, fo_id, cd_status FROM CouponDissemination WHERE fo_id = ?";
		List<CouponDissemination> couponDissemination = jdbcTemplate.query(getAssignedCouponList, new Object[]{ frightOrder.getFo_id() }, new CouponDisseminationRowMapper());
		
		if(couponDissemination.size() > 0){
			return rslt;
		}
		
		
		
		double remain = 0, coupon_amount = 0;
		
		final String getActiveCouponList = "SELECT c_id, oil_company, amount, c_serial_no, c_status FROM couponregistration WHERE c_status = 'Active' and c_id not in (select c_id from coupondissemination) ORDER BY c_id, c_serial_no asc ";
		List<CouponRegistration> activeCouponList = jdbcTemplate.query(getActiveCouponList, new CouponRegistrationRowMapper());
		
		List<CouponRegistration> selectedCoupon = new ArrayList<CouponRegistration>();
		
		for(int i = 0; i < activeCouponList.size(); i++){
			
			remain = frightOrder.getTotal_coupon_amount() - coupon_amount;
			
			if(remain >= Integer.parseInt(activeCouponList.get(i).getAmount())){
				
				coupon_amount = coupon_amount + Integer.parseInt(activeCouponList.get(i).getAmount());
				
				CouponRegistration cr = new CouponRegistration();
				cr.setC_id(activeCouponList.get(i).getC_id());
				cr.setAmount(activeCouponList.get(i).getAmount());
				cr.setC_serial_no(activeCouponList.get(i).getC_serial_no());
				
				selectedCoupon.add(cr);
			}
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String cr_date = TodayDate_YYYYMMDD.getToday();
		
		final String sql = "INSERT INTO coupondissemination(fo_id, c_id, cd_status, create_by, create_date) VALUES(?, ?, 'Active', ?, ?)";
		
		int insertCounter = 0;
		
		if(frightOrder.getTotal_coupon_amount() == coupon_amount){
			
			for(int i = 0; i < selectedCoupon.size(); i++){
				
				int u = jdbcTemplate.update(sql, new Object[]{ getLastInsertedFOID(), selectedCoupon.get(i).getC_id(), loggedInUserId, cr_date });
				
				if(u > 0){
					
					insertCounter++;
				}
			}
		}
		
		if(insertCounter > 0){
			rslt = true;
		}
		
		return rslt;
	}
	
	public boolean assignCouponDissemination(FrightOrder frightOrder){
		
		boolean rslt = false;
				
		//////**** to check already assigned coupon values **** //////
		final String getAssignedCouponList = "SELECT cd_id, c_id, fo_id, cd_status FROM CouponDissemination WHERE fo_id = ?";
		List<CouponDissemination> couponDissemination = jdbcTemplate.query(getAssignedCouponList, new Object[]{ frightOrder.getFo_id() }, new CouponDisseminationRowMapper());
		
		if(couponDissemination.size() > 0){
			return rslt;
		}
		
		double remain = 0, coupon_amount = 0;
		
		final String getActiveCouponList = "SELECT c_id, oil_company, amount, c_serial_no, c_status FROM couponregistration WHERE c_status = 'Active' and c_id not in (select c_id from coupondissemination) ORDER BY c_serial_no asc ";
		List<CouponRegistration> activeCouponList = jdbcTemplate.query(getActiveCouponList, new CouponRegistrationRowMapper());
		
		List<CouponRegistration> selectedCoupon = new ArrayList<CouponRegistration>();
		
		for(int i = 0; i < activeCouponList.size(); i++){
			
			remain = frightOrder.getTotal_coupon_amount() - coupon_amount;
			
			if(remain >= Integer.parseInt(activeCouponList.get(i).getAmount())){
				
				coupon_amount = coupon_amount + Integer.parseInt(activeCouponList.get(i).getAmount());
				
				CouponRegistration cr = new CouponRegistration();
				cr.setC_id(activeCouponList.get(i).getC_id());
				cr.setAmount(activeCouponList.get(i).getAmount());
				cr.setC_serial_no(activeCouponList.get(i).getC_serial_no());
				
				selectedCoupon.add(cr);
			}
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String cr_date = TodayDate_YYYYMMDD.getToday();
		
		final String sql = "INSERT INTO coupondissemination(fo_id, c_id, cd_status, create_by, create_date) VALUES(?, ?, 'Active', ?, ?)";
		
		int insertCounter = 0;
		
		if(frightOrder.getTotal_coupon_amount() == coupon_amount){
			
			for(int i = 0; i < selectedCoupon.size(); i++){
				
				int u = jdbcTemplate.update(sql, new Object[]{ frightOrder.getFo_id(), selectedCoupon.get(i).getC_id(), loggedInUserId, cr_date });
				
				if(u > 0){
					
					insertCounter++;
				}
			}
		}
		
		if(insertCounter > 0){
			rslt = true;
		}
		
		return rslt;
	}
	
	public boolean assignNotGivenCouponDissemination(FrightOrder frightOrder){
		
		boolean rslt = false;
		
		
		//////**** to check already assigned coupon values ****//////
		final String getAssignedCouponList = "SELECT a.cd_id, a.c_id, a.fo_id, a.cd_status, b.amount FROM CouponDissemination a, CouponRegistration b WHERE a.c_id = b.c_id and fo_id = ?";
		List<CouponDissemination> couponDissemination = jdbcTemplate.query(getAssignedCouponList, new Object[]{ frightOrder.getFo_id() }, new CouponDisseminationWithCouponTypeRowMapper());
		
		double total_assigned_c_amount = 0, remain_c_amount = 0;
		
		if(couponDissemination.size() > 0){
			
			for(int i = 0; i < couponDissemination.size(); i++){
				
				total_assigned_c_amount += Integer.parseInt(couponDissemination.get(i).getCouponRegistration().getAmount());
			}
			
			remain_c_amount = frightOrder.getTotal_coupon_amount() - total_assigned_c_amount;
			
			if(frightOrder.getTotal_coupon_amount() <= total_assigned_c_amount){
				
				return rslt;
			}
		}
				
		
		double remain = 0, coupon_amount = remain_c_amount;
		
		final String getActiveCouponList = "SELECT c_id, oil_company, amount, c_serial_no, c_status FROM couponregistration WHERE c_status = 'Active' and c_id not in (select c_id from coupondissemination) ORDER BY c_serial_no asc ";
		List<CouponRegistration> activeCouponList = jdbcTemplate.query(getActiveCouponList, new CouponRegistrationRowMapper());
		
		List<CouponRegistration> selectedCoupon = new ArrayList<CouponRegistration>();
		
		for(int i = 0; i < activeCouponList.size(); i++){
			
			remain = frightOrder.getTotal_coupon_amount() - coupon_amount;
			
			if(remain >= Integer.parseInt(activeCouponList.get(i).getAmount())){
				
				coupon_amount = coupon_amount + Integer.parseInt(activeCouponList.get(i).getAmount());
				
				CouponRegistration cr = new CouponRegistration();
				cr.setC_id(activeCouponList.get(i).getC_id());
				cr.setAmount(activeCouponList.get(i).getAmount());				
				cr.setC_serial_no(activeCouponList.get(i).getC_serial_no());
				
				selectedCoupon.add(cr);
			}
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String cr_date = TodayDate_YYYYMMDD.getToday();
		
		final String sql = "INSERT INTO coupondissemination(fo_id, c_id, c_onhand, cd_status, create_by, create_date) VALUES(?, ?, ?, 'Active', ?, ?)";
		
		int insertCounter = 0;
		
		if(frightOrder.getTotal_coupon_amount() == coupon_amount){
			
			for(int i = 0; i < selectedCoupon.size(); i++){
				
				int u = jdbcTemplate.update(sql, new Object[]{ frightOrder.getFo_id(), selectedCoupon.get(i).getC_id(), frightOrder.getCouponDissemination().getC_onhand(), loggedInUserId, cr_date });
				
				if(u > 0){
					
					insertCounter++;
				}
			}
		}
		
		if(insertCounter > 0){
			rslt = true;
		}
		
		return rslt;
	}
	
	public int getLastInsertedFOID(){
		
		final String frightOrderList = "SELECT max(a.fo_id) fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, a.commission "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and a.fo_id not in (select fo_id from frightordertripdetail)";
		
		List<FrightOrder> foList = jdbcTemplate.query(frightOrderList, new FrightOrderRowMapper());
		
		return foList.get(0).getFo_id();
	}
	
	@Override
	public boolean updateFrightOrderCommission(FrightOrder frightOrder) {
		
		final String inprocessFrightOrderList = "UPDATE frightorder SET commission = ? WHERE fo_id = ?";
		
		int rslt = jdbcTemplate.update(inprocessFrightOrderList, new Object[]{ frightOrder.getCommission(), frightOrder.getFo_id() });
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public List<FrightOrder> getFrightOrderInfoByFon(String fon) {
		
		if(!checkFrightOrderInfoByFon(fon)){
			
			List<FrightOrder> rslt = new ArrayList<FrightOrder>();
			
			return rslt;
		}
		
		final String frightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, (a.total_coupon_amount + sum(g.coupon_receive_amount)) as total_coupon_amount, a.date_from, a.date_to, a.fo_status, f.dispatch_doc_ref_no, f.delivery_doc_ref_no, f.delivered_quantity, f.origin_place, f.destination_place, f.client_organization, f.loading_type, f.loading_quantity, f.price, a.commission, f.payment_appointment_date, f.payment_request_date, sum(g.advance_payment_amount) advance_payment_amount, f.fotd_status "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e, frightordertripdetail f, advancepayment g "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and a.fo_id = g.fo_id and a.fon = ?";
		
		return jdbcTemplate.query(frightOrderList, new Object[]{ fon }, new FrightOrderWithFrightDetailAdvancePaymentRowMapper());
	}
	
	public boolean checkFrightOrderInfoByFon(String fon) {
		
		final String frightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, f.dispatch_doc_ref_no, f.delivery_doc_ref_no, f.delivered_quantity, f.origin_place, f.destination_place, f.client_organization, f.loading_type, f.loading_quantity, f.price, a.commission, f.payment_appointment_date, f.payment_request_date, g.advance_payment_amount, f.fotd_status "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e, frightordertripdetail f, advancepayment g "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and a.fo_id = g.fo_id and f.fotd_status = 'Closed' and a.fon = ?";
		
		List<FrightOrder> rslt = jdbcTemplate.query(frightOrderList, new Object[]{ fon }, new FrightOrderWithFrightDetailAdvancePaymentRowMapper());
		
		if(rslt.size() > 0){
			return true;
		}
		return false;
	}
	
	public boolean checkFrightOrderInfoByFonAndAssociation(String fon, int a_id) {
		
		final String frightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, f.dispatch_doc_ref_no, f.delivery_doc_ref_no, f.delivered_quantity, f.origin_place, f.destination_place, f.client_organization, f.loading_type, f.loading_quantity, f.price, a.commission, f.payment_appointment_date, f.payment_request_date, g.advance_payment_amount, f.fotd_status "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e, frightordertripdetail f, advancepayment g "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and a.fo_id = g.fo_id and a.fon = ? and a.a_id = ?";
		
		List<FrightOrder> rslt = jdbcTemplate.query(frightOrderList, new Object[]{ fon, a_id }, new FrightOrderWithFrightDetailAdvancePaymentRowMapper());
		
		if(rslt.size() > 0){
			return true;
		}
		return false;
	}
	
	public boolean checkUpdateFrightOrderInfoByFonAndAssociation(String fon, int a_id, int fo_id) {
		
		final String frightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, f.dispatch_doc_ref_no, f.delivery_doc_ref_no, f.delivered_quantity, f.origin_place, f.destination_place, f.client_organization, f.loading_type, f.loading_quantity, f.price, a.commission, f.payment_appointment_date, f.payment_request_date, g.advance_payment_amount, f.fotd_status "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e, frightordertripdetail f, advancepayment g "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and a.fo_id = g.fo_id and a.fon = ? and a.a_id = ? and a.fo_id <> ?";
		
		List<FrightOrder> rslt = jdbcTemplate.query(frightOrderList, new Object[]{ fon, a_id, fo_id }, new FrightOrderWithFrightDetailAdvancePaymentRowMapper());
		
		if(rslt.size() > 0){
			return true;
		}
		return false;
	}
	
	@Override
	public List<FrightOrder> getTruckDailyActivityList() {

		final String frightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, "
				+ "e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, f.dispatch_doc_ref_no, f.delivery_doc_ref_no, f.delivered_quantity, "
				+ "f.origin_place, f.destination_place, f.client_organization, f.loading_type, f.loading_quantity, f.price, a.commission, f.payment_appointment_date, f.payment_request_date, g.advance_payment_amount, "
				+ "f.fotd_status, c.side_no "
				+ "FROM associations b, trailinformation d, drivers e, frightordertripdetail f, truckinformation c LEFT JOIN frightorder a ON a.tri_id = c.tri_id LEFT JOIN advancepayment g ON a.fo_id = g.fo_id "
				+ "WHERE a.a_id = b.a_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id ORDER BY a.date_from, a.fo_id asc";
		
		//return jdbcTemplate.query(frightOrderList, new FrightOrderWithFrightDetailAdvancePaymentRowMapper());
		
		Query query = getSessionFactory().openSession().createSQLQuery(frightOrderList);
		
		List<FrightOrder> result_list = (List<FrightOrder>)query.list();
		
		List<FrightOrder> list = new ArrayList<>();
		
		for(Object obj : result_list){
			
			Object[] feild = (Object[])obj;
			
			FrightOrder fo = new FrightOrder();
			
			fo.setFo_id(Integer.parseInt(feild[0].toString()));
			fo.setFon(feild[1].toString());
			fo.setA_id(Integer.parseInt(feild[2].toString()));
			fo.setTri_id(Integer.parseInt(feild[4].toString()));
			fo.setTli_id(Integer.parseInt(feild[6].toString()));
			fo.setDr_id(Integer.parseInt(feild[8].toString()));
			fo.setTrip(feild[12].toString());
			fo.setTotal_coupon_amount(feild[13]==null?0.0:Double.parseDouble(feild[13].toString()));
			fo.setCommission(feild[26]==null?0.0:Double.parseDouble(feild[26].toString()));			
			fo.setDate_from(feild[14].toString());
			fo.setDate_to(feild[15].toString());			
			fo.setFo_status(feild[16].toString());
			
			Associations associations = new Associations();
			associations.setAssociation_name(feild[3].toString());
			fo.setAssociations(associations);
									
			TruckInformation truckInformation = new TruckInformation();
			truckInformation.setTruck_plate_no(feild[5].toString());
			truckInformation.setSide_no(feild[31].toString());
			fo.setTruckInformation(truckInformation);				
						
			TrailInformation trailInformation = new TrailInformation();
			trailInformation.setTrail_plate_no(feild[8].toString());
			fo.setTrailInformation(trailInformation);
			
			Drivers drivers = new Drivers();
			drivers.setFname(feild[9].toString());
			drivers.setMname(feild[10].toString());
			drivers.setGname(feild[11].toString());
			drivers.setFullName(feild[9].toString() + " " + feild[10].toString() + " " + feild[11].toString());
			fo.setDrivers(drivers);
						
			FrightOrderTripDetail fotd = new FrightOrderTripDetail();
			fotd.setDispatch_doc_ref_no(feild[17]==null?"":feild[17].toString());
			fotd.setDelivery_doc_ref_no(feild[18]==null?"":feild[18].toString());
			fotd.setDelivered_quantity(feild[19]==null?0.0:Double.parseDouble(feild[19].toString()));
			fotd.setOrigin_place(feild[20].toString());
			fotd.setDestination_place(feild[21].toString());
			fotd.setClient_organization(feild[22].toString());
			fotd.setLoading_type(feild[23].toString());
			fotd.setLoading_quantity(feild[24].toString());
			fotd.setPrice(Double.parseDouble(feild[25].toString()));
			
			if(feild[27] != null){
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date request_date;
				try {
					
					request_date = df.parse(feild[27].toString());
					fotd.setPayment_request_date(request_date);
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			fotd.setPayment_appointment_date(feild[28]==null?"":feild[28].toString());
			fotd.setFotd_status(feild[30].toString());		
			fo.setFrightOrderTripDetail(fotd);
			
			AdvancePayment ap = new AdvancePayment();
			ap.setAdvance_payment_amount(feild[29]==null?0.0:Double.parseDouble((String.format("%.2f", Double.parseDouble(feild[29].toString())))));
			fo.setAdvancePayment(ap);
			
			list.add(fo);
		}
		
		return list;
	}
	
	@Override
	public List<FrightOrder> getTruckDailyActivityListByTruckType(String truck_type) {
		
		String truckType = "";
		
		if(truck_type.equals("BOTH")){
			
			truckType = "'DRY', 'FLUID'";
		} else {
			
			truckType = "'" + truck_type + "'";
		}

		final String frightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, f.dispatch_doc_ref_no, f.delivery_doc_ref_no, f.delivered_quantity, f.origin_place, f.destination_place, f.client_organization, f.loading_type, f.loading_quantity, f.price, a.commission, f.payment_appointment_date, f.payment_request_date, g.advance_payment_amount, f.fotd_status, c.side_no "
				+ "FROM associations b, trailinformation d, drivers e, frightordertripdetail f, truckinformation c LEFT JOIN frightorder a ON a.tri_id = c.tri_id LEFT JOIN advancepayment g ON a.fo_id = g.fo_id "
				+ "WHERE a.a_id = b.a_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and c.truck_type in (" + truckType + ") "
				+ "ORDER BY a.date_from, a.fo_id asc";
		
		//return jdbcTemplate.query(frightOrderList, new FrightOrderWithFrightDetailAdvancePaymentRowMapper());
		
		Query query = getSessionFactory().openSession().createSQLQuery(frightOrderList);
		
		List<FrightOrder> result_list = (List<FrightOrder>)query.list();
		
		List<FrightOrder> list = new ArrayList<>();
		
		for(Object obj : result_list){
			
			Object[] feild = (Object[])obj;
			
			FrightOrder fo = new FrightOrder();
			
			fo.setFo_id(Integer.parseInt(feild[0].toString()));
			fo.setFon(feild[1].toString());
			fo.setA_id(Integer.parseInt(feild[2].toString()));
			fo.setTri_id(Integer.parseInt(feild[4].toString()));
			fo.setTli_id(Integer.parseInt(feild[6].toString()));
			fo.setDr_id(Integer.parseInt(feild[8].toString()));
			fo.setTrip(feild[12].toString());
			fo.setTotal_coupon_amount(feild[13]==null?0.0:Double.parseDouble(feild[13].toString()));
			fo.setCommission(feild[26]==null?0.0:Double.parseDouble(feild[26].toString()));			
			fo.setDate_from(feild[14].toString());
			fo.setDate_to(feild[15].toString());			
			fo.setFo_status(feild[16].toString());
			
			Associations associations = new Associations();
			associations.setAssociation_name(feild[3].toString());
			fo.setAssociations(associations);
									
			TruckInformation truckInformation = new TruckInformation();
			truckInformation.setTruck_plate_no(feild[5].toString());
			truckInformation.setSide_no(feild[31].toString());
			fo.setTruckInformation(truckInformation);				
						
			TrailInformation trailInformation = new TrailInformation();
			trailInformation.setTrail_plate_no(feild[8].toString());
			fo.setTrailInformation(trailInformation);
			
			Drivers drivers = new Drivers();
			drivers.setFname(feild[9].toString());
			drivers.setMname(feild[10].toString());
			drivers.setGname(feild[11].toString());
			drivers.setFullName(feild[9].toString() + " " + feild[10].toString() + " " + feild[11].toString());
			fo.setDrivers(drivers);
						
			FrightOrderTripDetail fotd = new FrightOrderTripDetail();
			fotd.setDispatch_doc_ref_no(feild[17]==null?"":feild[17].toString());
			fotd.setDelivery_doc_ref_no(feild[18]==null?"":feild[18].toString());
			fotd.setDelivered_quantity(feild[19]==null?0.0:Double.parseDouble(feild[19].toString()));
			fotd.setOrigin_place(feild[20].toString());
			fotd.setDestination_place(feild[21].toString());
			fotd.setClient_organization(feild[22].toString());
			fotd.setLoading_type(feild[23].toString());
			fotd.setLoading_quantity(feild[24].toString());
			fotd.setPrice(Double.parseDouble(feild[25].toString()));
			
			if(feild[27] != null){
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date request_date;
				try {
					
					request_date = df.parse(feild[27].toString());
					fotd.setPayment_request_date(request_date);
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			fotd.setPayment_appointment_date(feild[28]==null?"":feild[28].toString());
			fotd.setFotd_status(feild[30].toString());		
			fo.setFrightOrderTripDetail(fotd);
			
			AdvancePayment ap = new AdvancePayment();
			ap.setAdvance_payment_amount(feild[29]==null?0.0:Double.parseDouble((String.format("%.2f", Double.parseDouble(feild[29].toString())))));
			fo.setAdvancePayment(ap);
			
			list.add(fo);
		}
		
		return list;
	}
		

	@Override
	public List<FrightOrder> getLastFrightOrderInserted() {
		
		final String frightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, a.commission "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and a.fo_id = (select max(fo_id) from frightorder)";
		
		return jdbcTemplate.query(frightOrderList, new FrightOrderRowMapper());
	}
		
	@Override
	public void insertTrackReserve(FrightOrder frightOrder) {
		
		try{
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");				
			Date date_f = df.parse(frightOrder.getDate_from().toString());
			Date date_t = df.parse(frightOrder.getDate_to().toString());
			
			final String inprocessFrightOrderList = "UPDATE truckavailabilitystatus SET fo_id = ?, date_from = ?, date_to = ?, tas_status = 'OC' WHERE tri_id = ?";
			
			jdbcTemplate.update(inprocessFrightOrderList, new Object[]{ frightOrder.getFo_id(), date_f, date_t, frightOrder.getTri_id() });
		
		} catch(Exception e){
			
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateTrackReserve(FrightOrder frightOrder) {
		
		try{
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");				
			Date date_f = df.parse(frightOrder.getDate_from().toString());
			Date date_t = df.parse(frightOrder.getDate_to().toString());
			
			final String sql1 = "UPDATE truckavailabilitystatus SET fo_id = ?, date_from = ?, date_to = ?, tas_status = 'OC' WHERE tri_id = ?";
			
			jdbcTemplate.update(sql1, new Object[]{ frightOrder.getFo_id(), date_f, date_t, frightOrder.getTri_id() });
			
			final String sql2 = "UPDATE truckavailabilitystatus SET fo_id = ?, date_from = ?, date_to = ?, tas_status = 'AV' WHERE tri_id = ?";
			
			jdbcTemplate.update(sql2, new Object[]{ null, null, null, frightOrder.getOld_tri_id() });
		
		} catch(Exception e){
			
			e.printStackTrace();
		}
	}
	
	@Override
	public void insertTrailReserve(FrightOrder frightOrder) {

		try{
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");				
			Date date_f = df.parse(frightOrder.getDate_from().toString());
			Date date_t = df.parse(frightOrder.getDate_to().toString());
			
			final String inprocessFrightOrderList = "UPDATE trailavailabilitystatus SET fo_id = ?, date_from = ?, date_to = ?, tlas_status = 'OC' WHERE tli_id = ?";
			
			jdbcTemplate.update(inprocessFrightOrderList, new Object[]{ frightOrder.getFo_id(), date_f, date_t, frightOrder.getTli_id() });
			
		} catch(Exception e){
			
			e.printStackTrace();
		}
	}

	@Override
	public void updateTrailReserve(FrightOrder frightOrder) {
		
		try{
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");				
			Date date_f = df.parse(frightOrder.getDate_from().toString());
			Date date_t = df.parse(frightOrder.getDate_to().toString());
			
			final String sql1 = "UPDATE trailavailabilitystatus SET fo_id = ?, date_from = ?, date_to = ?, tlas_status = 'OC' WHERE tli_id = ?";
			
			jdbcTemplate.update(sql1, new Object[]{ frightOrder.getFo_id(), date_f, date_t, frightOrder.getTli_id() });
			
			final String sql2 = "UPDATE trailavailabilitystatus SET fo_id = ?, date_from = ?, date_to = ?, tlas_status = 'AV' WHERE tli_id = ?";
			
			jdbcTemplate.update(sql2, new Object[]{ null, null, null, frightOrder.getOld_tli_id() });
			
		} catch(Exception e){
			
			e.printStackTrace();
		}
	}	

	@Override
	public List<FrightOrder> getActiveFrightOrderListWithPriceZero(AdvancePayment advancePayment) {
		
		final String frightOrderList = "SELECT distinct a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, a.commission "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e, frightordertripdetail f, advancepayment g "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and a.fo_id = g.fo_id and g.return_amount is null and g.additional_amount is null and a.fo_id <> ? "
				+ "ORDER BY a.fon asc";
		//fo_id not in (select fo_id from advance_payment where g.return_amount is not null and g.additional_amount is not null) fo_status = 'Closed'
		return jdbcTemplate.query(frightOrderList, new Object[]{ advancePayment.getFo_id() }, new FrightOrderRowMapper());
	}


	@Override
	public List<FrightOrder> getActiveFrightOrderList(AdvancePayment advancePayment) {
		/*
		 * >>> used to get those closed freight orders that are served with the same truck
		 * 	   and don't have registered expense (other than commission expense - 'et_id <> 16') ready to accept transfer amount of money
		 */		
		final String frightOrderList = "SELECT distinct a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, a.commission "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e, frightordertripdetail f "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and f.fotd_status = 'Closed' and "
				+ "a.fo_id not in (SELECT c.`fo_id` FROM advancepayment c where (c.additional_amount IS not NULL or c.return_amount IS not NULL)) and "
				+ "c.tri_id in (SELECT e.tri_id FROM frightorder e WHERE e.fo_id = ?) and "
				+ "a.fon <> (select g.fon from frightorder g where g.fo_id = ? and a.fo_id not in (select fo_id from expense where et_id <> 16)) "
				+ "ORDER BY a.fon";
		
		return jdbcTemplate.query(frightOrderList, new Object[]{ advancePayment.getFo_id(), advancePayment.getFo_id() }, new FrightOrderRowMapper());
	}
	
	@Override
	public List<FrightOrder> getTransactionListPerTruck(FrightOrder frightOrder) {

		final String frightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, f.dispatch_doc_ref_no, f.delivery_doc_ref_no, f.delivered_quantity, f.origin_place, f.destination_place, f.client_organization, f.loading_type, f.loading_quantity, f.price, a.commission, f.payment_appointment_date, f.payment_request_date, sum(g.advance_payment_amount) advance_payment_amount, f.fotd_status "
				+ "FROM associations b, trailinformation d, drivers e, frightordertripdetail f, truckinformation c LEFT JOIN frightorder a ON a.tri_id = c.tri_id LEFT JOIN advancepayment g ON a.fo_id = g.fo_id "
				+ "WHERE a.a_id = b.a_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and c.tri_id = ? and a.date_from >= ? and a.date_from <= ? "
				+ "GROUP BY a.fo_id ORDER BY a.date_from";
		
		return jdbcTemplate.query(frightOrderList, new Object[]{ frightOrder.getTri_id(), frightOrder.getDate_from(), frightOrder.getDate_to() }, new FrightOrderWithFrightDetailAdvancePaymentRowMapper());
	}
	
	@Override
	public List<FrightOrder> getClosedFrightOrderList() {
		
		final String frightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, f.dispatch_doc_ref_no, f.delivery_doc_ref_no, f.delivered_quantity, f.origin_place, f.destination_place, f.client_organization, f.loading_quantity, f.price, f.crv_number, a.commission, f.payment_appointment_date, f.payment_request_date, f.close_date, c.truck_owner "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e, frightordertripdetail f "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and f.fotd_status = 'Closed'";
		
		return jdbcTemplate.query(frightOrderList, new FrightOrderWithFrightDetailRowMapper());
	}
	
	@Override
	public List<FrightOrder> getLoadingUnloadingDifferencePerTruck(FrightOrder frightOrder) {
		
		final String frightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, f.dispatch_doc_ref_no, f.delivery_doc_ref_no, f.delivered_quantity, f.origin_place, f.destination_place, f.client_organization, f.loading_type, f.loading_quantity, f.price, a.commission, f.payment_appointment_date, f.payment_request_date, sum(g.advance_payment_amount) advance_payment_amount, f.fotd_status "
				+ "FROM associations b, trailinformation d, drivers e, frightordertripdetail f, truckinformation c LEFT JOIN frightorder a ON a.tri_id = c.tri_id LEFT JOIN advancepayment g ON a.fo_id = g.fo_id "
				+ "WHERE a.a_id = b.a_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and c.tri_id = ? and a.date_from >= ? and a.date_from <= ? "
				+ "GROUP BY a.fo_id ORDER BY a.date_from";
		
		return jdbcTemplate.query(frightOrderList, new Object[]{ frightOrder.getTri_id(), frightOrder.getDate_from(), frightOrder.getDate_to() }, new FrightOrderWithFrightDetailAdvancePaymentRowMapper());
	}
	
	
	
	public java.sql.Date convertJavaDateToSqlDate(java.util.Date date){
		return new java.sql.Date(date.getTime());
	}
	
	/*
	 * setter and getter for DataSource and JdbcTemplate 
	 * setDataSource is updated to init the jdbcTemplate
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
