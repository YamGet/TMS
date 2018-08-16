package org.fidel.tms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Users {
	
	@Id
	@Column
	private int user_id;
	@Column
	private String fname;
	@Column
	private String mname;
	@Column
	private String gname;
	@Column
	private String user_name;
	@Column
	private String password;
	@Column
	private String user_status;
	@Column
	private int ur_id;
	
	@Transient
	private UsersRole usersRole;
	@Transient
	private String oldPassword;
	
	public Users(){}
	
	public Users(int user_id, String user_name, String password, String user_status, int ur_id) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
		this.user_status = user_status;
		this.ur_id = ur_id;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_status() {
		return user_status;
	}
	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}
	public int getUr_id() {
		return ur_id;
	}
	public void setUr_id(int ur_id) {
		this.ur_id = ur_id;
	}
	public UsersRole getUsersRole() {
		return usersRole;
	}
	public void setUsersRole(UsersRole usersRole) {
		this.usersRole = usersRole;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}	
}
