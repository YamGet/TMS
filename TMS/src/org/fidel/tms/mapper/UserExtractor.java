package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.Users;
import org.fidel.tms.model.UsersRole;
import org.fidel.tms.utils.PasswordEncription;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class UserExtractor implements ResultSetExtractor<Users> {

	public Users extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		
		Users users = new Users();
		
		users.setUser_id(resultSet.getInt("user_id"));
		users.setFname(resultSet.getString("fname"));
		users.setMname(resultSet.getString("mname"));
		users.setGname(resultSet.getString("gname"));
		users.setUser_name(resultSet.getString("user_name"));
		users.setPassword(resultSet.getString("password"));
		users.setUser_status(resultSet.getString("user_status"));
		users.setUr_id(resultSet.getInt("ur_id"));
		
		UsersRole usersRole = new UsersRole();
		usersRole.setUserrole_name(resultSet.getString("userrole_name"));
		users.setUsersRole(usersRole);
		
		return users;
	}

}
