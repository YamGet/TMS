package org.fidel.tms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Drivers {
	@Id
	@Column
	private int dr_id;
	@Column
	private String fname;
	@Column
	private String mname;
	@Column
	private String gname;
	@Column
	private String driving_license_no;
	@Column
	private String local_phone;
	@Column
	private String abroad_phone;
	@Column
	private String dr_status;
	
	@Transient
	private String fullName;
	
	public Drivers(){}
	
	public Drivers(int dr_id, String fname, String mname, String gname, String driving_license_no, String dr_status) {
		super();
		this.dr_id = dr_id;
		this.fname = fname;
		this.mname = mname;
		this.gname = gname;
		this.driving_license_no = driving_license_no;
		this.dr_status = dr_status;
	}
	public int getDr_id() {
		return dr_id;
	}
	public void setDr_id(int dr_id) {
		this.dr_id = dr_id;
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
	public String getDriving_license_no() {
		return driving_license_no;
	}
	public void setDriving_license_no(String driving_license_no) {
		this.driving_license_no = driving_license_no;
	}
	public String getDr_status() {
		return dr_status;
	}
	public void setDr_status(String dr_status) {
		this.dr_status = dr_status;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getLocal_phone() {
		return local_phone;
	}

	public void setLocal_phone(String local_phone) {
		this.local_phone = local_phone;
	}

	public String getAbroad_phone() {
		return abroad_phone;
	}

	public void setAbroad_phone(String abroad_phone) {
		this.abroad_phone = abroad_phone;
	}
}
