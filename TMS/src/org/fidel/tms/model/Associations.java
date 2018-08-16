package org.fidel.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Associations {
	@Id
	private int a_id;
	private String association_name;
	private String association_status;
	
	public Associations(){}
	
	public Associations(int a_id, String association_Name, String association_Status) {
		super();
		this.a_id = a_id;
		association_name = association_Name;
		association_status = association_Status;
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public String getAssociation_name() {
		return association_name;
	}
	public void setAssociation_name(String association_Name) {
		association_name = association_Name;
	}
	public String getAssociation_status() {
		return association_status;
	}
	public void setAssociation_status(String association_Status) {
		association_status = association_Status;
	}
}
