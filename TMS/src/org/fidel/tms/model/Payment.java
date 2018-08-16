package org.fidel.tms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Payment {
	@Id
	private int p_id;
	private int fo_id;	
	private String payment_type;
	private String payment_doc_ref_no;
	private Double payment_amount;
	private String payment_date;
	private String payment_status;
	
	@Transient
	private String date_from;
	@Transient
	private String date_to;
	
	@Transient
	private String directory_path;
	@Transient
	private String search_value;
	
	@Transient
	private String coupon_status;
	
	public Payment(){}
	
	public Payment(int p_id, int fo_id, String payment_type, String payment_doc_ref_no, Double payment_amount,
			String payment_date, String payment_status) {
		super();
		this.p_id = p_id;
		this.fo_id = fo_id;
		this.payment_type = payment_type;
		this.payment_doc_ref_no = payment_doc_ref_no;
		this.payment_amount = payment_amount;
		this.payment_date = payment_date;
		this.payment_status = payment_status;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getFo_id() {
		return fo_id;
	}
	public void setFo_id(int fo_id) {
		this.fo_id = fo_id;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public String getPayment_doc_ref_no() {
		return payment_doc_ref_no;
	}
	public void setPayment_doc_ref_no(String payment_doc_ref_no) {
		this.payment_doc_ref_no = payment_doc_ref_no;
	}
	public Double getPayment_amount() {
		return payment_amount;
	}
	public void setPayment_amount(Double payment_amount) {
		this.payment_amount = payment_amount;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public String getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}
	public String getDirectory_path() {
		return directory_path;
	}
	public void setDirectory_path(String directory_path) {
		this.directory_path = directory_path;
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

	public String getSearch_value() {
		return search_value;
	}

	public void setSearch_value(String search_value) {
		this.search_value = search_value;
	}

	public String getCoupon_status() {
		return coupon_status;
	}

	public void setCoupon_status(String coupon_status) {
		this.coupon_status = coupon_status;
	}
}
