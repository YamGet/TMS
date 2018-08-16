package org.fidel.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FuelCardAvailabilityStatus {
	
	@Id
	private int fca_id;
	private int fc_id;
	private int fo_id;
	private String date_from;
	private String date_to;
	private String fca_status;
	
	public FuelCardAvailabilityStatus() {
		super();
	}

	public FuelCardAvailabilityStatus(int fca_id, int fc_id, int fo_id, String date_from, String date_to,
			String fca_status) {
		super();
		this.fca_id = fca_id;
		this.fc_id = fc_id;
		this.fo_id = fo_id;
		this.date_from = date_from;
		this.date_to = date_to;
		this.fca_status = fca_status;
	}

	public int getFca_id() {
		return fca_id;
	}

	public void setFca_id(int fca_id) {
		this.fca_id = fca_id;
	}

	public int getFc_id() {
		return fc_id;
	}

	public void setFc_id(int fc_id) {
		this.fc_id = fc_id;
	}

	public int getFo_id() {
		return fo_id;
	}

	public void setFo_id(int fo_id) {
		this.fo_id = fo_id;
	}

	public String getDate_from() {
		return date_from;
	}

	public void setDate_from(String date_from) {
		this.date_from = date_from;
	}

	public String getDate_to() {
		return date_to;
	}

	public void setDate_to(String date_to) {
		this.date_to = date_to;
	}

	public String getFca_status() {
		return fca_status;
	}

	public void setFca_status(String fca_status) {
		this.fca_status = fca_status;
	}
}
