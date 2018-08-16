package org.fidel.tms.model;

import java.util.Date;

public class TrailAvailabilityStatus {
	
	private int tlas_id;
	private int tli_id;
	private int fo_id;
	private Date date_from;
	private Date date_to;
	private String tlas_status;
	
	public int getTlas_id() {
		return tlas_id;
	}
	public void setTlas_id(int tlas_id) {
		this.tlas_id = tlas_id;
	}
	public int getTli_id() {
		return tli_id;
	}
	public void setTli_id(int tli_id) {
		this.tli_id = tli_id;
	}
	public int getFo_id() {
		return fo_id;
	}
	public void setFo_id(int fo_id) {
		this.fo_id = fo_id;
	}
	public Date getDate_from() {
		return date_from;
	}
	public void setDate_from(Date date_from) {
		this.date_from = date_from;
	}
	public Date getDate_to() {
		return date_to;
	}
	public void setDate_to(Date date_to) {
		this.date_to = date_to;
	}
	public String getTlas_status() {
		return tlas_status;
	}
	public void setTlas_status(String tlas_status) {
		this.tlas_status = tlas_status;
	}

	
}
