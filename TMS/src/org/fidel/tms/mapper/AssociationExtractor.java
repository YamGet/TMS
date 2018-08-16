package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.Associations;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class AssociationExtractor implements ResultSetExtractor<Associations> {

	@Override
	public Associations extractData(ResultSet result) throws SQLException, DataAccessException {
		
		Associations as = new Associations();
		
		as.setA_id(result.getInt("a_id"));
		as.setAssociation_name(result.getString("association_name"));
		as.setAssociation_status(result.getString("association_status"));
		
		return as;
	}

}
