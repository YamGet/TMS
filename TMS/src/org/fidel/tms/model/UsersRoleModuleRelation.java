package org.fidel.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class UsersRoleModuleRelation {
	@Id
	private int urmr_id;
	private int ur_id;
	private int m_id;
	private String urmr_status;
	
	@Transient
	private UsersRole usersRole;
	@Transient
	private SystemModule systemModule;
	
	public UsersRoleModuleRelation(){}
	
	public UsersRoleModuleRelation(int urmr_id, int ur_id, int m_id) {
		super();
		this.urmr_id = urmr_id;
		this.ur_id = ur_id;
		this.m_id = m_id;
	}
	
	public int getUrmr_id() {
		return urmr_id;
	}
	public void setUrmr_id(int urmr_id) {
		this.urmr_id = urmr_id;
	}
	public int getUr_id() {
		return ur_id;
	}
	public void setUr_id(int ur_id) {
		this.ur_id = ur_id;
	}
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public UsersRole getUsersRole() {
		return usersRole;
	}

	public void setUsersRole(UsersRole usersRole) {
		this.usersRole = usersRole;
	}

	public String getUrmr_status() {
		return urmr_status;
	}

	public void setUrmr_status(String urmr_status) {
		this.urmr_status = urmr_status;
	}

	public SystemModule getSystemModule() {
		return systemModule;
	}

	public void setSystemModule(SystemModule systemModule) {
		this.systemModule = systemModule;
	}
	
	

}
