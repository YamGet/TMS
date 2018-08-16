package org.fidel.tms.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.fidel.tms.dao.PaymentDao;
import org.fidel.tms.mapper.FrightOrderCollectedPaymentMapper;
import org.fidel.tms.mapper.FrightOrderRowMapper;
import org.fidel.tms.mapper.FrightOrderWithFrightDetailRowMapper;
import org.fidel.tms.model.AdvancePayment;
import org.fidel.tms.model.Associations;
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
public class PaymentDaoImpl implements PaymentDao {
	
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<FrightOrder> getUnprocessedPaymentList() {
		
		final String unprocessedfrightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, f.dispatch_doc_ref_no, f.delivery_doc_ref_no, f.delivered_quantity, f.origin_place, f.destination_place, f.client_organization, f.loading_quantity, f.price, f.crv_number, a.commission, f.payment_appointment_date, f.payment_request_date, f.close_date, c.truck_owner "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e, frightordertripdetail f "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and f.fotd_status = 'Closed' and a.fo_id not in (select fo_id from payment) and f.payment_request_date is null and f.delivered_quantity > 0.0";
		
		return jdbcTemplate.query(unprocessedfrightOrderList, new FrightOrderWithFrightDetailRowMapper());
	}
	
	@Override
	public List<FrightOrder> getUnprocessedPaymentListByTruckType(String truck_type) {
		
		String truckType = "";
		
		if(truck_type.equals("BOTH")){
			
			truckType = "'DRY', 'FLUID'";
		} else {
			
			truckType = "'" + truck_type + "'";
		}
		
		final String unprocessedfrightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, f.dispatch_doc_ref_no, f.delivery_doc_ref_no, f.delivered_quantity, f.origin_place, f.destination_place, f.client_organization, f.loading_quantity, f.price, f.crv_number, a.commission, f.payment_appointment_date, f.payment_request_date, f.close_date, c.truck_owner "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e, frightordertripdetail f "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and f.fotd_status = 'Closed' and a.fo_id not in (select fo_id from payment) and f.payment_request_date is null and f.delivered_quantity > 0.0 and c.truck_type in (" + truckType + ")";
		
		return jdbcTemplate.query(unprocessedfrightOrderList, new FrightOrderWithFrightDetailRowMapper());
	}
	
