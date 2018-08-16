package org.fidel.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TrailInformation {
	@Id
	private int tli_id;
	private String trail_plate_no;
	private String loading_capacity;
	private String trail_owner;
	private String trail_status;
	private String trail_type;
	
	public TrailInformation(){}
	
	public TrailInformation(int tli_id, String trail_plate_no, String loading_capacity, String trail_owner, String trail_type,
			String trail_status) {
		super();
		this.tli_id = tli_id;
		this.trail_plate_no = trail_plate_no;
		this.loading_capacity = loading_capacity;
		this.trail_owner = trail_owner;
		this.trail_status = trail_status;
		this.trail_type = trail_type;
	}
	public int getTli_id() {
		return tli_id;
	}
	public void setTli_id(int tli_id) {
		this.tli_id = tli_id;
	}
	public String getTrail_plate_no() {
		return trail_plate_no;
	}
	public void setTrail_plate_no(String trail_plate_no) {
		this.trail_plate_no = trail_plate_no;
	}
	public String getLoading_capacity() {
		return loading_capacity;
	}
	public void setLoading_capacity(String loading_capacity) {
		this.loading_capacity = loading_capacity;
	}
	public String getTrail_owner() {
		return trail_owner;
	}
	public void setTrail_owner(String trail_owner) {
		this.trail_owner = trail_owner;
	}
	public String getTrail_status() {
		return trail_status;
	}
	public void setTrail_status(String trail_status) {
		this.trail_status = trail_status;
	}

	public String getTrail_type() {
		return trail_type;
	}

	public void setTrail_type(String trail_type) {
		this.trail_type = trail_type;
	}
}
