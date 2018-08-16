package org.fidel.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UsersRole {
	@Id
	private int ur_id;
	private String userrole_name;
	private String userrole_status;
	
	public UsersRole(){}
	
	public UsersRole(int ur_id, String userrole_name, String userrole_status) {
		super();
		this.ur_id = ur_id;
		this.userrole_name = userrole_name;
		this.userrole_status = userrole_status;
	}
	public int getUr_id() {
		return ur_id;
	}
	public void setUr_id(int ur_id) {
		this.ur_id = ur_id;
	}
	public String getUserrole_name() {
		return userrole_name;
	}
	public void setUserrole_name(String userrole_name) {
		this.userrole_name = userrole_name;
	}
	public String getUserrole_status() {
		return userrole_status;
	}
	public void setUserrole_status(String userrole_status) {
		this.userrole_status = userrole_status;
	}
	
	

}
