package org.fidel.tms.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.fidel.tms.dao.SystemModuleDao;
import org.fidel.tms.mapper.SystemModuleRowMapper;
import org.fidel.tms.mapper.UsersRoleModuleRelationRowMapper;
import org.fidel.tms.model.SystemModule;
import org.fidel.tms.model.UsersRoleModuleRelation;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SystemModuleDaoImpl implements SystemModuleDao {
	
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<SystemModule> getUnrelatedSystemModuleList(int ur_id) {
		
		Query query = getSessionFactory().openSession().createSQLQuery("SELECT m_id, module_name FROM SystemModule WHERE module_status = 'Active' and m_id not in (select m_id from usersrolemodulerelation where ur_id = :ur_id)");
		query.setParameter("ur_id", ur_id);
		
		return (List<SystemModule>)query.list();
	}
	
	@Override
	public List<SystemModule> getRelatedSystemModuleList(int ur_id) {
				
		Query query = getSessionFactory().openSession().createSQLQuery("SELECT a.m_id, b.module_name FROM usersrolemodulerelation a, systemmodule b WHERE a.m_id = b.m_id and b.module_status = 'Active' and a.ur_id = :ur_id");
		query.setParameter("ur_id", ur_id);
		
		return (List<SystemModule>)query.list();
	}
	
	@Override
	public List<UsersRoleModuleRelation> getUsersRoleRelatedSystemModuleList(int ur_id) {
		
		final String sql = "SELECT a.m_id, a.ur_id, a.urmr_id, a.urmr_status, b.module_name FROM usersrolemodulerelation a, systemmodule b WHERE a.m_id = b.m_id and b.module_status = 'Active' and a.ur_id = ?";
		
		return jdbcTemplate.query(sql, new Object[]{ ur_id }, new UsersRoleModuleRelationRowMapper());

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
	@Autowired
	public void setDataSource(DataSource dataSource) {
		//this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
