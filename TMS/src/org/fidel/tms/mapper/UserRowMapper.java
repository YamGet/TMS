package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.Users;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<Users> {

	@Override
	public Users mapRow(ResultSet resultSet, int line) throws SQLException {
		UserExtractor userExtractor = new UserExtractor();
		return userExtractor.extractData(resultSet);
	}

}