	@Override
	public List<FrightOrder> getInprocessPaymentList() {
		
		final String inprocessFrightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, "
				+ "e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, f.dispatch_doc_ref_no, f.delivery_doc_ref_no, f.delivered_quantity, "
				+ "f.origin_place, f.destination_place, f.client_organization, f.loading_type, f.loading_quantity, f.price, a.commission, f.payment_appointment_date, f.payment_request_date, "
				+ "f.close_date, c.truck_owner, f.crv_number, c.truck_type "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e, frightordertripdetail f "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and f.fotd_status = 'Closed' and a.fo_id not in (select fo_id from payment) and f.payment_request_date is not null and f.delivered_quantity > 0.0"
				+ "ORDER BY a.fon";
		
		//return jdbcTemplate.query(inprocessFrightOrderList, new FrightOrderWithFrightDetailRowMapper());
		
		Query query = getSessionFactory().openSession().createSQLQuery(inprocessFrightOrderList);
		
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
			truckInformation.setTruck_owner(feild[30].toString());
			truckInformation.setTruck_type(feild[32].toString());
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
			fotd.setCrv_number(feild[31]==null?"":feild[31].toString());
			fotd.setPrice(Double.parseDouble(feild[25].toString()));
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			if(feild[28] != null){
				
				Date request_date;
				try {
					
					request_date = df.parse(feild[28].toString());
					fotd.setPayment_request_date(request_date);
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(feild[29] != null){
				
				Date close_date;
				try {
					
					close_date = df.parse(feild[29].toString());
					fotd.setClose_date(close_date);
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}			
			fo.setFrightOrderTripDetail(fotd);
			
			list.add(fo);
		}
		
		return list;
	}
	
	@Override
	public List<FrightOrder> getInprocessPaymentListByTruckType(String truck_type) {

		String truckType = "";
		
		if(truck_type.equals("BOTH")){
			
			truckType = "'DRY', 'FLUID'";
		} else {
			
			truckType = "'" + truck_type + "'";
		}

		final String inprocessFrightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, f.dispatch_doc_ref_no, f.delivery_doc_ref_no, f.delivered_quantity, f.origin_place, f.destination_place, f.client_organization, f.loading_quantity, f.price, f.crv_number, a.commission, f.payment_appointment_date, f.payment_request_date, f.close_date, c.truck_owner "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e, frightordertripdetail f "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and f.fotd_status = 'Closed' and a.fo_id not in (select fo_id from payment) and f.payment_request_date is not null and f.delivered_quantity > 0.0 and c.truck_type in (" + truckType + ") "
				+ "ORDER BY a.fon";
		
		return jdbcTemplate.query(inprocessFrightOrderList, new FrightOrderWithFrightDetailRowMapper());
	}

	@Override
	public List<FrightOrder> getFrightOrderInfo(int fo_id) {

		final String inprocessFrightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, f.dispatch_doc_ref_no, f.delivery_doc_ref_no, f.delivered_quantity, f.origin_place, f.destination_place, f.client_organization, f.loading_quantity, f.price, f.crv_number, a.commission, f.payment_appointment_date, f.payment_request_date, f.close_date, c.truck_owner "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e, frightordertripdetail f "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and f.fotd_status = 'Closed' and a.fo_id not in (select fo_id from payment) and f.fo_id = ?";
		
		return jdbcTemplate.query(inprocessFrightOrderList, new Object[]{ fo_id }, new FrightOrderWithFrightDetailRowMapper());
	}
	
	@Override
	public boolean updateFrightOrderPaymentRequestDate(int fo_id) {
		
		final String inprocessFrightOrderList = "UPDATE frightordertripdetail SET payment_request_date = '" +  TodayDate_YYYYMMDD.getToday()  + "' WHERE fo_id = ?";
		
		int rslt = jdbcTemplate.update(inprocessFrightOrderList, new Object[]{ fo_id });
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean saveFrightOrderPayment(List<Integer> foid, Payment payment) {
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String create_date = TodayDate_YYYYMMDD.getToday();
		
		int rslt = 0;
				
		final String sql = "INSERT INTO payment(fo_id, payment_type, payment_doc_ref_no, payment_amount, payment_date, payment_status, create_by, create_date) VALUES(?, ?, ?, ?, ?, 'Active', ?, ?)";
		
		for(int i = 0; i < foid.size(); i++){
			
			rslt = jdbcTemplate.update(sql, new Object[]{ foid.get(i), payment.getPayment_type(), payment.getPayment_doc_ref_no(), payment.getPayment_amount(), payment.getPayment_date(), loggedInUserId, create_date });
		}
		
		if(rslt > 0){
			
			////**** to register commission expense ****////			
			Query query1 = getSessionFactory().openSession().createQuery("FROM ExpenseType WHERE account_number = :account_number");
			query1.setParameter("account_number", "7014");
			List<ExpenseType> exTypeList = (List<ExpenseType>)query1.list();
			
			int counter = 0;
			
			for(int i = 0; i < foid.size(); i++){
				
				List<FrightOrder> frightOrderInfo = getFrightOrderInfoByIdForExpense(foid.get(i));
				
				Double expenseAmount = frightOrderInfo.get(0).getFrightOrderTripDetail().getDelivered_quantity() * frightOrderInfo.get(0).getFrightOrderTripDetail().getPrice() * frightOrderInfo.get(0).getCommission();
				
				Query query = getSessionFactory().openSession().createSQLQuery("INSERT INTO Expense(fo_id, et_id, expense_amount, create_by, create_date) VALUES(:fo_id, :et_id, :expense_amount, :create_by, :create_date)");
				query.setParameter("fo_id", foid.get(i));
				query.setParameter("et_id", exTypeList.get(0).getEt_id());
				query.setParameter("expense_amount", expenseAmount);
				query.setParameter("create_by", loggedInUserId);
				query.setParameter("create_date", create_date);
				int val = query.executeUpdate();
			
				if(val > 0){
					counter++;
				}
			}
			
			if(counter > 0){
				return true;
			}
			return false;
			////**** to register commission expense ****////
			
		} else {
			
			return false;
		}
	}
	
	public List<FrightOrder> getFrightOrderInfoByIdForExpense(int fo_id) {

		final String inprocessFrightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status, f.dispatch_doc_ref_no, f.delivery_doc_ref_no, f.delivered_quantity, f.origin_place, f.destination_place, f.client_organization, f.loading_quantity, f.price, f.crv_number, a.commission, f.payment_appointment_date, f.payment_request_date, f.close_date, c.truck_owner "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e, frightordertripdetail f "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and f.fotd_status = 'Closed' and f.fo_id = ?";
		
		return jdbcTemplate.query(inprocessFrightOrderList, new Object[]{ fo_id }, new FrightOrderWithFrightDetailRowMapper());
	}
	
	@Override
	public List<FrightOrder> getCollectedPaymentReport(FrightOrder frightOrder) {

		String truckType = "";
		
		if(frightOrder.getTruckInformation().getTruck_type().equals("BOTH")){
			
			truckType = "'DRY', 'FLUID'";
		} else {
			
			truckType = "'" + frightOrder.getTruckInformation().getTruck_type() + "'";
		}

		final String collectedPayment = "SELECT a.fo_id, a.client_organization, c.fon, a.price, a.delivered_quantity, ((a.price * a.delivered_quantity) - (a.price * a.delivered_quantity * c.commission)) as payment_amount, c.commission, b.payment_type, b.payment_doc_ref_no, b.payment_date, d.truck_plate_no, a.crv_number, a.origin_place, a.destination_place "
										+ "FROM frightordertripdetail a, payment b, frightorder c, truckinformation d "
										+ "WHERE a.fo_id = b.fo_id and a.fo_id = c.fo_id and c.tri_id = d.tri_id and payment_date between ? and ? and (a.price * a.delivered_quantity) > 0.0 and d.truck_type in (" + truckType + ")";
		
		return jdbcTemplate.query(collectedPayment, new Object[]{ frightOrder.getDate_from(), frightOrder.getDate_to() }, new FrightOrderCollectedPaymentMapper());
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

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
}
