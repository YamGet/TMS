package org.fidel.tms.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.fidel.tms.dao.CouponRegistrationDao;
import org.fidel.tms.mapper.CouponConsumedRowMapper;
import org.fidel.tms.mapper.CouponRegistrationCategoryRowMapper;
import org.fidel.tms.mapper.CouponRegistrationRowMapper;
import org.fidel.tms.model.CouponDissemination;
import org.fidel.tms.model.CouponRegistration;
import org.fidel.tms.utils.ReturnCurrentEthiopianYear;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CouponRegistrationDaoImpl implements CouponRegistrationDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;

	@Override
	public List<CouponRegistration> getActiveCoupon() {
		
		final String sql = "SELECT c_id, oil_company, amount, c_serial_no, c_status FROM CouponRegistration a WHERE a.c_status = 'Active' and c_id not in (select c_id from coupondissemination)";
		
		return jdbcTemplate.query(sql, new CouponRegistrationRowMapper());
	}
	
	@Override
	public List<CouponRegistration> getSelectedActiveCoupon(CouponRegistration coupon) {
		
		Query query = getSessionFactory().openSession().createQuery("FROM CouponRegistration a WHERE a.c_status = 'Active' and a.amount = :amount ORDER BY oil_company, c_serial_no LIMIT " + coupon.getNo_of_coupon());
		query.setParameter("amount", coupon.getAmount());
		
		return (List<CouponRegistration>)query.list();
	}

	@Override
	public boolean saveSingleCoupon(CouponRegistration coupon) {
		
		if(checkCoupon(coupon.getOil_company(), coupon.getC_serial_no())){
			return false;
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String cr_date = TodayDate_YYYYMMDD.getToday();
		
		Query query = getSessionFactory().openSession().createSQLQuery("INSERT INTO CouponRegistration(oil_company, c_serial_no, amount, c_status, create_by, create_date) VALUES(:oil_company, :c_serial_no, :amount, 'Active', :create_by, :create_date)");
		query.setParameter("oil_company", coupon.getOil_company());
		query.setParameter("c_serial_no", coupon.getC_serial_no());
		query.setParameter("amount", coupon.getAmount());
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
	public boolean saveMultipleCoupons(CouponRegistration coupon) {
		
		if(checkCoupon(coupon.getOil_company(), coupon.getC_serial_no())){
			return false;
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		int counter = 0, size = Integer.parseInt(coupon.getNo_of_coupon());
		
		Long serial_no = Long.parseLong(coupon.getC_serial_no_from());
		
		String cr_date = TodayDate_YYYYMMDD.getToday();
		
		Query query = getSessionFactory().openSession().createSQLQuery("INSERT INTO CouponRegistration(oil_company, c_serial_no, amount, c_status, create_by, create_date) VALUES(:oil_company, :c_serial_no, :amount, 'Active', :create_by, :create_date)");
				
		for(int i = 0; i < size; i++){
			
			query.setParameter("oil_company", coupon.getOil_company());
			query.setParameter("c_serial_no", String.valueOf(serial_no + i));
			query.setParameter("amount", coupon.getAmount());
			query.setParameter("create_by", loggedInUserId);
			query.setParameter("create_date", cr_date);
			int rslt = query.executeUpdate();
			
			if(rslt > 0){
				counter++;
			} 
		}
		
		if(counter == Integer.parseInt(coupon.getNo_of_coupon())){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateCoupon(CouponRegistration coupon) {
		
		if(checkCoupon(coupon.getOil_company(), coupon.getC_serial_no(), coupon.getC_id())){
			return false;
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String update_date = TodayDate_YYYYMMDD.getToday();
		
		Query query = getSessionFactory().openSession().createSQLQuery("UPDATE CouponRegistration SET oil_company = :oil_company, c_serial_no = :c_serial_no, amount = :amount, c_status = :c_status, update_by = :update_by, update_date = :update_date WHERE c_id = :c_id");
		query.setParameter("oil_company", coupon.getOil_company());
		query.setParameter("c_serial_no", coupon.getC_serial_no());
		query.setParameter("amount", coupon.getAmount());
		query.setParameter("c_status", "Active");
		query.setParameter("update_by", loggedInUserId);
		query.setParameter("update_date", update_date);
		query.setParameter("c_id", coupon.getC_id());
		int rslt = query.executeUpdate();
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean checkCoupon(String oil_company, String c_serial_no) {

		Query query = getSessionFactory().openSession().createQuery("FROM CouponRegistration a WHERE a.oil_company = :oil_company and a.c_serial_no = :c_serial_no");
		query.setParameter("oil_company", oil_company);
		query.setParameter("c_serial_no", c_serial_no);
		
		int size =  query.list().size();
		
		if(size > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkCoupon(String oil_company, String c_serial_no, int c_id) {
		
		Query query = getSessionFactory().openSession().createQuery("FROM CouponRegistration a WHERE a.oil_company = :oil_company and a.c_serial_no = :c_serial_no and a.c_id <> :c_id");
		query.setParameter("oil_company", oil_company);
		query.setParameter("c_serial_no", c_serial_no);
		query.setParameter("c_id", c_id);
		
		int size =  query.list().size();
		
		if(size > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<CouponRegistration> getRelatedCouponList(int fo_id) {
		
		final String sql = "SELECT c_id, oil_company, amount, c_serial_no, c_status FROM CouponRegistration a WHERE a.c_status = 'Active' and c_id in (select c_id from coupondissemination where fo_id = ?)";
		
		return jdbcTemplate.query(sql, new Object[]{ fo_id }, new CouponRegistrationRowMapper());
	}
	
	@Override
	public boolean validateCoupon(CouponRegistration coupon) {
		
		final String sql = "SELECT c_id, oil_company, amount, c_serial_no, c_status FROM CouponRegistration a WHERE a.c_serial_no = ? and a.oil_company = ? and a.amount = ? ";
		
		List<CouponRegistration> rslt = jdbcTemplate.query(sql, new Object[]{ coupon.getC_serial_no(), coupon.getOil_company(), coupon.getAmount() }, new CouponRegistrationRowMapper());
		
		if(rslt.size() > 0){
			
			return true;
		} else {
			
			return false;
		}
	}
	
	@Override
	public List<CouponRegistration> getCouponCategory() {

		final String sql = "SELECT oil_company, create_date, sum(amount) total FROM couponregistration GROUP BY create_date, oil_company";
		
		return jdbcTemplate.query(sql, new CouponRegistrationCategoryRowMapper());
	}

	@Override
	public List<CouponDissemination> getConsumedCoupon(CouponRegistration coupon) {
		
		final String sql = "SELECT a.fo_id, sum(b.amount) amount, c.origin_place, c.destination_place, e.truck_plate_no, b.oil_company " +
				"FROM coupondissemination a, couponregistration b, frightordertripdetail c, frightorder d, truckinformation e " +
				"WHERE a.c_id = b.c_id and a.fo_id = c.fo_id and c.fo_id = d.fo_id and e.tri_id = d.tri_id and b.create_date = ? and a.create_date between ? and ? " +
				"group by a.fo_id;";
		
		return jdbcTemplate.query(sql, new Object[]{ coupon.getCreate_date(), coupon.getDate_from(), coupon.getDate_to() }, new CouponConsumedRowMapper());
	}
	
	@Override
	public List<CouponDissemination> getCouponTotalConsumed(CouponRegistration coupon) {
		
		final String sql = "SELECT a.fo_id, sum(b.amount) amount, c.origin_place, c.destination_place, e.truck_plate_no, b.oil_company " +
				"FROM coupondissemination a, couponregistration b, frightordertripdetail c, frightorder d, truckinformation e " +
				"WHERE a.c_id = b.c_id and a.fo_id = c.fo_id and c.fo_id = d.fo_id and e.tri_id = d.tri_id and b.create_date = ? and a.create_date between ? and ? " +
				"group by a.fo_id;";
		
		return jdbcTemplate.query(sql, new Object[]{ coupon.getCreate_date(), coupon.getCreate_date(), TodayDate_YYYYMMDD.getToday() }, new CouponConsumedRowMapper());
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
