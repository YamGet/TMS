package org.fidel.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class UsersAuthentication {
	
	@Id
	private int ua_id;
	private int urmr_id;
	private int su_id;
	private String ua_status;
	
	@Transient
	private UsersRoleModuleRelation usersRoleModuleRelation;
	
	public int getUa_id() {
		return ua_id;
	}
	public void setUa_id(int ua_id) {
		this.ua_id = ua_id;
	}
	public int getUrmr_id() {
		return urmr_id;
	}
	public void setUrmr_id(int urmr_id) {
		this.urmr_id = urmr_id;
	}
	public int getSu_id() {
		return su_id;
	}
	public void setSu_id(int su_id) {
		this.su_id = su_id;
	}
	public String getUa_status() {
		return ua_status;
	}
	public void setUa_status(String ua_status) {
		this.ua_status = ua_status;
	}
	public UsersRoleModuleRelation getUsersRoleModuleRelation() {
		return usersRoleModuleRelation;
	}
	public void setUsersRoleModuleRelation(UsersRoleModuleRelation usersRoleModuleRelation) {
		this.usersRoleModuleRelation = usersRoleModuleRelation;
	}

}
