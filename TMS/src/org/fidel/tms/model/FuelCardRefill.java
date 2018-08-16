package org.fidel.tms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class FuelCardRefill {
	
	@Id
	private int fcr_id;
	private int fc_id;
	private Double initial_balance;
	private Double topup_amount;
	private Double total_amount;
	private String fcr_status;
	
	@Transient
	private FuelCard fuelCard;
	
	public FuelCardRefill() {
		super();
	}

	public FuelCardRefill(int fcr_id, int fc_id, Double initial_balance, Double topup_amount, Double total_amount,
			String fcr_status) {
		super();
		this.fcr_id = fcr_id;
		this.fc_id = fc_id;
		this.initial_balance = initial_balance;
		this.topup_amount = topup_amount;
		this.total_amount = total_amount;
		this.fcr_status = fcr_status;
	}

	public int getFcr_id() {
		return fcr_id;
	}

	public void setFcr_id(int fcr_id) {
		this.fcr_id = fcr_id;
	}

	public int getFc_id() {
		return fc_id;
	}

	public void setFc_id(int fc_id) {
		this.fc_id = fc_id;
	}

	public Double getInitial_balance() {
		return initial_balance;
	}

	public void setInitial_balance(Double initial_balance) {
		this.initial_balance = initial_balance;
	}

	public Double getTopup_amount() {
		return topup_amount;
	}

	public void setTopup_amount(Double topup_amount) {
		this.topup_amount = topup_amount;
	}

	public Double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Double total_amount) {
		this.total_amount = total_amount;
	}

	public String getFcr_status() {
		return fcr_status;
	}

	public void setFcr_status(String fcr_status) {
		this.fcr_status = fcr_status;
	}

}
