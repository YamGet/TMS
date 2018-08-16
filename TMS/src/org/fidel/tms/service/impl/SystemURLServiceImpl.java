package org.fidel.tms.service.impl;

import java.util.List;

import org.fidel.tms.dao.SystemURLDao;
import org.fidel.tms.model.SystemURL;
import org.fidel.tms.service.SystemURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SystemURLServiceImpl implements SystemURLService {
	
	@Autowired
	SystemURLDao systemURLDao;

	@Override
	public List<SystemURL> getRelatedModule(int m_id, int ur_id) {
		
		return systemURLDao.getRelatedModule(m_id, ur_id);
	}

	@Override
	public List<SystemURL> getUnrelatedModule(int m_id, int ur_id) {
		
		return systemURLDao.getUnrelatedModule(m_id, ur_id);
	}

	@Override
	public List<SystemURL> getAuthenticatedURL(String user_name) {
		
		return systemURLDao.getAuthenticatedURL(user_name);
	}

}
