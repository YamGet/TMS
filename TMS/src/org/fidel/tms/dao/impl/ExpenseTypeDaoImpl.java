package org.fidel.tms.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.fidel.tms.dao.ExpenseTypeDao;
import org.fidel.tms.mapper.ExpenseTypeFrightOrderRowMapper;
import org.fidel.tms.model.ExpenseType;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ExpenseTypeDaoImpl implements ExpenseTypeDao {
	
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ExpenseType> getActiveExpenseType() {
		
		Query query = getSessionFactory().openSession().createQuery("FROM ExpenseType WHERE expense_type_status = 'Active'");
		
		return (List<ExpenseType>) query.list();
	}

	@Override
	public List<ExpenseType> getAllExpenseType() {
		
		Query query = getSessionFactory().openSession().createQuery("FROM ExpenseType");
		
		return (List<ExpenseType>) query.list();
	}

	@Override
	public boolean saveExpenseType(ExpenseType expenseType) {
		
		if(checkExpenseType(expenseType.getExpense_type_name())){
			return false;
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String cr_date = TodayDate_YYYYMMDD.getToday();
		
		Query query = getSessionFactory().openSession().createSQLQuery("INSERT INTO ExpenseType(expense_type_name, account_number, expense_type_usage, expense_type_status, create_by, create_date) VALUES(:expense_type_name, :account_number, :expense_type_usage, 'Active', :create_by, :create_date)");
		query.setParameter("expense_type_name", expenseType.getExpense_type_name());
		query.setParameter("account_number", expenseType.getAccount_number());
		query.setParameter("expense_type_usage", expenseType.getExpense_type_usage());
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
	public boolean updateExpenseType(ExpenseType expenseType) {
		
		if(checkExpenseType(expenseType.getExpense_type_name(), expenseType.getEt_id())){
			return false;
		}
		
		Query query = getSessionFactory().openSession().createQuery("UPDATE ExpenseType SET expense_type_name = :expense_type_name, account_number = :account_number, expense_type_usage = :expense_type_usage, expense_type_status = :expense_type_status WHERE et_id = :et_id");
		query.setParameter("expense_type_name", expenseType.getExpense_type_name());
		query.setParameter("account_number", expenseType.getAccount_number());
		query.setParameter("expense_type_usage", expenseType.getExpense_type_usage());
		query.setParameter("expense_type_status", expenseType.getExpense_type_status());
		query.setParameter("et_id", expenseType.getEt_id());
		int rslt = query.executeUpdate();
		
		if(rslt > 0){
			return true;
		} else {		
			return false;
		}
	}

	@Override
	public boolean checkExpenseType(String expenseTypeName) {
		
		Query query = getSessionFactory().openSession().createQuery("FROM ExpenseType WHERE expense_type_name like :expense_type_name");
		query.setParameter("expense_type_name", expenseTypeName);
		int size = query.list().size();
		
		if(size > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkExpenseType(String expenseTypeName, int et_id) {
		
		Query query = getSessionFactory().openSession().createQuery("FROM ExpenseType WHERE expense_type_name like :expense_type_name and et_id <> :et_id");
		query.setParameter("expense_type_name", expenseTypeName);
		query.setParameter("et_id", et_id);
		int size = query.list().size();
		
		if(size > 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public List<ExpenseType> getUnrelatedExpenseType(String fon) {
		
		final String sql = "SELECT et_id, expense_type_name, expense_type_status, account_number "
				+ "FROM ExpenseType "
				+ "WHERE expense_type_status = 'Active' and account_number != '7014' and et_id not in (SELECT et_id FROM expense WHERE fo_id = (SELECT fo_id FROM frightOrder WHERE fon = ?))";
				
		return jdbcTemplate.query(sql, new Object[]{ fon }, new ExpenseTypeFrightOrderRowMapper());
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
