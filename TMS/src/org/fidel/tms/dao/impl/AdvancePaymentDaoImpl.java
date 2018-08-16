package org.fidel.tms.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.fidel.tms.dao.AdvancePaymentDao;
import org.fidel.tms.mapper.AdvancePaymentRowMapper;
import org.fidel.tms.mapper.AdvancePaymentWithCouponTransferRowMapper;
import org.fidel.tms.model.AdvancePayment;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdvancePaymentDaoImpl implements AdvancePaymentDao {
	
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean insertAdvancePayment(AdvancePayment advancePayment) {
				
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String cr_date = TodayDate_YYYYMMDD.getToday();

		String sql = "INSERT INTO advancepayment(fo_id, advance_payment_amount, ap_status, create_by, create_date) VALUES(?, ?, 'Active', ?, ?)";
		
		int rslt = jdbcTemplate.update(sql, new Object[] { advancePayment.getFo_id(), advancePayment.getAdvance_payment_amount(), loggedInUserId, cr_date });
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean insertAdvancePaymentAdditionalAmount(AdvancePayment advancePayment) {
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String cr_date = TodayDate_YYYYMMDD.getToday();

		String sql = "INSERT INTO advancepayment(fo_id, advance_payment_amount, send_reference_number, send_date, ap_status, create_by, create_date) VALUES(?, ?, ?, ?, 'Active', ?, ?)";
		
		int rslt = jdbcTemplate.update(sql, new Object[] { advancePayment.getFo_id(), advancePayment.getAdvance_payment_amount(), advancePayment.getSend_reference_number(), advancePayment.getSend_date(), loggedInUserId, cr_date });
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean updateAdvancePayment(AdvancePayment advancePayment) {
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String update_date = TodayDate_YYYYMMDD.getToday();

		String sql = "UPDATE advancepayment SET advance_payment_amount = ?, update_by = ?, update_date = ? WHERE ap_id = ?";
		
		int rslt = jdbcTemplate.update(sql, new Object[] { advancePayment.getAdvance_payment_amount(), loggedInUserId, update_date, advancePayment.getAp_id() });
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<AdvancePayment> getAdvancePayment(int fo_id, int ap_id) {
		
		String hql = "SELECT ap_id, fo_id, return_amount, additional_amount, advance_payment_amount, send_reference_number, send_date FROM AdvancePayment WHERE fo_id = ? and ap_id = ? and ap_status = 'Active'";
		
		return jdbcTemplate.query(hql, new Object[]{ fo_id, ap_id }, new AdvancePaymentRowMapper());
	}
	
	@Override
	public List<AdvancePayment> getAdvancePaymentByFOId(int fo_id) {

		String hql = "SELECT ap_id, fo_id, return_amount, additional_amount, advance_payment_amount, send_reference_number, send_date FROM AdvancePayment WHERE fo_id = ? and ap_status = 'Active'";
		
		return jdbcTemplate.query(hql, new Object[]{fo_id}, new AdvancePaymentRowMapper());
	}
	
	@Override
	public List<AdvancePayment> getAdvancePaymentWithCouponTransferByFOId(int fo_id) {

		String hql = "SELECT ap_id, fo_id, return_amount, additional_amount, advance_payment_amount, send_reference_number, send_date, coupon_transfer_amount, coupon_receive_amount FROM AdvancePayment WHERE fo_id = ? and ap_status = 'Active'";
		
		return jdbcTemplate.query(hql, new Object[]{fo_id}, new AdvancePaymentWithCouponTransferRowMapper());
	}
	
	@Override
	public List<AdvancePayment> getAdvancePaymentById(AdvancePayment advancePayment) {

		String hql = "SELECT ap_id, fo_id, return_amount, additional_amount, advance_payment_amount, send_reference_number, send_date FROM AdvancePayment WHERE ap_id = ? and ap_status = 'Active'";
		
		return jdbcTemplate.query(hql, new Object[]{advancePayment.getAp_id()}, new AdvancePaymentRowMapper());
	}
	
	@Override
	public boolean saveRemainingBalanceReturn(AdvancePayment advancePayment) {

		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String update_date = TodayDate_YYYYMMDD.getToday();

		String sql = "UPDATE advancepayment SET return_amount = ?, return_pay_date = ?, update_by = ?, update_date = ? WHERE ap_id = ?";
		
		int rslt = jdbcTemplate.update(sql, new Object[] { advancePayment.getReturn_amount(), update_date, loggedInUserId, update_date, advancePayment.getAp_id() });
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean saveRemainingBalanceTransfer(AdvancePayment advancePayment) {
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String update_date = TodayDate_YYYYMMDD.getToday();

		String sql = "";
				
		if(advancePayment.getReturn_amount() < 0.0){
			
			sql = "UPDATE advancepayment SET additional_amount = ?, coupon_transfer_amount = ?, return_pay_date = ?, update_by = ?, update_date = ? WHERE ap_id = ?";
		} else {
			
			sql = "UPDATE advancepayment SET return_amount = ?, coupon_transfer_amount = ?, return_pay_date = ?, update_by = ?, update_date = ? WHERE ap_id = ?";
		}
		
		int rslt = jdbcTemplate.update(sql, new Object[] { advancePayment.getReturn_amount(), advancePayment.getCoupon_transfer_amount(), update_date, loggedInUserId, update_date, advancePayment.getAp_id() });
		
		if(rslt > 0){
			
			List<AdvancePayment> advPaymentList = getAdvancePaymentById(advancePayment);
			
			String sql2 = "INSERT INTO advancepayment(fo_id, advance_payment_amount, coupon_receive_amount, parent_fo_id, transfer_date, ap_status, create_by, create_date) VALUES(?, ?, ?, ?, ?, 'Active', ?, ?)";
			
			int rslt2 = 0;
			
			if(advancePayment.getReturn_amount() < 0.0){
				
				rslt2 = jdbcTemplate.update(sql2, new Object[] { advancePayment.getFo_id(), 0, advancePayment.getCoupon_transfer_amount(), advPaymentList.get(0).getFo_id(), update_date, loggedInUserId, update_date });
			} else {
				
				rslt2 = jdbcTemplate.update(sql2, new Object[] { advancePayment.getFo_id(), advancePayment.getReturn_amount(), advancePayment.getCoupon_transfer_amount(), advPaymentList.get(0).getFo_id(), update_date, loggedInUserId, update_date });
			}
					
			
			if(rslt2 > 0){	
				
				return true;
				
			} else {
				
				String sql3 = "UPDATE advancepayment SET return_amount = ?, coupon_transfer_amount = ?, return_pay_date = ?, update_by = ?, update_date = ? WHERE ap_id = ?";
				
				jdbcTemplate.update(sql3, new Object[] { null, null, null, null, null, advancePayment.getAp_id() });
								
				return false;
			}
			
		} else {
			
			return false;
		}
	}
	
	@Override
	public boolean updateAdvancePaymentCouponStatus(AdvancePayment advancePayment) {

		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String update_date = TodayDate_YYYYMMDD.getToday();
		
		String sql = "UPDATE advancepayment SET coupon_status = ?, update_by = ?, update_date = ? WHERE fo_id = ? and coupon_receive_amount <> 0";
		
		int rslt = jdbcTemplate.update(sql, new Object[] { "END", loggedInUserId, update_date, advancePayment.getFo_id() });
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean saveAdditionalExpenseAmount(AdvancePayment advancePayment) {

		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String update_date = TodayDate_YYYYMMDD.getToday();

		String sql = "UPDATE advancepayment SET additional_amount = ?, return_pay_date = ?, update_by = ?, update_date = ? WHERE ap_id = ?";
		
		int rslt = jdbcTemplate.update(sql, new Object[] { advancePayment.getAdditional_amount(), update_date, loggedInUserId, update_date, advancePayment.getAp_id() });
		
		if(rslt > 0){
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
