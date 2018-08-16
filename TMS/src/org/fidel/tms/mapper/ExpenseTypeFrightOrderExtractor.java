package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.ExpenseType;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ExpenseTypeFrightOrderExtractor implements ResultSetExtractor<ExpenseType> {

	@Override
	public ExpenseType extractData(ResultSet result) throws SQLException, DataAccessException {
		
		ExpenseType et = new ExpenseType();
		
		et.setEt_id(result.getInt("et_id"));
		et.setExpense_type_name(result.getString("expense_type_name"));
		et.setExpense_type_status(result.getString("expense_type_status"));
		et.setAccount_number(result.getString("account_number"));
		
		return et;
	}

}
