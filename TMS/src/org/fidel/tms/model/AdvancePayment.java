package org.fidel.tms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AdvancePayment {
	
	@Id
	private int ap_id;
	private int fo_id;
	private Double advance_payment_amount;
	private Double return_amount;
	private Double coupon_receive_amount;
	private Double coupon_transfer_amount;
	private String coupon_status;
	private Double additional_amount;
	private String send_reference_number;
	private String send_date;
	
	public AdvancePayment(){}
	
	public AdvancePayment(int ap_id, int fo_id, Double advance_payment_amount, Double return_amount, Double additional_amount) {
		super();
		this.ap_id = ap_id;
		this.fo_id = fo_id;
		this.advance_payment_amount = advance_payment_amount;
		this.return_amount = return_amount;
		this.additional_amount = additional_amount;
	}
	public int getAp_id() {
		return ap_id;
	}
	public void setAp_id(int ap_id) {
		this.ap_id = ap_id;
	}
	public int getFo_id() {
		return fo_id;
	}
	public void setFo_id(int fo_id) {
		this.fo_id = fo_id;
	}
	public Double getAdvance_payment_amount() {
		return advance_payment_amount;
	}
	public void setAdvance_payment_amount(Double advance_payment_amount) {
		this.advance_payment_amount = advance_payment_amount;
	}

	public Double getReturn_amount() {
		return return_amount;
	}

	public void setReturn_amount(Double return_amount) {
		this.return_amount = return_amount;
	}

	public Double getAdditional_amount() {
		return additional_amount;
	}

	public void setAdditional_amount(Double additional_amount) {
		this.additional_amount = additional_amount;
	}

	public String getSend_reference_number() {
		return send_reference_number;
	}

	public void setSend_reference_number(String send_reference_number) {
		this.send_reference_number = send_reference_number;
	}

	public String getSend_date() {
		return send_date;
	}

	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}

	public Double getCoupon_transfer_amount() {
		return coupon_transfer_amount;
	}

	public void setCoupon_transfer_amount(Double coupon_transfer_amount) {
		this.coupon_transfer_amount = coupon_transfer_amount;
	}

	public Double getCoupon_receive_amount() {
		return coupon_receive_amount;
	}

	public void setCoupon_receive_amount(Double coupon_receive_amount) {
		this.coupon_receive_amount = coupon_receive_amount;
	}

	public String getCoupon_status() {
		return coupon_status;
	}

	public void setCoupon_status(String coupon_status) {
		this.coupon_status = coupon_status;
	}
	
	

}
