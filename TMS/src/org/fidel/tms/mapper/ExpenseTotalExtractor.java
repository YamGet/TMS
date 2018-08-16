package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.Expense;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ExpenseTotalExtractor implements ResultSetExtractor<Expense> {

	@Override
	public Expense extractData(ResultSet result) throws SQLException, DataAccessException {

		Expense exp = new Expense();		
		
		exp.setFo_id(result.getInt("fo_id"));
		
		exp.setExpense_amount(result.getDouble("expense_amount"));
		
		return exp;
	}

}
