package org.fidel.tms.dao.impl;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.fidel.tms.dao.AssociationsDao;
import org.fidel.tms.mapper.AssociationRowMapper;
import org.fidel.tms.model.Associations;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AssociationsDaoImpl implements AssociationsDao {
	
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Associations> getActiveAssociations() {
		
		final String getAssociationList = "SELECT a_id, association_name, association_status FROM associations WHERE association_status = 'Active'";

		return jdbcTemplate.query(getAssociationList, new AssociationRowMapper());
	}

	@Override
	public List<Associations> getAllAssociations() {
		
		final String getAssociationList = "SELECT a_id, association_name, association_status FROM associations";

		return jdbcTemplate.query(getAssociationList, new AssociationRowMapper());
	}

	@Override
	public boolean saveAssociations(Associations associaton) {
		
		if(checkAssociationExistance(associaton.getAssociation_name())){
			return false;
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String cr_date = TodayDate_YYYYMMDD.getToday();

		String sql = "INSERT INTO associations(association_name, association_status, create_by, create_date) VALUES (?, 'Active', ?, ?)";
		
		int rslt = jdbcTemplate.update(sql, new Object[] { associaton.getAssociation_name(), loggedInUserId, cr_date });
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateAssociations(Associations association) {
		
		if(checkAssociationExistance(association.getAssociation_name(), association.getA_id())){
			return false;
		}
		
		int loggedInUserId = SessionManager.getUserIdOnSession();
		
		String update_date = TodayDate_YYYYMMDD.getToday();
		
		final String updateAssociation = "UPDATE associations SET association_name = ?, association_status = ?, update_by = ?, update_date = ? WHERE a_id = ?";
		
		int rslt = jdbcTemplate.update(updateAssociation, new Object[]{association.getAssociation_name(), association.getAssociation_status(), loggedInUserId, update_date, association.getA_id()});
		
		if(rslt > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkAssociationExistance(String association_name) {

		final String getAssociationList = "SELECT a_id, association_name, association_status FROM associations WHERE association_name like '" + association_name + "'";

		int size = jdbcTemplate.query(getAssociationList, new AssociationRowMapper()).size();
		
		if(size > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkAssociationExistance(String association_name, int a_id) {

		final String getAssociationList = "SELECT a_id, association_name, association_status FROM associations WHERE association_name like '" + association_name + "' and a_id <> '" + a_id + "'";

		int size = jdbcTemplate.query(getAssociationList, new AssociationRowMapper()).size();
		
		if(size > 0){
			return true;
		} else {
			return false;
		}
	}
	
	
	////*** setter and getter ***\\\\
	
	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
