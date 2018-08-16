package org.fidel.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FuelCard {
	
	@Id
	private int fc_id;
	private String fc_no;
	private String fc_company;
	private String fc_status;
	
	public FuelCard() {
		super();
	}
	
	public FuelCard(int fc_id, String fc_no, String fc_company, String fc_status) {
		super();
		this.fc_id = fc_id;
		this.fc_no = fc_no;
		this.fc_company = fc_company;
		this.fc_status = fc_status;
	}

	public int getFc_id() {
		return fc_id;
	}

	public void setFc_id(int fc_id) {
		this.fc_id = fc_id;
	}

	public String getFc_no() {
		return fc_no;
	}

	public void setFc_no(String fc_no) {
		this.fc_no = fc_no;
	}

	public String getFc_company() {
		return fc_company;
	}

	public void setFc_company(String fc_company) {
		this.fc_company = fc_company;
	}

	public String getFc_status() {
		return fc_status;
	}

	public void setFc_status(String fc_status) {
		this.fc_status = fc_status;
	}
	
}
