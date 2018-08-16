package org.fidel.tms.dao;

import java.util.List;

import org.fidel.tms.model.Files;

public interface FilesDao {
	
	public Files find(int f_id);
	
	public Files findByName(String fileName);
	
	public List<Files> listAllActiveFiles();
	
	public boolean save(final org.fidel.tms.model.Files file);
	
	public void delete(int id);

}
