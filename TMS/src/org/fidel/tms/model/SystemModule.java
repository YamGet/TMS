package org.fidel.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SystemModule {
	@Id
	private int m_id;
	private String module_name;
	private String module_status;
	
	public SystemModule(){}
	
	public SystemModule(int m_id, String module_name, String module_status) {
		super();
		this.m_id = m_id;
		this.module_name = module_name;
		this.module_status = module_status;
	}
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public String getModule_name() {
		return module_name;
	}
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	public String getModule_status() {
		return module_status;
	}
	public void setModule_status(String module_status) {
		this.module_status = module_status;
	}	
	
	
}
