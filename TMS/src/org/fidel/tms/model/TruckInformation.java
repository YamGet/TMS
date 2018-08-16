package org.fidel.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TruckInformation {
	
	@Id
	private int tri_id;
	private String shanci_no;
	private String truck_plate_no;
	private String side_no;
	private String truck_model;
	private String loading_capacity;
	private String truck_owner;
	private String truck_status;
	private String truck_type;
	
	public TruckInformation(){}
	
	public TruckInformation(int tri_id, String shanci_no, String truck_plate_no, String side_no, String truck_model,
			String loading_capacity, String truck_owner, String truck_status, String truck_type) {
		super();
		this.tri_id = tri_id;
		this.shanci_no = shanci_no;
		this.truck_plate_no = truck_plate_no;
		this.side_no = side_no;
		this.truck_model = truck_model;
		this.loading_capacity = loading_capacity;
		this.truck_owner = truck_owner;
		this.truck_status = truck_status;
		this.truck_type = truck_type;
	}
	public int getTri_id() {
		return tri_id;
	}
	public void setTri_id(int tri_id) {
		this.tri_id = tri_id;
	}
	public String getshanci_no() {
		return shanci_no;
	}
	public void setshanci_no(String shanci_no) {
		this.shanci_no = shanci_no;
	}
	public String getTruck_plate_no() {
		return truck_plate_no;
	}
	public void setTruck_plate_no(String truck_plate_no) {
		this.truck_plate_no = truck_plate_no;
	}
	public String getSide_no() {
		return side_no;
	}
	public void setSide_no(String side_no) {
		this.side_no = side_no;
	}
	public String getTruck_model() {
		return truck_model;
	}
	public void setTruck_model(String truck_model) {
		this.truck_model = truck_model;
	}
	public String getloading_capacity() {
		return loading_capacity;
	}
	public void setloading_capacity(String loading_capacity) {
		this.loading_capacity = loading_capacity;
	}
	public String getTruck_owner() {
		return truck_owner;
	}
	public void setTruck_owner(String truck_owner) {
		this.truck_owner = truck_owner;
	}
	public String getTruck_status() {
		return truck_status;
	}
	public void setTruck_status(String truck_status) {
		this.truck_status = truck_status;
	}

	public String getTruck_type() {
		return truck_type;
	}

	public void setTruck_type(String truck_type) {
		this.truck_type = truck_type;
	}	
}
