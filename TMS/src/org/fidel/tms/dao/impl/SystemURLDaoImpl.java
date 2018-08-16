package org.fidel.tms.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.fidel.tms.dao.SystemURLDao;
import org.fidel.tms.mapper.SystemURLRowMapper;
import org.fidel.tms.model.SystemURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SystemURLDaoImpl implements SystemURLDao {
	
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;

	@Override
	public List<SystemURL> getRelatedModule(int m_id, int ur_id) {
		
		final String sql = "SELECT a.su_id, a.description, a.url, a.sm_id, c.urmr_id FROM systemurl a, systemmodule b, usersrolemodulerelation c, usersauthentication d WHERE a.sm_id = b.m_id and a.sm_id = c.m_id  and a.su_id = d.su_id and c.urmr_id = d.urmr_id and d.ua_status = 'Active' and a.sm_id = ? and c.ur_id = ?";
		
		return jdbcTemplate.query(sql, new Object[]{ m_id, ur_id }, new SystemURLRowMapper());
	}

	@Override
	public List<SystemURL> getUnrelatedModule(int m_id, int ur_id) {
		
		final String sql = "SELECT a.su_id, a.description, a.url, a.sm_id, c.urmr_id FROM systemurl a, systemmodule b, usersrolemodulerelation c WHERE a.sm_id = b.m_id and a.sm_id = c.m_id  and a.su_id not in (select d.su_id from usersauthentication d where d.ua_status = 'Active' and c.urmr_id = d.urmr_id) and a.sm_id = ? and c.ur_id = ?";
		
		return jdbcTemplate.query(sql, new Object[]{ m_id, ur_id }, new SystemURLRowMapper());
	}
	
	@Override
	public List<SystemURL> getAuthenticatedURL(String user_name) {
		
		final String sql = "SELECT d.su_id, d.description, d.url, d.sm_id, c.urmr_id FROM usersauthentication a, users b, usersrolemodulerelation c, systemurl d, usersrole e WHERE b.user_name = ? and e.ur_id = b.ur_id and c.ur_id = e.ur_id and c.urmr_id = a.urmr_id and d.su_id = a.su_id and a.ua_status = 'Active'";
		
		return jdbcTemplate.query(sql, new Object[]{ user_name }, new SystemURLRowMapper());
	}
	
	
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	@Autowired
	public void setDateSource(DataSource dataSource) {
		//this.dateSource = dateSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
