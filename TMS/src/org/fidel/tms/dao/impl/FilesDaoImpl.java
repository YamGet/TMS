package org.fidel.tms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.fidel.tms.dao.FilesDao;
import org.fidel.tms.model.Files;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class FilesDaoImpl implements FilesDao {
	
	String query = null;
	
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Files find(int f_id) {

		query = "select * from files where f_id = ?";
		
		try{
			
			Files file = (Files) getJdbcTemplate().queryForObject(query, new Object[]{f_id}, new RowMapper(){
				
				Files fl;
				
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException{
					
					fl = new Files();
					
					fl.setF_id(rs.getInt(1));
					fl.setFilename(rs.getString(2));
					fl.setNotes(rs.getString(3));
					fl.setType(rs.getString(4));
					fl.setFile(rs.getBytes(5));
					
					return fl;
				}				
			});
			
			return file;
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Files findByName(String fileName) {
		
		query = "select * from files where filename = ?";
		
		try{
			
			Files file = (Files) getJdbcTemplate().queryForObject(query, new Object[]{fileName}, new RowMapper(){
				
				Files fl;
				
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException{
					
					fl = new Files();
					
					fl.setF_id(rs.getInt(1));
					fl.setFilename(rs.getString(2));
					fl.setNotes(rs.getString(3));
					fl.setType(rs.getString(4));
					fl.setFile(rs.getBytes(5));
					
					return fl;
				}				
			});
			
			return file;
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Files> listAllActiveFiles() {
		
		query = "select f_id, filename, notes, type from files";
		
		try{
			
			List<Files> files = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Files.class));
			
			return files;
			
		} catch(Exception ex){
			
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean save(final Files file) {
		
		query = "insert files (filename, notes, type, file, f_status) values(?, ?, ?, ?, ?)";
		
		try{
			synchronized (this) {
				int rslt = getJdbcTemplate().update(new PreparedStatementCreator() {
					
					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						
						PreparedStatement st = con.prepareStatement(query);
						st.setString(1, file.getFilename());
						st.setString(2, file.getNotes());
						st.setString(3, file.getType());
						st.setBytes(4, file.getFile());
						st.setString(5, "Active");

						return st;
					}
				});
				if(rslt > 0){
					return true;
				}
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public void delete(int id) {
		
		query = "delete from files where f_id = ?";
		
		try{
			
			getJdbcTemplate().update(query, new Object[]{ id });
			
		}catch(Exception ex){
			ex.printStackTrace();
		}

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

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
