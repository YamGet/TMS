package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.ExpenseType;
import org.springframework.jdbc.core.RowMapper;

public class ExpenseTypeFrightOrderRowMapper implements RowMapper<ExpenseType> {

	@Override
	public ExpenseType mapRow(ResultSet result, int rowNum) throws SQLException {
		ExpenseTypeFrightOrderExtractor etfo = new ExpenseTypeFrightOrderExtractor();
		return etfo.extractData(result);
	}

}
