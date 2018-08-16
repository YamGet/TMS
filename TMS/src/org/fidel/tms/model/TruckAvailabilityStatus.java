package org.fidel.tms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class TruckAvailabilityStatus {
	@Id
	private int tas_id;
	private int tri_id;
	private int fo_id;
	private Date date_from;
	private Date date_to;
	private String tas_status;
		
	public TruckAvailabilityStatus(){}
	
	public TruckAvailabilityStatus(int tas_id, int tri_id, int fo_id, Date date_from, Date date_to, String tas_status) {
		super();
		this.tas_id = tas_id;
		this.tri_id = tri_id;
		this.fo_id = fo_id;
		this.date_from = date_from;
		this.date_to = date_to;
		this.tas_status = tas_status;
	}
	public int getTas_id() {
		return tas_id;
	}
	public void setTas_id(int tas_id) {
		this.tas_id = tas_id;
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
	public String getTas_status() {
		return tas_status;
	}
	public void setTas_status(String tas_status) {
		this.tas_status = tas_status;
	}

	public int getTri_id() {
		return tri_id;
	}

	public void setTri_id(int tri_id) {
		this.tri_id = tri_id;
	}
}
