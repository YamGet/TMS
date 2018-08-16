package org.fidel.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FuelExpense {
	@Id
	private int fe_id;
	private int fo_id;
	private String km_reading;
	private String total_liter;
	private String total_cost;
	private String payment_type;
	
	public FuelExpense(){}
	
	public FuelExpense(int fe_id, int fo_id, String km_reading, String total_liter, String total_cost,
			String payment_type) {
		super();
		this.fe_id = fe_id;
		this.fo_id = fo_id;
		this.km_reading = km_reading;
		this.total_liter = total_liter;
		this.total_cost = total_cost;
		this.payment_type = payment_type;
	}
	public int getFe_id() {
		return fe_id;
	}
	public void setFe_id(int fe_id) {
		this.fe_id = fe_id;
	}
	public int getFo_id() {
		return fo_id;
	}
	public void setFo_id(int fo_id) {
		this.fo_id = fo_id;
	}
	public String getKm_reading() {
		return km_reading;
	}
	public void setKm_reading(String km_reading) {
		this.km_reading = km_reading;
	}
	public String getTotal_liter() {
		return total_liter;
	}
	public void setTotal_liter(String total_liter) {
		this.total_liter = total_liter;
	}
	public String getTotal_cost() {
		return total_cost;
	}
	public void setTotal_cost(String total_cost) {
		this.total_cost = total_cost;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
}
