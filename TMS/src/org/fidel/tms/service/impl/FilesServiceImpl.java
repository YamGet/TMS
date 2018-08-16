package org.fidel.tms.service.impl;

import org.fidel.tms.model.Files;
import java.util.List;

import org.fidel.tms.dao.FilesDao;
import org.fidel.tms.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilesServiceImpl implements FilesService {
	
	@Autowired
	FilesDao filesDao;

	@Override
	public Files find(int f_id) {
		
		return filesDao.find(f_id);
	}

	@Override
	public List<Files> listAllActiveFiles() {
		
		return filesDao.listAllActiveFiles();
	}

	@Override
	public boolean save(Files file) {
		
		return filesDao.save(file);
	}

	@Override
	public void delete(int id) {
		
		filesDao.delete(id);
	}

	@Override
	public Files findByName(String fileName) {
		
		return filesDao.findByName(fileName);
	}

}
