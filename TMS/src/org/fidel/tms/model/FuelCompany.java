package org.fidel.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FuelCompany {

	@Id
	private int fc_id;
	private String company_name;
	private String contact_person_name;
	private String contact_person_phone;
	private String fc_status;
	
	public int getFc_id() {
		return fc_id;
	}
	public void setFc_id(int fc_id) {
		this.fc_id = fc_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getContact_person_name() {
		return contact_person_name;
	}
	public void setContact_person_name(String contact_person_name) {
		this.contact_person_name = contact_person_name;
	}
	public String getContact_person_phone() {
		return contact_person_phone;
	}
	public void setContact_person_phone(String contact_person_phone) {
		this.contact_person_phone = contact_person_phone;
	}
	public String getFc_status() {
		return fc_status;
	}
	public void setFc_status(String fc_status) {
		this.fc_status = fc_status;
	}
}
