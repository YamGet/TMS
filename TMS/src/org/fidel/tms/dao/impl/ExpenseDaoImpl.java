package org.fidel.tms.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.fidel.tms.dao.ExpenseDao;
import org.fidel.tms.mapper.ExpenseRowMapper;
import org.fidel.tms.mapper.ExpenseTotalRowMapper;
import org.fidel.tms.mapper.FrightOrderRowMapper;
import org.fidel.tms.mapper.FrightOrderWithFrightDetailAdvancePaymentRowMapper;
import org.fidel.tms.mapper.ReportExpenseRowMapper;
import org.fidel.tms.model.Expense;
import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ExpenseDaoImpl implements ExpenseDao {
	
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<FrightOrder> getFrightOrder(String fon) {
		
		final String frightOrderList = "SELECT a.fo_id, a.fon, a.a_id, b.association_name, c.tri_id, c.truck_plate_no, d.tli_id, d.trail_plate_no, e.dr_id, e.fname, e.mname, e.gname, a.trip, a.total_coupon_amount, a.date_from, a.date_to, a.fo_status "
				+ "FROM frightorder a, associations b, truckinformation c, trailinformation d, drivers e, frightordertripdetail f "
				+ "WHERE a.a_id = b.a_id and a.tri_id = c.tri_id and a.tli_id = d.tli_id and a.dr_id = e.dr_id and f.fo_id = a.fo_id and f.fotd_status = 'Active'";
		
		return jdbcTemplate.query(frightOrderList, new FrightOrderWithFrightDetailAdvancePaymentRowMapper());
	}
	
	@Override
	public boolean saveFrightOrderExpenseAmount(Expense expense) {
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String cr_date = TodayDate_YYYYMMDD.getToday();
		
		Query query = getSessionFactory().openSession().createSQLQuery("INSERT INTO Expense(fo_id, et_id, expense_amount, create_by, create_date) VALUES(:fo_id, :et_id, :expense_amount, :create_by, :create_date)");
		query.setParameter("fo_id", expense.getFo_id());
		query.setParameter("et_id", expense.getEt_id());
		query.setParameter("expense_amount", expense.getExpense_amount());
		query.setParameter("create_by", loggedInUserId);
		query.setParameter("create_date", cr_date);
		int val = query.executeUpdate();
		
		if(val > 0){
			return true;
		}
		return false;
	}
	
	@Override
	public String getTotalExpenseByFoid(Expense expense) {
		
		final String sql = "SELECT sum(a.expense_amount) expense_amount, a.fo_id FROM expense a, expensetype b WHERE a.et_id = b.et_id and fo_id = ? and b.account_number <> 7014";
		
		List<Expense> rslt = jdbcTemplate.query(sql, new Object[]{ expense.getFo_id() }, new ExpenseTotalRowMapper());
		
		return rslt.get(0).getExpense_amount().toString();
	}
	
	@Override
	public List<Expense> getExpenseListByFoid(Expense expense) {

		final String sql = "SELECT a.e_id, a.fo_id, a.et_id, a.expense_amount, b.expense_type_name, b.account_number, c.fon, d.truck_plate_no "
				+ "FROM expense a, expenseType b, frightorder c, truckinformation d "
				+ "WHERE a.et_id = b.et_id and a.fo_id = c.fo_id and c.tri_id = d.tri_id and a.fo_id = ?";
		
		return jdbcTemplate.query(sql, new Object[]{ expense.getFo_id() }, new ExpenseRowMapper());
	}
	

	@Override
	public List<Expense> rpt_generateRevenueExpensePerTruckReport(Expense expense) {
		//(commission * (b.price * b.delivered_quantity))
		
		String truckType = "";
		
		if(expense.getFrightOrder().getTruckInformation().getTruck_type().equals("BOTH")){
			
			truckType = "'DRY', 'FLUID'";
		} else {
			
			truckType = "'" + expense.getFrightOrder().getTruckInformation().getTruck_type() + "'";
		}
		
		if(expense.getReport_type().equalsIgnoreCase("External")){
			
			final String sql = "SELECT a.tri_id, d.truck_plate_no, a.fo_id, a.fon, SUM(expense_amount) expense, (b.price * b.delivered_quantity) revenue " +
					"FROM frightordertripdetail b, truckinformation d, frightorder a LEFT JOIN expense c ON a.fo_id = c.fo_id LEFT JOIN expensetype e ON c.et_id = e.et_id and e.expense_type_usage = 'Both' " +
					"WHERE a.fo_id = b.fo_id and a.tri_id = d.tri_id and a.date_from between ? and ? and d.truck_type in (" + truckType + ") " +
					"GROUP BY a.fo_id";

			return jdbcTemplate.query(sql, new Object[]{ expense.getDate_from(), expense.getDate_to() }, new ReportExpenseRowMapper());
		}
		
		final String sql = "SELECT a.tri_id, d.truck_plate_no, a.fo_id, a.fon, SUM(expense_amount) expense, (b.price * b.delivered_quantity) revenue "
				+"FROM frightordertripdetail b, truckinformation d, frightorder a LEFT JOIN expense c ON a.fo_id = c.fo_id "
				+"WHERE a.fo_id = b.fo_id and a.tri_id = d.tri_id and a.date_from between ? and ? and d.truck_type in (" + truckType + ") " 
				+"GROUP BY a.fo_id";
		
		return jdbcTemplate.query(sql, new Object[]{ expense.getDate_from(), expense.getDate_to() }, new ReportExpenseRowMapper());
	}
	
	@Override
	public boolean updateFOExpenseAmount(Expense expense) {
		
		Query query = getSessionFactory().openSession().createQuery("UPDATE Expense SET expense_amount = :expense_amount WHERE e_id = :e_id");
		query.setParameter("expense_amount", expense.getExpense_amount());
		query.setParameter("e_id", expense.getE_id());
		int rslt = query.executeUpdate();
		
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
