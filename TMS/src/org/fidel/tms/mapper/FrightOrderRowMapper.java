package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.FrightOrder;
import org.springframework.jdbc.core.RowMapper;

public class FrightOrderRowMapper implements RowMapper<FrightOrder> {

	@Override
	public FrightOrder mapRow(ResultSet result, int rowNum) throws SQLException {
		FrightOrderExtractor foe = new FrightOrderExtractor();
		return foe.extractData(result);
	}

}
