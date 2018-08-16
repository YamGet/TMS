package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.Expense;
import org.springframework.jdbc.core.RowMapper;

public class ExpenseRowMapper implements RowMapper<Expense> {

	@Override
	public Expense mapRow(ResultSet result, int rowNum) throws SQLException {
		
		ExpenseExtractor ee = new ExpenseExtractor();
		
		return ee.extractData(result);
	}

}
