package org.fidel.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class SystemURL {
	@Id
	private int su_id;
	private String description;
	private String url;
	private int sm_id;
	private String su_status;
	
	@Transient
	private UsersRoleModuleRelation usersRoleModuleRelation;
	
	public int getSu_id() {
		return su_id;
	}
	public void setSu_id(int su_id) {
		this.su_id = su_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getSm_id() {
		return sm_id;
	}
	public void setSm_id(int sm_id) {
		this.sm_id = sm_id;
	}
	public String getSu_status() {
		return su_status;
	}
	public void setSu_status(String su_status) {
		this.su_status = su_status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UsersRoleModuleRelation getUsersRoleModuleRelation() {
		return usersRoleModuleRelation;
	}
	public void setUsersRoleModuleRelation(UsersRoleModuleRelation usersRoleModuleRelation) {
		this.usersRoleModuleRelation = usersRoleModuleRelation;
	}
	
}
