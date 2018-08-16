package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.UsersRoleModuleRelation;
import org.springframework.jdbc.core.RowMapper;

public class UsersRoleModuleRelationRowMapper implements RowMapper<UsersRoleModuleRelation> {

	@Override
	public UsersRoleModuleRelation mapRow(ResultSet result, int rowNum) throws SQLException {
		
		UsersRoleModuleRelationExtractor urre = new UsersRoleModuleRelationExtractor();
		
		return urre.extractData(result);
	}

}
