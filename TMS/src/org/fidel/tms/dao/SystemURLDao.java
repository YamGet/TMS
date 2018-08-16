package org.fidel.tms.dao;

import java.util.List;

import org.fidel.tms.model.SystemURL;

public interface SystemURLDao {

	public List<SystemURL> getRelatedModule(int m_id, int ur_id);
	
	public List<SystemURL> getUnrelatedModule(int m_id, int ur_id);
	
	public List<SystemURL> getAuthenticatedURL(String user_name);
}
