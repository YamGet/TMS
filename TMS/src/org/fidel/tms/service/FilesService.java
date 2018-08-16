package org.fidel.tms.service;

import org.fidel.tms.model.Files;
import java.util.List;

public interface FilesService {
	
	public Files find(int f_id);
	
	public Files findByName(String fileName);
	
	public List<Files> listAllActiveFiles();
	
	public boolean save(final org.fidel.tms.model.Files file);
	
	public void delete(int id);

}
