package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.Associations;
import org.springframework.jdbc.core.RowMapper;

public class AssociationRowMapper implements RowMapper<Associations> {

	@Override
	public Associations mapRow(ResultSet result, int rowNum) throws SQLException {
		AssociationExtractor ae = new AssociationExtractor();
		return ae.extractData(result);
	}

}
